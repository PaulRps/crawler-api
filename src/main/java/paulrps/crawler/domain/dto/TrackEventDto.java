package paulrps.crawler.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackEventDto {
  private String dtEvent;
  private String description;
}
