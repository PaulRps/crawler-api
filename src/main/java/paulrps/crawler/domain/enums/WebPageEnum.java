package paulrps.crawler.domain.enums;

import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum WebPageEnum {
  GITHUB_BACKEND_ISSUES(1, "https://github.com/backend-br/vagas/issues");

  private Integer id;
  private String url;

  private WebPageEnum(final Integer id, final String url) {
    this.id = id;
    this.url = url;
  }

  public static WebPageEnum getOne(final Integer id) {
    return Stream.of(WebPageEnum.values())
        .filter(wpe -> wpe.id.equals(id))
        .findFirst()
        .orElseThrow(() -> new RuntimeException(String.format("WebPageEnum (%d) not found", id)));
  }
}
