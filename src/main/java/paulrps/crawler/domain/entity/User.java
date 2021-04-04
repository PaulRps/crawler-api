package paulrps.crawler.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Comparator;
import java.util.List;

@Data
@Builder
@Document
public class User {
  @Id private String id;
  private String name;
  private String email;
  private List<Integer> webPages;
  private List<String> jobKeyWords;
  private Boolean isActive = true;

  public static final Comparator<User> compareById = (u1, u2) -> u1.getId().compareTo(u2.getId());
  public static final Comparator<User> compareByEmail =
      (u1, u2) -> u1.getEmail().compareTo(u2.getEmail());
}
