package paulrps.crawler.util;

import paulrps.crawler.domain.dto.NotificationMessageDto;
import paulrps.crawler.domain.entity.User;

public abstract class JobNotifier {
  protected MessageFormatter messageFormatter;

  public abstract void sendTo(User user, NotificationMessageDto message);
  public abstract void sendTo(User user, String subject, String message);
}
