package paulrps.crawler.domain.dto;

import lombok.Data;
import paulrps.crawler.domain.enums.CarrierEnum;

@Data
public class TrackObjectDto {
  private String trackCode;
  private String description;
  private String userEmail;
  private CarrierEnum carrier;
}
