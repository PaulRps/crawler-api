package paulrps.crawler.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import paulrps.crawler.domain.dto.EmailNotificationMessageDto;
import paulrps.crawler.domain.dto.GitHubIssuePageDto;
import paulrps.crawler.domain.entity.User;

import java.util.Arrays;

@SpringBootTest
class EmailNotifierITest {

  @Autowired
  @Qualifier("EmailNotifier")
  private JobNotifier jobNotifier;

  @Test
  void sendTo() {
    // given
    User user = User.builder().email("ricardopaulo18@hotmail.com").build();
    EmailNotificationMessageDto emailNotificationMessageDto =
        EmailNotificationMessageDto.builder()
            .subject("Testing Mail API")
            .body(
                Arrays.asList(
                    GitHubIssuePageDto.builder()
                        .title("Testing job opening")
                        .labels(Arrays.asList("Java", "Spring"))
                        .url("http://www.test.com")
                        .build()))
            .build();
    //then
    Assertions.assertDoesNotThrow(() -> jobNotifier.sendTo(user, emailNotificationMessageDto));
  }
}
