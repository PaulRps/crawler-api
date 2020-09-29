package paulrps.crawler.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import paulrps.crawler.domain.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
  User findByName(String name);

  User findByEmail(String email);

  List<User> findByIsActiveTrue();
}
