package paulrps.crawler.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobOpeningSourceEnum {
  GITHUB(ParserTypeEnum.GITHUB_BACKEND_ISSUES.getId());
  private Integer id;
}
