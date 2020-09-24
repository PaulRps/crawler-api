package paulrps.crawler.services;

import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import paulrps.crawler.domain.dto.EmailNotificationMessageDto;
import paulrps.crawler.domain.dto.WebPageDataDto;
import paulrps.crawler.domain.entity.User;
import paulrps.crawler.util.JobNotifier;

@Slf4j
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
    log.info("SENDING JOB OPENINGS NOTIFICATION TO ALL USERS");
    Map<User, List<WebPageDataDto>> userJobOpenningsMap = jobService.getAll();

    userJobOpenningsMap.forEach(
        (user, data) ->
            emailNotifier.sendTo(
                user,
                EmailNotificationMessageDto.builder()
                    .subject("Job Crawler Summary")
                    .body(data)
                    .build()));
    log.info("SENT JOB OPENINGS NOTIFICATION TO ALL USERS");
  }

  @Override
  public void notifyByUserEmail(String email) {
    log.info("SENDING JOB OPENINGS NOTIFICATION TO {}", email);
    List<WebPageDataDto> data = jobService.getByUserEmail(email);
    emailNotifier.sendTo(
        User.builder().email(email).build(),
        EmailNotificationMessageDto.builder().subject("Job Crawler Summary").body(data).build());
    log.info("SENT JOB OPENINGS NOTIFICATION TO {}", email);
  }
}
