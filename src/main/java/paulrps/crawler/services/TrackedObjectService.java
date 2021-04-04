package paulrps.crawler.services;

import paulrps.crawler.domain.dto.TrackingDataDto;
import paulrps.crawler.domain.entity.TrackedObject;

import java.util.List;

public interface TrackedObjectService {

  void save(TrackedObject trackedObject);

  TrackedObject update(TrackedObject trackedObject);

  void delete(String id);

  List<TrackingDataDto> parseByUserEmail(String email);
}
