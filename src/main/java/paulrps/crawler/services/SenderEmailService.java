package paulrps.crawler.services;

import paulrps.crawler.domain.dto.EmailContent;

public interface SenderEmailService {
  void sendTo(String email, EmailContent content);
}
