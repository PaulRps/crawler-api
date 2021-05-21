package paulrps.crawler.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import paulrps.crawler.domain.entity.User;

@Testcontainers
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class UserRepositoryTest {

  @Container static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry diProperties) {
    diProperties.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }

  @Autowired UserRepository repository;

  private User user = User.builder().name("teste").email("teste@gmail.com").build();

  @AfterEach
  void setUp() {
    repository.deleteAll();
  }

  @Test
  void findByName() {
    // given
    repository.save(user);

    // when
    User byName = repository.findByName(user.getName());

    // then
    Assertions.assertThat(byName).isNotNull();
    Assertions.assertThat(byName.getName()).isEqualTo(user.getName());
  }

  @Test
  void findByEmail() {}

  @Test
  void findByIsActiveTrue() {}
}
