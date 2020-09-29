package paulrps.crawler.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paulrps.crawler.domain.dto.WebPageDataDto;
import paulrps.crawler.domain.entity.User;
import paulrps.crawler.domain.enums.WebPageEnum;
import paulrps.crawler.util.WebPageParserFactory;

@Service
public class JobsServiceImpl implements paulrps.crawler.services.JobService {
  private paulrps.crawler.services.UserService userService;

  @Autowired
  public JobsServiceImpl(final paulrps.crawler.services.UserService userService) {
    this.userService = userService;
  }

  @Override
  public List<WebPageDataDto> getByUserEmail(String email) {
    User user = userService.findOneByEmail(email);
    List<WebPageDataDto> jobs = new ArrayList<>();
    user.getWebPages()
        .forEach(
            webPageId ->
                WebPageParserFactory.getOne(WebPageEnum.getOne(Integer.parseInt(webPageId)))
                    .parseData().stream()
                    .filter(data -> filterData(data, user))
                    .forEach(jobs::add));
    return jobs;
  }

  @Override
  public Map<User, List<WebPageDataDto>> getAll() {
    Map<User, List<WebPageDataDto>> jobOpenningsMap = new LinkedHashMap<>();
    userService.findAllActive().stream()
        .forEach(user -> jobOpenningsMap.put(user, getByUserEmail(user.getEmail())));
    return jobOpenningsMap;
  }

  private boolean filterData(WebPageDataDto data, User user) {
    Map<String, String> dataMap = data.getDataMap();
    long countMatch =
        user.getJobKeyWords().stream()
            .filter(keyWord -> dataMap.containsKey(keyWord.toLowerCase()))
            .count();

    return (BigDecimal.valueOf(countMatch).divide(BigDecimal.valueOf(user.getJobKeyWords().size())))
            .compareTo(BigDecimal.valueOf(0.5))
        >= 0;
  }
}
