package paulrps.crawler.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Map;

public abstract class WebPageDataDto {
  @JsonIgnore
  public abstract Map<String, String> getDataMap();
}
