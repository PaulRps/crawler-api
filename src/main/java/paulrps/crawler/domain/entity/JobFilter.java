package paulrps.crawler.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document
public class JobFilter {
  @Id private String id;
  private String userId;
  private List<Integer> jobOpeningSources;
  private List<String> jobKeyWords;
  private Boolean isActive = true;
}
