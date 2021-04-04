package paulrps.crawler.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import paulrps.crawler.domain.entity.TrackObject;

import java.util.List;

public interface TrackObjectRepository extends MongoRepository<TrackObject, String> {
  List<TrackObject> findByUserIdAndIsActiveTrue(String userId);

  TrackObject findByTrackCode(String trackCode);
}
