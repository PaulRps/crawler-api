package paulrps.crawler.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import paulrps.crawler.domain.entity.UserJobFilter;

public interface UserJobFilterRepository extends MongoRepository<UserJobFilter, String> {

  UserJobFilter findByUserIdAndIsActiveTrue(String userId);

  UserJobFilter findByUserId(String userId);
}
