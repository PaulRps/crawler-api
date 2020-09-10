package paulrps.crawler.services;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import paulrps.crawler.domain.dto.WebPageDataDto;
import paulrps.crawler.domain.entity.User;

@SpringBootTest
class JobsServiceImplTest {
  @Autowired private paulrps.crawler.services.JobService jobService;
  @Autowired private UserService userService;

  @Test
  void getByUserEmail() {
    String email = "paulosilvajp0@gmail.com";
    User user = userService.findOneByEmail(email);

    if (!Optional.ofNullable(user).isPresent()) {
      user =
          User.builder()
              .name("Paulo Silva")
              .email(email)
              .webPages(Arrays.asList("1"))
              .jobKeyWords(Arrays.asList("Java", "Remoto", "Remota", "CLT", "Spring"))
              .build();
      userService.save(user);
    }

    Assertions.assertNotNull(user);
    Assertions.assertFalse(user.getEmail().isEmpty());
    Assertions.assertEquals(email, user.getEmail());
    Assertions.assertNotNull(user.getWebPages());
    Assertions.assertFalse(user.getJobKeyWords().isEmpty());
    Assertions.assertNotNull(user.getJobKeyWords());
    Assertions.assertFalse(user.getJobKeyWords().isEmpty());

    List<WebPageDataDto> byUserEmail = jobService.getByUserEmail(user.getEmail());

    Assertions.assertNotNull(byUserEmail);
    Assertions.assertFalse(byUserEmail.isEmpty());
  }

  @Test
  void getAll() {
    Map<User, List<WebPageDataDto>> all = jobService.getAll();
    Assertions.assertNotNull(all);
    Assertions.assertFalse(all.isEmpty());
  }
}
