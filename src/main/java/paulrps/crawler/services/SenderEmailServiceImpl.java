package paulrps.crawler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import paulrps.crawler.domain.dto.EmailContent;

@Service
public class SenderEmailServiceImpl implements paulrps.crawler.services.SenderEmailService {

  @Autowired private JavaMailSender javaMailSender;

  @Override
  public void sendTo(String email, EmailContent content) {
    SimpleMailMessage mail = new SimpleMailMessage();
    mail.setTo(email);
    mail.setSubject(content.getSubject());
    mail.setText(content.getBody());

    javaMailSender.send(mail);
  }
}
