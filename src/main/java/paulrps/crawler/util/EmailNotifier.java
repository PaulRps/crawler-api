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
    sendTo(
        user,
        ((EmailNotificationMessageDto) message).getSubject(),
        messageFormatter.formatBody(message.getBody()));
  }

  @Override
  public void sendTo(User user, String subject, String message) {
    SimpleMailMessage mail = new SimpleMailMessage();
    mail.setTo(user.getEmail());
    mail.setSubject(subject);
    mail.setText(message);
    javaMailSender.send(mail);
  }
}
