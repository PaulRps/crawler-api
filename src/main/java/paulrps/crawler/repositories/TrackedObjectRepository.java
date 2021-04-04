package paulrps.crawler.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import paulrps.crawler.domain.entity.TrackedObject;

import java.util.List;

public interface TrackedObjectRepository extends MongoRepository<TrackedObject, String> {
    List<TrackedObject> findByUserId(String userId);
}
