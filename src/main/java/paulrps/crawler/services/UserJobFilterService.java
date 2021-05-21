package paulrps.crawler.services;

import paulrps.crawler.domain.entity.UserJobFilter;

import java.util.List;

public interface UserJobFilterService {
  UserJobFilter findByUserId(String userId);

  List<UserJobFilter> findAll();

  UserJobFilter save(UserJobFilter userJobFilter);
}
