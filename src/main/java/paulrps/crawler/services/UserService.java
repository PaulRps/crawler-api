package paulrps.crawler.services;

import java.util.List;
import paulrps.crawler.domain.entity.User;

public interface UserService {

  User findOneByEmail(final String email);

  List<User> findAll();

  void save(User user);
}
