package paulrps.crawler.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paulrps.crawler.domain.converters.TrackObjectConverter;
import paulrps.crawler.domain.dto.TrackDataDto;
import paulrps.crawler.domain.dto.TrackObjectDto;
import paulrps.crawler.domain.entity.TrackObject;
import paulrps.crawler.domain.entity.User;
import paulrps.crawler.domain.enums.ParserTypeEnum;
import paulrps.crawler.repositories.TrackObjectRepository;
import paulrps.crawler.util.WebPageParser;
import paulrps.crawler.util.WebPageParserFactory;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TrackObjectServiceImpl implements TrackObjectService {

  private final @NonNull TrackObjectRepository repository;
  private final @NonNull UserService userService;
  private final @NonNull TrackObjectConverter converter;

  @Override
  public TrackObject save(TrackObjectDto trackObjectDto) {
    TrackObject trackObject = converter.toEntity(trackObjectDto);
    Optional.ofNullable(userService.findOneByEmail(trackObjectDto.getUserEmail()))
        .ifPresent(user -> trackObject.setUserId(user.getId()));

    return repository.save(trackObject);
  }

  @Override
  public TrackObject update(TrackObjectDto trackObjectDto) {

    Optional.ofNullable(trackObjectDto)
        .orElseThrow(() -> new RuntimeException("tracked object is null"));

    TrackObject trackObject = repository.findByTrackCode(trackObjectDto.getTrackCode());
    Optional.ofNullable(trackObject)
        .orElseThrow(() -> new RuntimeException("tracked object does not exist"));

    TrackObject toUpdate = converter.toEntity(trackObjectDto);
    toUpdate.setId(trackObject.getId());

    return repository.save(toUpdate);
  }

  @Override
  public void inactivate(String trackCode) {
    Optional.ofNullable(trackCode).orElseThrow(() -> new RuntimeException("trackCode is null"));
    TrackObject trackObject = repository.findByTrackCode(trackCode);
    trackObject.setIsActive(false);
    repository.save(trackObject);
  }

  @Override
  public void delete(String id) {
    Optional.ofNullable(id).orElseThrow(() -> new RuntimeException("tracked object id is null"));
    if (!repository.existsById(id)) throw new RuntimeException("tracked object does not exist");

    repository.deleteById(id);
  }

  @Override
  public List<TrackDataDto> getTrackObjByUserEmail(String email) {
    User user = userService.findOneByEmail(email);
    List<TrackObject> trackObjects = repository.findByUserIdAndIsActiveTrue(user.getId());

    Map<String, String> trackCodeDescriptionMap = new LinkedHashMap<String, String>();
    trackObjects.forEach(td -> trackCodeDescriptionMap.put(td.getTrackCode(), td.getDescription()));

    List<TrackDataDto> result = new ArrayList<>();
    trackObjects.stream()
        .collect(Collectors.groupingBy(TrackObject::getCarrier))
        .forEach(
            (k, v) -> {
              ParserTypeEnum senderType = ParserTypeEnum.getOne(k);
              WebPageParser instance = WebPageParserFactory.getInstance(senderType);

              ((TrackingParseService) instance).setTrackObjects(trackObjects);
              List<TrackDataDto> list = instance.<TrackDataDto>parseData(senderType.getUrl());
              list.forEach(
                  td -> td.setDescription(trackCodeDescriptionMap.get(td.getTrackingCode())));

              result.addAll(list);
              updateIfChanged(v, list);
            });

    return result;
  }

  private void updateIfChanged(List<TrackObject> trackObjectList, List<TrackDataDto> result) {

    Map<String, TrackObject> trackObjMap =
        trackObjectList.stream().collect(Collectors.toMap(TrackObject::getTrackCode, to -> to));

    result.forEach(
        r -> {
          TrackObject trackObject = trackObjMap.get(r.getTrackingCode());
          long size = r.getEvents().size();
          boolean hasChanged = trackObject.hasChanged(size);
          r.setHasChanged(hasChanged);
          if (hasChanged) {
            trackObject.setLastEventsAmount(size);
            repository.save(trackObject);
          }
        });
  }
}
