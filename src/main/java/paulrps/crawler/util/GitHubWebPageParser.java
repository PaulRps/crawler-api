package paulrps.crawler.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import paulrps.crawler.domain.dto.GitHubIssuePageDto;
import paulrps.crawler.domain.dto.WebPageDataDto;
import paulrps.crawler.domain.enums.WebPageEnum;

public class GitHubWebPageParser extends paulrps.crawler.util.WebPageParser {

  public GitHubWebPageParser(WebPageEnum webPageEnum) {
    super();
    this.webPageEnum = webPageEnum;
  }

  @Override
  public List<WebPageDataDto> parseData() {
    List<WebPageDataDto> gitIssuesData = new ArrayList<>();
    getDocument(getPageUrl())
        .ifPresent(
            doc -> {
              Elements issues = doc.select("[id^=issue_]");
              issues.stream()
                  .filter(issue -> issue.hasClass("js-issue-row"))
                  .forEach(issue -> gitIssuesData.add(getWebPageData(issue)));
            });
    return gitIssuesData;
  }

  private WebPageDataDto getWebPageData(Element issue) {
    Elements title = issue.select("a[id^=issue_]");
    return GitHubIssuePageDto.builder()
        .title(title.text())
        .labels(getLabels(issue.select("span.labels")))
        .dateCreation(issue.select("relative-time").attr("datetime"))
        .url(title.attr("abs:href"))
        .description(getDescription(title.attr("abs:href")))
        .build();
  }

  private String getDescription(String issueUrl) {
    Optional<Document> issuePage = getDocument(issueUrl);
    String description = null;
    if (issuePage.isPresent()) {
      Elements descrip = issuePage.get().select("td.comment-body");
      description = descrip.text();
    }
    return description;
  }

  private List<String> getLabels(Elements labels) {
    return labels.select("a").stream().map(Element::text).collect(Collectors.toList());
  }
}
