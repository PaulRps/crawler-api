package paulrps.crawler.util;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import paulrps.crawler.domain.dto.GitHubIssuePageDto;
import paulrps.crawler.domain.dto.WebPageDataDto;
import paulrps.crawler.domain.enums.EmailTemplateEnum;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("EmailJobMessageFormatter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailJobMessageFormatter implements MessageFormatter {

  private final @NonNull SpringTemplateEngine templateEngine;

  @Override
  public String formatBody(List<WebPageDataDto> data) {
    Map<String, Object> thymeLeafContextData = new LinkedHashMap();
    thymeLeafContextData.put("job_openings", data);

    Context context = new Context();
    context.setVariables(thymeLeafContextData);

    String html = templateEngine.process(EmailTemplateEnum.JOB_OPENING.getName(), context);

    return html;
  }

  private String old(List<WebPageDataDto> data) {
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
