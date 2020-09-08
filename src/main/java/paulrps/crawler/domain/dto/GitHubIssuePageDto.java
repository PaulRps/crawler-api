package paulrps.crawler.domain.dto;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GitHubIssuePageDto extends WebPageDataDto {
  private String title;
  private List<String> labels;
  private String dateCreation;
  private String url;
  private String description;

  @Override
  public Map<String, String> getDataMap() {
    Map<String, String> data = new TreeMap<>();
    Stream.of(title.split(" ")).forEach(str -> data.put(str.trim().toLowerCase(), str));
    labels.forEach(str -> data.put(str.trim().toLowerCase(), str));
    Stream.of(description.split(" ")).forEach(str -> data.put(str.trim().toLowerCase(), str));
    return data;
  }
}
