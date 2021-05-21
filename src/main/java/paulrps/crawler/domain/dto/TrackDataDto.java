package paulrps.crawler.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackDataDto {
  private String trackingCode;
  private String description;
  private List<TrackEventDto> events;
  private boolean hasChanged = false;
}
