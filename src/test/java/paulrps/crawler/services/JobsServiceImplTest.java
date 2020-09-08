package paulrps.crawler.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JobsServiceImplTest {
  @Autowired private paulrps.crawler.services.JobService jobService;

  @Test
  void getFilteredJobs() {}

  @Test
  void notifyAllUsers() {
    Assertions.assertDoesNotThrow(() -> jobService.notifyAllUsers());
  }
}
