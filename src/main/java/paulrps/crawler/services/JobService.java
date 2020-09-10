package paulrps.crawler.services;

import java.util.List;
import java.util.Map;
import paulrps.crawler.domain.dto.WebPageDataDto;
import paulrps.crawler.domain.entity.User;

public interface JobService {
  List<WebPageDataDto> getByUserEmail(String email);

  Map<User, List<WebPageDataDto>> getAll();
}
