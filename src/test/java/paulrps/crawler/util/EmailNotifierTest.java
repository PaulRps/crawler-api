package paulrps.crawler.util;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import paulrps.crawler.domain.dto.EmailNotificationMessageDto;
import paulrps.crawler.domain.dto.GitHubIssuePageDto;
import paulrps.crawler.domain.entity.User;

@SpringBootTest
class EmailNotifierTest {

  @Autowired
  @Qualifier("EmailNotifier")
  private JobNotifier jobNotifier;

  @Test
  void sendTo() {
    Assertions.assertDoesNotThrow(
        () ->
            jobNotifier.sendTo(
                User.builder().email("ricardopaulo18@hotmail.com").build(),
                EmailNotificationMessageDto.builder()
                    .subject("Testing Mail API")
                    .body(
                        Arrays.asList(
                            GitHubIssuePageDto.builder()
                                .title("Testing job opening")
                                .labels(Arrays.asList("Java", "Spring"))
                                .url("http://www.test.com")
                                .build()))
                    .build()));
  }
}
