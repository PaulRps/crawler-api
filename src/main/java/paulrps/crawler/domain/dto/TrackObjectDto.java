package paulrps.crawler.domain.dto;

import lombok.Data;
import paulrps.crawler.domain.entity.TrackObject;

@Data
public class TrackObjectDto extends TrackObject {
  private String userEmail;
}
