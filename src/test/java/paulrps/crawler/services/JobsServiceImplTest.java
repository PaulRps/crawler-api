package paulrps.crawler.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import paulrps.crawler.domain.dto.UserDto;
import paulrps.crawler.domain.dto.WebPageDataDto;
import paulrps.crawler.domain.entity.JobFilter;
import paulrps.crawler.domain.entity.User;

import java.util.*;

@SpringBootTest
class JobsServiceImplTest {
  @Autowired private paulrps.crawler.services.JobService jobService;
  @Autowired private UserService userService;
  @Autowired private JobFilterService jobFilterService;

  @Test
  void getByUserEmail() {
    String email = "ricardopaulo18@hotmail.com";
    User user = userService.findByEmail(email);

    if (!Optional.ofNullable(user).isPresent()) {
      UserDto userDto = UserDto.builder().name("Paulo Silva").email(email).build();
      user = userService.save(userDto);
    }

    JobFilter jobFilter = jobFilterService.findByUserId(user.getId());

    if (Objects.isNull(jobFilter)) {
      jobFilter =
          JobFilter.builder()
              .userId(user.getId())
              .jobOpeningSources(Arrays.asList(1))
              .jobKeyWords(Arrays.asList("Java", "Remoto", "Remota", "CLT", "Spring"))
              .isActive(true)
              .build();
      jobFilterService.save(jobFilter);
    }

    Assertions.assertNotNull(user);
    Assertions.assertFalse(user.getEmail().isEmpty());
    Assertions.assertEquals(email, user.getEmail());
    Assertions.assertNotNull(jobFilter.getJobOpeningSources());
    Assertions.assertFalse(jobFilter.getJobKeyWords().isEmpty());
    Assertions.assertNotNull(jobFilter.getJobKeyWords());
    Assertions.assertFalse(jobFilter.getJobKeyWords().isEmpty());

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
