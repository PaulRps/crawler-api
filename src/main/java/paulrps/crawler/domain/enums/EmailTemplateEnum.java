package paulrps.crawler.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailTemplateEnum {
  JOB_OPENING("job-openings"),
  TRACK_OBJECT("track-objects");
  private String name;
}
