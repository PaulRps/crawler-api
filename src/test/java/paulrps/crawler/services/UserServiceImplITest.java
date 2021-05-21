package paulrps.crawler.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import paulrps.crawler.domain.entity.User;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserServiceImplITest {
  @Autowired private paulrps.crawler.services.UserService userService;

  private static final User testUser =
      User.builder().name("Paulo Silva").email("paulosilvajp0@gmail.com").build();

  @Test
  void save() {
    User storedUser = userService.findOneByEmail(testUser.getEmail());
    if (!Optional.ofNullable(storedUser).isPresent()) userService.save(testUser);
    User paulo = userService.findOneByEmail(testUser.getEmail());
    Assertions.assertEquals(paulo.getName(), testUser.getName());
  }

  @Test
  void findOneByEmail() {
    User oneByEmail = userService.findOneByEmail(testUser.getEmail());

    Assertions.assertNotNull(oneByEmail);
    Assertions.assertEquals(testUser.getEmail(), oneByEmail.getEmail());
  }

  @Test
  void findAll() {
    List<User> all = userService.findAll();

    Assertions.assertNotNull(all);
    Assertions.assertFalse(all.isEmpty());
  }
}
