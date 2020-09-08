package paulrps.crawler.util;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import paulrps.crawler.domain.dto.WebPageDataDto;
import paulrps.crawler.domain.enums.WebPageEnum;

public abstract class WebPageParser {

  protected WebPageEnum webPageEnum;

  public abstract List<WebPageDataDto> parseData();

  protected Optional<Document> getDocument(String url) {
    try {
      Connection.Response response =
          Jsoup.connect(url)
              .ignoreContentType(true)
              .userAgent(Constants.CHROME_USER_AGENT)
              .followRedirects(true)
              .execute();
      return Optional.ofNullable(response.parse());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  protected String getPageUrl() {
    return this.webPageEnum.getUrl();
  }
}
