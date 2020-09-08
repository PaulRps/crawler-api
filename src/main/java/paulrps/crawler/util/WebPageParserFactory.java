package paulrps.crawler.util;

import paulrps.crawler.domain.enums.WebPageEnum;

public class WebPageParserFactory {
  private WebPageParserFactory() {}

  public static paulrps.crawler.util.WebPageParser getOne(WebPageEnum webPageEnum) {
    if (WebPageEnum.GITHUB_BACKEND_ISSUES.equals(webPageEnum)) {

      return new paulrps.crawler.util.GitHubWebPageParser(webPageEnum);
    }

    return null;
  }
}
