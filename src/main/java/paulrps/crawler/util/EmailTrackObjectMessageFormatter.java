package paulrps.crawler.util;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import paulrps.crawler.domain.dto.TrackDataDto;
import paulrps.crawler.domain.enums.EmailTemplateEnum;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component("EmailTrackObjectMessageFormatter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailTrackObjectMessageFormatter {

  private final @NonNull SpringTemplateEngine templateEngine;

  public String formatBody(List<TrackDataDto> data) {
    Map<String, Object> thymeLeafContextData = new LinkedHashMap();
    thymeLeafContextData.put("track_objects", data);

    Context context = new Context();
    context.setVariables(thymeLeafContextData);

    String html = templateEngine.process(EmailTemplateEnum.TRACK_OBJECT.getName(), context);

    return html;
  }
}
