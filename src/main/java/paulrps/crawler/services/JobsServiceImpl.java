package paulrps.crawler.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paulrps.crawler.domain.dto.EmailContent;
import paulrps.crawler.domain.dto.WebPageDataDto;
import paulrps.crawler.domain.entity.User;
import paulrps.crawler.domain.enums.WebPageEnum;
import paulrps.crawler.util.EmailContentFormatter;
import paulrps.crawler.util.WebPageParserFactory;

@Service
public class JobsServiceImpl implements paulrps.crawler.services.JobService {
  private paulrps.crawler.services.UserService userService;
  private paulrps.crawler.services.SenderEmailService senderEmailService;
  private EmailContentFormatter emailContentFormatter;

  @Autowired
  JobsServiceImpl(
      paulrps.crawler.services.UserService userService,
      paulrps.crawler.services.SenderEmailService senderEmailService,
      EmailContentFormatter emailContentFormatter) {
    this.userService = userService;
    this.senderEmailService = senderEmailService;
    this.emailContentFormatter = emailContentFormatter;
  }

  @Override
  public List<WebPageDataDto> getFilteredJobs(String email) {
    User user = userService.findOneByEmail(email);
    List<WebPageDataDto> jobs = new ArrayList<>();
    user.getWebPages()
        .forEach(
            webPageId ->
                WebPageParserFactory.getOne(WebPageEnum.getOne(Integer.parseInt(webPageId)))
                    .parseData().stream()
                    .filter(data -> filterData(data, user))
                    .forEach(data -> jobs.add(data)));
    return jobs;
  }

  @Override
  public void notifyAllUsers() {
    Map<User, List<WebPageDataDto>> jobOpenningsMap = new LinkedHashMap<>();
    userService.findAll().stream()
        .forEach(user -> jobOpenningsMap.put(user, getFilteredJobs(user.getEmail())));

    jobOpenningsMap.forEach(
        (user, data) ->
            senderEmailService.sendTo(
                user.getEmail(),
                EmailContent.builder()
                    .subject("Test crawler API")
                    .body(emailContentFormatter.formatBody(data))
                    .build()));
  }

  private boolean filterData(WebPageDataDto data, User user) {
    Map<String, String> dataMap = data.getDataMap();
    long countMatch =
        user.getJobKeyWords().stream()
            .filter(keyWord -> dataMap.containsKey(keyWord.toLowerCase()))
            .count();
    return (user.getJobKeyWords().size() - countMatch) <= 2;
  }
}
