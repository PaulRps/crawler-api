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
  private List<TrackEventDto> events;
}
