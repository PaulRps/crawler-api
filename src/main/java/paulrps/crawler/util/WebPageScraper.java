package paulrps.crawler.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class WebPageScraper {

  public static Optional<Document> getDocument(
      String url, Connection.Method method, Map<String, String> data) {
    try {
      Connection connection =
          Jsoup.connect(url)
              .ignoreContentType(true)
              .userAgent(Constants.CHROME_USER_AGENT)
              .method(method)
              .followRedirects(true);

      if (Objects.nonNull(data) && !data.isEmpty()) connection.data(data);

      Connection.Response response = connection.execute();

      return Optional.ofNullable(response.parse());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  public static Optional<Document> getDocument(String url, Connection.Method method) {
    return getDocument(url, method, null);
  }
}
