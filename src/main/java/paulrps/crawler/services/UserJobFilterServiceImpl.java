package paulrps.crawler.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paulrps.crawler.domain.converters.UserJobFilterConverter;
import paulrps.crawler.domain.dto.UserJobFilterDto;
import paulrps.crawler.domain.entity.User;
import paulrps.crawler.domain.entity.UserJobFilter;
import paulrps.crawler.repositories.UserJobFilterRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserJobFilterServiceImpl implements UserJobFilterService {

  private final @NonNull UserJobFilterRepository repository;
  private final @NonNull UserJobFilterConverter converter;
  private final @NonNull UserService userService;

  @Override
  public UserJobFilter findByUserId(final String userId) {
    if (Objects.isNull(userId) || userId.isEmpty()) return null;
    return repository.findByUserIdAndIsActiveTrue(userId);
  }

  @Override
  public UserJobFilter findByUserEmail(final String email) {
    if (Objects.isNull(email)) throw new RuntimeException("user email is null");

    User user = userService.findByEmail(email);

    if (Objects.isNull(user))
      throw new RuntimeException(String.format("user could not be found by email %s", email));

    return repository.findByUserId(user.getId());
  }

  @Override
  public List<UserJobFilter> findAll() {
    return repository.findAll();
  }

  @Override
  public UserJobFilter save(UserJobFilter userJobFilter) {
    if (Objects.isNull(userJobFilter)) throw new RuntimeException("userJobFilter is null");
    if (!Objects.isNull(userJobFilter.getId())) return userJobFilter;
    return repository.save(userJobFilter);
  }

  @Override
  public UserJobFilter save(UserJobFilterDto dto) {
    if (Objects.isNull(dto)) throw new RuntimeException("userJobFilter is null");

    User user = userService.findByEmail(dto.getUserEmail());

    if (Objects.isNull(user))
      throw new RuntimeException(
          String.format("user could not be found by email %s", dto.getUserEmail()));

    UserJobFilter userJobFilter = converter.toEntity(dto);
    userJobFilter.setUserId(user.getId());

    return repository.save(userJobFilter);
  }

  @Override
  public void update(UserJobFilterDto userJobFilterdto) {

    if (Objects.isNull(userJobFilterdto) || Objects.isNull(userJobFilterdto.getId()))
      throw new RuntimeException("userJobFilter is null or has no id");

    UserJobFilter toUpdate = converter.toEntity(userJobFilterdto);

    repository.save(toUpdate);
  }
}
