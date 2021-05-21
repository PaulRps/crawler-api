package paulrps.crawler.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private String name;

  @NotBlank(message = "user email is required")
  private String email;
}
