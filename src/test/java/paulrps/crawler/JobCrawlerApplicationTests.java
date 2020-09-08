package paulrps.crawler;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import paulrps.crawler.domain.dto.WebPageDataDto;
import paulrps.crawler.domain.enums.WebPageEnum;
import paulrps.crawler.util.WebPageParserFactory;

@SpringBootTest
class JobCrawlerApplicationTests {

  @Test
  void contextLoads() {}

  @Test
  void testParser() {
    List<WebPageDataDto> webPageDataDtos =
        WebPageParserFactory.getOne(WebPageEnum.GITHUB_BACKEND_ISSUES).parseData();
    webPageDataDtos.forEach(System.out::println);
    Assertions.assertEquals(webPageDataDtos.size(), 25);
  }
}
