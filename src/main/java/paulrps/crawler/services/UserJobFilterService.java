package paulrps.crawler.services;

import paulrps.crawler.domain.dto.UserJobFilterDto;
import paulrps.crawler.domain.entity.UserJobFilter;

import java.util.List;

public interface UserJobFilterService {
  UserJobFilter findByUserId(String userId);

  UserJobFilter findByUserEmail(String email);

  List<UserJobFilter> findAll();

  UserJobFilter save(UserJobFilter userJobFilter);

  UserJobFilter save(UserJobFilterDto userJobFilterDto);

  void update(UserJobFilterDto userJobFilter);
}
