package paulrps.crawler.services;

import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import paulrps.crawler.domain.entity.User;

@SpringBootTest
class UserServiceImplTest {
  @Autowired private paulrps.crawler.services.UserService userService;

  @Test
  void findOneByEmail() {}

  @Test
  void findAll() {}

  @Test
  void save() {
    User user =
        User.builder()
            .name("Paulo Silva")
            .email("ricardopaulo18@hotmail.com")
            .webPages(Arrays.asList("1"))
            .jobKeyWords(Arrays.asList("Java", "Remoto", "Remota", "CLT"))
            .build();
    User storedUser = userService.findOneByEmail(user.getEmail());
    if (!Optional.ofNullable(storedUser).isPresent()) userService.save(user);
    User paulo = userService.findOneByEmail(user.getEmail());
    Assertions.assertEquals(paulo.getName(), user.getName());
  }
}
