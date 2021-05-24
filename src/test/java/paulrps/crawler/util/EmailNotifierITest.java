package paulrps.crawler.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import paulrps.crawler.domain.dto.EmailNotificationMessageDto;
import paulrps.crawler.domain.dto.GitHubIssuePageDto;
import paulrps.crawler.domain.entity.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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
    // then
    Assertions.assertDoesNotThrow(() -> jobNotifier.sendTo(user, emailNotificationMessageDto));
  }

  @Autowired private JavaMailSender javaMailSender;
  @Test
  void sendEmailWithAttachment() {

    try {
      MimeMessage msg = javaMailSender.createMimeMessage();

      // true = multipart message
      MimeMessageHelper helper = new MimeMessageHelper(msg, true);

      helper.setTo("paulosilvajp0@gmail.com");

      helper.setSubject("Testing from Spring Boot");

      // default = text/plain
      // helper.setText("Check attachment for image!");

      // true = text/html
      helper.setText("<h1>Check attachment for image!</h1>", true);

      // hard coded a file path
      // FileSystemResource file = new FileSystemResource(new File("path/android.png"));

      //      helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

      javaMailSender.send(msg);
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}
