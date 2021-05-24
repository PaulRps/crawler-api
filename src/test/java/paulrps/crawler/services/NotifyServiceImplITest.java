package paulrps.crawler.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NotifyServiceImplITest {
  @Autowired private NotifyService notifyService;

  @Test
  void notifyAllUsers() {
    Assertions.assertDoesNotThrow(() -> notifyService.notifyJobsAllUsers());
  }

  @Test
  void notifyByUserEmail() {
    Assertions.assertDoesNotThrow(() -> notifyService.notifyJobsByUserEmail("paulosilvajp0@gmail.com"));
  }
}
