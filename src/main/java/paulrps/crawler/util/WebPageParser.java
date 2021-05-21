package paulrps.crawler.util;

import java.util.List;

public interface WebPageParser<T> {

  <T extends Object> List<T> parseData(String url);
}
