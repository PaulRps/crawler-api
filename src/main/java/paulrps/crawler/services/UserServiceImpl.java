package paulrps.crawler.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paulrps.crawler.domain.converters.UserConverter;
import paulrps.crawler.domain.dto.UserDto;
import paulrps.crawler.domain.entity.User;
import paulrps.crawler.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements paulrps.crawler.services.UserService {
  private final @NonNull UserRepository userRepository;
  private final @NonNull UserConverter converter;

  @Override
  public User findByEmail(final String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public User save(UserDto userDto) {
    User user = converter.toEntity(userDto);
    return userRepository.save(user);
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public void update(UserDto userDto) {
    Optional.ofNullable(userDto).orElseThrow(() -> new RuntimeException("user parameter is null"));

    User user = userRepository.findByEmail(userDto.getEmail());

    Optional.ofNullable(user).orElseThrow(() -> new RuntimeException("user does not exist"));

    User toUpdate = converter.toEntity(userDto);
    toUpdate.setId(user.getId());

    userRepository.save(toUpdate);
  }

  @Override
  public void delete(String email) {
    Optional.ofNullable(userRepository.findByEmail(email))
        .ifPresent(user -> userRepository.delete(user));
  }
}
