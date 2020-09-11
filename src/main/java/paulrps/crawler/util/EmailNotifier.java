package paulrps.crawler.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import paulrps.crawler.domain.dto.EmailNotificationMessageDtoDto;
import paulrps.crawler.domain.dto.NotificationMessageDto;
import paulrps.crawler.domain.entity.User;

@Slf4j
@Component("EmailNotifier")
public class EmailNotifier extends JobNotifier {

  private JavaMailSender javaMailSender;

  @Autowired
  public EmailNotifier(
      final @Qualifier("EmailMessageFormatter") MessageFormatter emailMessageFormatter,
      final JavaMailSender javaMailSender) {
    this.messageFormatter = emailMessageFormatter;
    this.javaMailSender = javaMailSender;
  }

  @Override
  public void sendTo(User user, NotificationMessageDto message) {
    SimpleMailMessage mail = new SimpleMailMessage();
    mail.setTo(user.getEmail());
    mail.setSubject(((EmailNotificationMessageDtoDto) message).getSubject());
    mail.setText(messageFormatter.formatBody(message.getBody()));
    log.info(System.getenv("EMAIL_PASSWORD"));
    log.info(System.getenv("EMAIL_USERNAME"));
    log.info(System.getenv("DATABASE_URI"));
    javaMailSender.send(mail);
  }
}
