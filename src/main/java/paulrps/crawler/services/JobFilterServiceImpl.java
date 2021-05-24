package paulrps.crawler.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paulrps.crawler.domain.converters.JobFilterConverter;
import paulrps.crawler.domain.dto.JobFilterDto;
import paulrps.crawler.domain.entity.JobFilter;
import paulrps.crawler.domain.entity.User;
import paulrps.crawler.repositories.JobFilterRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JobFilterServiceImpl implements JobFilterService {

  private final @NonNull JobFilterRepository repository;
  private final @NonNull JobFilterConverter converter;
  private final @NonNull UserService userService;

  @Override
  public JobFilter findByUserId(final String userId) {
    if (Objects.isNull(userId) || userId.isEmpty()) return null;
    return repository.findByUserIdAndIsActiveTrue(userId);
  }

  @Override
  public JobFilter findByUserEmail(final String email) {
    if (Objects.isNull(email)) throw new RuntimeException("user email is null");

    User user = userService.findByEmail(email);

    if (Objects.isNull(user))
      throw new RuntimeException(String.format("user could not be found by email %s", email));

    return repository.findByUserId(user.getId());
  }

  @Override
  public List<JobFilter> findAll() {
    return repository.findAll();
  }

  @Override
  public JobFilter save(JobFilter jobFilter) {
    if (Objects.isNull(jobFilter)) throw new RuntimeException("jobFilter is null");
    if (!Objects.isNull(jobFilter.getId())) return jobFilter;
    return repository.save(jobFilter);
  }

  @Override
  public JobFilter save(JobFilterDto dto) {
    if (Objects.isNull(dto)) throw new RuntimeException("jobFilter is null");

    User user = userService.findByEmail(dto.getUserEmail());

    if (Objects.isNull(user))
      throw new RuntimeException(
          String.format("user could not be found by email %s", dto.getUserEmail()));

    JobFilter jobFilter = converter.toEntity(dto);
    jobFilter.setUserId(user.getId());

    return repository.save(jobFilter);
  }

  @Override
  public void update(JobFilterDto jobFilterdto) {

    if (Objects.isNull(jobFilterdto) || Objects.isNull(jobFilterdto.getId()))
      throw new RuntimeException("userJobFilter is null or has no id");

    JobFilter toUpdate = converter.toEntity(jobFilterdto);

    repository.save(toUpdate);
  }
}
