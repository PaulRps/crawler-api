package paulrps.crawler.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackingEventDto {
  private String dtEvent;
  private String description;
}
