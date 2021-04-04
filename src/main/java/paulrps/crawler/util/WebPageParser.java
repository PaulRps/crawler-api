package paulrps.crawler.util;

import java.util.List;

public interface WebPageParser<T> {

    List<T> parseData(String url);
}
