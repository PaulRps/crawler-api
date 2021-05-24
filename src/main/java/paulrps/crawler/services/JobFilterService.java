package paulrps.crawler.services;

import paulrps.crawler.domain.dto.JobFilterDto;
import paulrps.crawler.domain.entity.JobFilter;

import java.util.List;

public interface JobFilterService {
  JobFilter findByUserId(String userId);

  JobFilter findByUserEmail(String email);

  List<JobFilter> findAll();

  JobFilter save(JobFilter jobFilter);

  JobFilter save(JobFilterDto jobFilterDto);

  void update(JobFilterDto userJobFilter);
}
