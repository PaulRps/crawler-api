package paulrps.crawler.util;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import paulrps.crawler.domain.dto.EmailNotificationMessageDto;
import paulrps.crawler.domain.dto.NotificationMessageDto;
import paulrps.crawler.domain.entity.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Component("EmailNotifier")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailNotifier extends JobNotifier {

  private final @NonNull JavaMailSender javaMailSender;
  private final @NonNull @Qualifier("EmailJobMessageFormatter") MessageFormatter messageFormatter;

  @Override
  public void sendTo(User user, NotificationMessageDto message) {
    sendTo(
        user,
        ((EmailNotificationMessageDto) message).getSubject(),
        messageFormatter.formatBody(message.getBody()));
  }

  @Override
  public void sendTo(User user, String subject, String message) {
    if (message.startsWith("<!DOCTYPE html PUBLIC")) sendHtmlMessage(user, subject, message);
    else sendSimpleMessage(user, subject, message);
  }

  private void sendHtmlMessage(User user, String subject, String htmlMessage) {
    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper =
          new MimeMessageHelper(
              message,
              MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
              StandardCharsets.UTF_8.name());

      helper.setTo(user.getEmail());
      helper.setText(htmlMessage, true);
      helper.setSubject(subject);

      javaMailSender.send(message);
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }

  private void sendSimpleMessage(User user, String subject, String message) {
    SimpleMailMessage mail = new SimpleMailMessage();
    mail.setTo(user.getEmail());
    mail.setSubject(subject);
    mail.setText(message);
    javaMailSender.send(mail);
  }
}
