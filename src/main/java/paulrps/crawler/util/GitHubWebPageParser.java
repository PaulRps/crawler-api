package paulrps.crawler.util;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import paulrps.crawler.domain.dto.GitHubIssuePageDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GitHubWebPageParser implements WebPageParser<GitHubIssuePageDto> {

  @Override
  public List<GitHubIssuePageDto> parseData(String url) {
    List<GitHubIssuePageDto> gitIssuesData = new ArrayList<>();
    WebPageScraper.getDocument(url, Connection.Method.GET)
        .ifPresent(
            doc -> {
              Elements issues = doc.select("[id^=issue_]");
              issues.stream()
                  .filter(issue -> issue.hasClass("js-issue-row"))
                  .forEach(issue -> gitIssuesData.add(getWebPageData(issue)));
            });
    return gitIssuesData;
  }

  private GitHubIssuePageDto getWebPageData(Element issue) {
    Elements title = issue.select("a[id^=issue_]");
    return GitHubIssuePageDto.builder()
        .title(title.text())
        .labels(getLabels(issue.select("span.labels")))
        .dateCreation(
            LocalDateTime.parse(
                    issue.select("relative-time").attr("datetime"), DateTimeFormatter.ISO_DATE_TIME)
                .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
        .url(title.attr("abs:href"))
        .description(getDescription(title.attr("abs:href")))
        .build();
  }

  private String getDescription(String issueUrl) {
    Optional<Document> issuePage = WebPageScraper.getDocument(issueUrl, Connection.Method.GET);
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
