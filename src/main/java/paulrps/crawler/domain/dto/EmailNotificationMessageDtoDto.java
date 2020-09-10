package paulrps.crawler.domain.dto;

import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class EmailNotificationMessageDtoDto extends NotificationMessageDto {
  private String subject;

  @Builder
  public EmailNotificationMessageDtoDto(final String subject, final List<WebPageDataDto> body) {
    this.subject = subject;
    this.body = body;
  }
}
