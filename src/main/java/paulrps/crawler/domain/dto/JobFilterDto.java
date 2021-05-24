package paulrps.crawler.domain.dto;

import lombok.*;
import paulrps.crawler.domain.enums.JobOpeningSourceEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobFilterDto {
  @NotBlank(message = "user email is required")
  private String userEmail;

  @NotEmpty(message = "jobOpeningSources is empty, pass some value")
  private List<JobOpeningSourceEnum> jobOpeningSources;

  @NotEmpty(message = "jobKeyWords is empty, pass some value")
  private List<String> jobKeyWords;

  private Boolean isActive = true;
  private String id;
}
