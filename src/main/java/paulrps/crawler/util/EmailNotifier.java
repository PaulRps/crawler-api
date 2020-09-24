package paulrps.crawler.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import paulrps.crawler.domain.dto.EmailNotificationMessageDto;
import paulrps.crawler.domain.dto.NotificationMessageDto;
import paulrps.crawler.domain.entity.User;

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
    mail.setSubject(((EmailNotificationMessageDto) message).getSubject());
    mail.setText(messageFormatter.formatBody(message.getBody()));
    javaMailSender.send(mail);
  }
}
