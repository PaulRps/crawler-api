package paulrps.crawler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paulrps.crawler.domain.dto.TrackDataDto;
import paulrps.crawler.domain.entity.TrackObject;
import paulrps.crawler.domain.entity.User;
import paulrps.crawler.domain.enums.ParserTypeEnum;
import paulrps.crawler.repositories.TrackObjectRepository;
import paulrps.crawler.util.WebPageParser;
import paulrps.crawler.util.WebPageParserFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackObjectServiceImpl implements TrackObjectService {

  private TrackObjectRepository repository;
  private UserService userService;

  @Autowired
  public TrackObjectServiceImpl(TrackObjectRepository repository, UserService userService) {
    super();
    this.repository = repository;
    this.userService = userService;
  }

  @Override
  public TrackObject save(TrackObject trackObject) {
    return repository.save(trackObject);
  }

  @Override
  public TrackObject update(TrackObject trackObject) {
    Optional.ofNullable(trackObject)
        .orElseThrow(() -> new RuntimeException("tracked object is null"));
    Optional.ofNullable(trackObject.getId())
        .orElseThrow(() -> new RuntimeException("tracked object id is null"));

    return repository.save(trackObject);
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

    List<TrackDataDto> result = new ArrayList<>();
    trackObjects.stream()
        .collect(Collectors.groupingBy(TrackObject::getSender))
        .forEach(
            (k, v) -> {
              ParserTypeEnum senderType = ParserTypeEnum.getOne(k);
              WebPageParser instance = WebPageParserFactory.getInstance(senderType);
              ((TrackingParseService) instance).setTrackObjects(trackObjects);
              List list = instance.parseData(senderType.getUrl());
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
            trackObject.setLastEvents(size);
            repository.save(trackObject);
          }
        });
  }
}
