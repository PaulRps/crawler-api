package paulrps.crawler.util;

import java.util.List;
import paulrps.crawler.domain.dto.WebPageDataDto;

public interface EmailContentFormatter {

  String formatBody(List<WebPageDataDto> data);
}
