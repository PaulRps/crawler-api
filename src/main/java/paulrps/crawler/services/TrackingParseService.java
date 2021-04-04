package paulrps.crawler.services;

import paulrps.crawler.domain.entity.TrackObject;

import java.util.List;

public interface TrackingParseService {
  void setTrackObjects(List<TrackObject> trackObjects);
}
