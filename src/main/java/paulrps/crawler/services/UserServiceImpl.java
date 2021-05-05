package paulrps.crawler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paulrps.crawler.domain.entity.User;
import paulrps.crawler.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

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
  public List<User> findAllActive() {
    return userRepository.findByIsActiveTrue();
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public void delete(String email) {
    Optional.ofNullable(userRepository.findByEmail(email))
        .ifPresent(user -> userRepository.delete(user));
  }
}
