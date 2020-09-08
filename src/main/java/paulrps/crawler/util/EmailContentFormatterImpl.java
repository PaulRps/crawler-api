package paulrps.crawler.util;

import java.util.List;
import org.springframework.stereotype.Service;
import paulrps.crawler.domain.dto.WebPageDataDto;

@Service
public class EmailContentFormatterImpl implements paulrps.crawler.util.EmailContentFormatter {
  @Override
  public String formatBody(List<WebPageDataDto> data) {
    // TODO: implement
    return "null";
  }
}
