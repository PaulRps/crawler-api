package paulrps.crawler.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NotifyServiceImplTest {
  @Autowired private NotifyService notifyService;

  @Test
  void notifyAllUsers() {
    Assertions.assertDoesNotThrow(() -> notifyService.notifyAllUsers());
  }

  @Test
  void notifyByUserEmail() {
    Assertions.assertDoesNotThrow(() -> notifyService.notifyByUserEmail("paulosilvajp0@gmail.com"));
  }
}
