package paulrps.crawler.services;

import paulrps.crawler.domain.dto.UserDto;
import paulrps.crawler.domain.entity.User;

import java.util.List;

public interface UserService {

  User findByEmail(final String email);

  List<User> findAll();

  User save(UserDto user);

  User save(User user);

  void update(UserDto user);

  void delete(String email);
}
