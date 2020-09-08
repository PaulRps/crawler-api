package paulrps.crawler.services;

import java.util.List;
import paulrps.crawler.domain.dto.WebPageDataDto;

public interface JobService {
  List<WebPageDataDto> getFilteredJobs(String email);

  void notifyAllUsers();
}
