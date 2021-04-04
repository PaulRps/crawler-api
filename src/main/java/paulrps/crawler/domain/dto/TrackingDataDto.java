package paulrps.crawler.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackingDataDto {
  private String trackingCode;
  private List<TrackingEventDto> events;
}
