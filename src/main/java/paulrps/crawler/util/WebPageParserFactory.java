package paulrps.crawler.util;

import paulrps.crawler.domain.enums.ParserTypeEnum;

public final class WebPageParserFactory {
  public static WebPageParser getInstance(ParserTypeEnum parserTypeEnum) {
    if (ParserTypeEnum.GITHUB_BACKEND_ISSUES.equals(parserTypeEnum)) {
      return new paulrps.crawler.util.GitHubWebPageParser();
    } else if (ParserTypeEnum.CORREIOS_TRACK_OBJ.equals(parserTypeEnum)) {
      return new CorreiosWebPageParser();
    }

    return null;
  }
}
