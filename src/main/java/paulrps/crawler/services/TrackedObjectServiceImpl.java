package paulrps.crawler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paulrps.crawler.domain.dto.TrackingDataDto;
import paulrps.crawler.domain.entity.TrackedObject;
import paulrps.crawler.domain.entity.User;
import paulrps.crawler.domain.enums.ParserTypeEnum;
import paulrps.crawler.repositories.TrackedObjectRepository;
import paulrps.crawler.util.WebPageParser;
import paulrps.crawler.util.WebPageParserFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackedObjectServiceImpl implements TrackedObjectService {

  private TrackedObjectRepository repository;
  private UserService userService;

  @Autowired
  public TrackedObjectServiceImpl(TrackedObjectRepository repository, UserService userService) {
    super();
    this.repository = repository;
    this.userService = userService;
  }

  @Override
  public TrackedObject save(TrackedObject trackedObject) {
    return repository.save(trackedObject);
  }

  @Override
  public TrackedObject update(TrackedObject trackedObject) {
    Optional.ofNullable(trackedObject)
        .orElseThrow(() -> new RuntimeException("tracked object is null"));
    Optional.ofNullable(trackedObject.getId())
        .orElseThrow(() -> new RuntimeException("tracked object id is null"));

    return repository.save(trackedObject);
  }

  @Override
  public void delete(String id) {
    Optional.ofNullable(id).orElseThrow(() -> new RuntimeException("tracked object id is null"));
    if (!repository.existsById(id)) throw new RuntimeException("tracked object does not exist");

    repository.deleteById(id);
  }

  @Override
  public List<TrackingDataDto> parseByUserEmail(String email) {
    User user = userService.findOneByEmail(email);
    List<TrackedObject> trackedObjects = repository.findByUserId(user.getId());

    List<TrackingDataDto> result = new ArrayList<>();
    trackedObjects.stream()
        .collect(Collectors.groupingBy(TrackedObject::getSender))
        .forEach(
            (k, v) -> {
              ParserTypeEnum senderType = ParserTypeEnum.getOne(k);
              WebPageParser instance = WebPageParserFactory.getInstance(senderType);
              ((TrackingService) instance).setTrackedObjects(trackedObjects);
              List list = instance.parseData(senderType.getUrl());
              result.addAll(list);
              updateIfChanged(trackedObjects, list);
            });

    return result;
  }

  private void updateIfChanged(
      List<TrackedObject> trackedObjectList, List<TrackingDataDto> result) {

    Map<String, TrackedObject> trackObjMap =
        trackedObjectList.stream()
            .collect(Collectors.toMap(TrackedObject::getTrackingCode, to -> to));

    result.forEach(
        r -> {
          TrackedObject trackedObject = trackObjMap.get(r.getTrackingCode());
          long size = r.getEvents().size();
          if (trackedObject.hasChanged(size)) {
            trackedObject.setLastEvents(size);
            repository.save(trackedObject);
          }
        });
  }
}
