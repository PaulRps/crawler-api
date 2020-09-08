package paulrps.crawler.domain.entity;

import java.util.Comparator;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class User {
  @Id private String id;
  private String name;
  private String email;
  private List<String> webPages;
  private List<String> jobKeyWords;

  public static final Comparator<User> compareById = (u1, u2) -> u1.getId().compareTo(u2.getId());
  public static final Comparator<User> compareByEmail =
      (u1, u2) -> u1.getEmail().compareTo(u2.getEmail());
}
