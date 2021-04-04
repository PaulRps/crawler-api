package paulrps.crawler.services;

import paulrps.crawler.domain.entity.TrackedObject;

import java.util.List;

public interface TrackingService {
  void setTrackedObjects(List<TrackedObject> trackedObjects);
}
