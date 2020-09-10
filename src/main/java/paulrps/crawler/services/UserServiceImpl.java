package paulrps.crawler.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paulrps.crawler.domain.entity.User;
import paulrps.crawler.repositories.UserRepository;

@Service
public class UserServiceImpl implements paulrps.crawler.services.UserService {
  private UserRepository userRepository;

  @Autowired
  public UserServiceImpl(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User findOneByEmail(final String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public void save(User user) {
    userRepository.save(user);
  }
}
