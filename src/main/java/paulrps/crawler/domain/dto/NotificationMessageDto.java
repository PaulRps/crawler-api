package paulrps.crawler.domain.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class NotificationMessageDto {
  protected List<WebPageDataDto> body;
}
