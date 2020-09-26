package paulrps.crawler.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import paulrps.crawler.domain.dto.GitHubIssuePageDto;
import paulrps.crawler.domain.dto.WebPageDataDto;

@Component("EmailMessageFormatter")
public class EmailMessageFormatter implements MessageFormatter {
  @Override
  public String formatBody(List<WebPageDataDto> data) {
    StringBuilder body = new StringBuilder("Job Openings Summary:");
    body.append(Constants.LINE_SEPARATOR);
    body.append(Constants.LINE_SEPARATOR);

    data.stream()
        .map(d -> (GitHubIssuePageDto) d)
        .filter(
            d ->
                Objects.nonNull(d.getTitle())
                    && Objects.nonNull(d.getLabels())
                    && Objects.nonNull(d.getUrl()))
        .forEach(
            d ->
                body.append(String.format("%s (%s)", d.getTitle(), d.getUrl()))
                    .append(Constants.LINE_SEPARATOR)
                    .append("Labels: ")
                    .append(d.getLabels().stream().collect(Collectors.joining(", ")))
                    .append(Constants.LINE_SEPARATOR)
                    .append(String.format("Published on %s", d.getDateCreation()))
                    .append(Constants.LINE_SEPARATOR)
                    .append(Constants.LINE_SEPARATOR));
    return body.toString();
  }
}
