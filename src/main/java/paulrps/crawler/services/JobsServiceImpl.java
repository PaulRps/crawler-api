package paulrps.crawler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paulrps.crawler.domain.dto.WebPageDataDto;
import paulrps.crawler.domain.entity.User;
import paulrps.crawler.domain.enums.ParserTypeEnum;
import paulrps.crawler.util.WebPageParserFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    Map<Integer, String> jobType =
        Map.of(
            ParserTypeEnum.GITHUB_BACKEND_ISSUES.getId(),
            ParserTypeEnum.GITHUB_BACKEND_ISSUES.getUrl());

    user.getWebPages().stream()
        .filter(webPageId -> jobType.containsKey(webPageId))
        .forEach(
            webPageId -> {
              ParserTypeEnum parserTypeEnum = ParserTypeEnum.getOne(webPageId);

              WebPageParserFactory.getInstance(parserTypeEnum)
                  .parseData(parserTypeEnum.getUrl())
                  .stream()
                  .filter(data -> filterData((WebPageDataDto) data, user))
                  .forEach(job -> jobs.add((WebPageDataDto) job));
            });
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
