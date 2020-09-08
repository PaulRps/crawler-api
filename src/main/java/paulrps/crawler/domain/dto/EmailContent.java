package paulrps.crawler.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmailContent {
  private String subject;
  private String body;
}
