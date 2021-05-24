package paulrps.crawler.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import paulrps.crawler.domain.entity.JobFilter;

public interface JobFilterRepository extends MongoRepository<JobFilter, String> {

  JobFilter findByUserIdAndIsActiveTrue(String userId);

  JobFilter findByUserId(String userId);
}
