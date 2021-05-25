package paulrps.crawler.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmailNotificationMessageDto extends NotificationMessageDto {
  private String subject;

  @Builder
  public EmailNotificationMessageDto(final String subject, final List<WebPageDataDto> body) {
    this.subject = subject;
    this.body = body;
  }
}
