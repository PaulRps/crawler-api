package paulrps.crawler.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class TrackObject {
  @Id private String id;
  private String userId;
  private String trackCode;
  private Integer sender;
  private Long lastEvents;
  private Boolean isActive = true;

  public boolean hasChanged(Long current) {
    return current > lastEvents;
  }
}
