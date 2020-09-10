package paulrps.crawler.services;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import paulrps.crawler.domain.dto.EmailNotificationMessageDtoDto;
import paulrps.crawler.domain.dto.WebPageDataDto;
import paulrps.crawler.domain.entity.User;
import paulrps.crawler.util.JobNotifier;

@Service
public class NotifyServiceImpl implements NotifyService {
  private JobService jobService;
  private JobNotifier emailNotifier;

  @Autowired
  public NotifyServiceImpl(
      final JobService jobService, final @Qualifier("EmailNotifier") JobNotifier emailNotifier) {
    this.jobService = jobService;
    this.emailNotifier = emailNotifier;
  }

  @Override
  public void notifyAllUsers() {

    Map<User, List<WebPageDataDto>> jobOpenningsMap = jobService.getAll();

    jobOpenningsMap.forEach(
        (user, data) ->
            emailNotifier.sendTo(
                user,
                EmailNotificationMessageDtoDto.builder()
                    .subject("Test Job Crawler")
                    .body(data)
                    .build()));
  }

  @Override
  public void notifyByUserEmail(String email) {
    List<WebPageDataDto> data = jobService.getByUserEmail(email);
    emailNotifier.sendTo(
        User.builder().email(email).build(),
        EmailNotificationMessageDtoDto.builder().subject("Test Job crawler").body(data).build());
  }
}
