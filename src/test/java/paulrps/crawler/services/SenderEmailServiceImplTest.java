package paulrps.crawler.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import paulrps.crawler.domain.dto.EmailContent;

@SpringBootTest
class SenderEmailServiceImplTest {

  @Autowired private paulrps.crawler.services.SenderEmailService senderEmailService;

  @Test
  void sendTo() {
    Assertions.assertDoesNotThrow(
        () ->
            senderEmailService.sendTo(
                "mateus.sobral.monteiro@gmail.com",
                EmailContent.builder()
                    .subject("Testing Mail API")
                    .body("Hurray ! I'm waiting for the invite, do you send today?")
                    .build()));
  }
}
