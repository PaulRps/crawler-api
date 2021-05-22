package paulrps.crawler.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paulrps.crawler.domain.entity.UserJobFilter;
import paulrps.crawler.repositories.UserJobFilterRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserJobFilterServiceImpl implements UserJobFilterService {

  private final @NonNull UserJobFilterRepository repository;

  @Override
  public UserJobFilter findByUserId(String userId) {
    if (Objects.isNull(userId) || userId.isEmpty()) return null;
    return repository.findByUserIdAndIsActiveTrue(userId);
  }

  @Override
  public List<UserJobFilter> findAll() {
    return repository.findAll();
  }

  @Override
  public UserJobFilter save(UserJobFilter userJobFilter) {
    if (Objects.isNull(userJobFilter)) return null;
    if (!Objects.isNull(userJobFilter.getId())) return userJobFilter;
    return repository.save(userJobFilter);
  }
}
