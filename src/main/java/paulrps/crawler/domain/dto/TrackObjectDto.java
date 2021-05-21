package paulrps.crawler.domain.dto;

import lombok.Data;
import paulrps.crawler.domain.enums.CarrierEnum;

import javax.validation.constraints.NotBlank;

@Data
public class TrackObjectDto {
  @NotBlank(message = "track code is required")
  private String trackCode;

  private String description;

  @NotBlank(message = "user email is required")
  private String userEmail;

  @NotBlank(message = "carrier field is required")
  private CarrierEnum carrier;
}
