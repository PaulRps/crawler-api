package paulrps.crawler.util;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import paulrps.crawler.domain.dto.TrackDataDto;
import paulrps.crawler.domain.dto.TrackEventDto;
import paulrps.crawler.domain.entity.TrackObject;
import paulrps.crawler.services.TrackingParseService;

import java.util.*;

@Setter
@Getter
public class CorreiosWebPageParser implements WebPageParser<TrackDataDto>, TrackingParseService {

  private List<TrackObject> trackObjects;

  public CorreiosWebPageParser() {
    super();
  }

  @Override
  public List<TrackDataDto> parseData(String url) {
    Optional.ofNullable(trackObjects)
        .orElseThrow(() -> new RuntimeException("tracked objects field is null"));

    Map<String, String> data = new TreeMap<>();
    data.put("acao", "track");
    data.put("btnPesq", "Buscar");

    List<TrackDataDto> result = new ArrayList<>();

    trackObjects.forEach(
        obj -> {
          data.put("objetos", obj.getTrackCode());
          result.add(
              TrackDataDto.builder()
                  .trackingCode(obj.getTrackCode())
                  .events(parseByCode(url, data))
                  .build());
        });

    return result;
  }

  private List<TrackEventDto> parseByCode(String url, Map<String, String> data) {
    Optional<Document> document = WebPageScraper.getDocument(url, Connection.Method.POST, data);
    List<TrackEventDto> result = new ArrayList<>();
    if (document.isPresent()) {
      Elements events = document.get().select("table.listEvent");
      events.forEach(
          e -> {
            Elements row = e.select("tr");
            result.add(getWebPageData(row));
          });
    }
    return result;
  }

  private TrackEventDto getWebPageData(Elements row) {
    String text = row.text();
    return TrackEventDto.builder()
        .dtEvent(text.substring(0, 16))
        .description(text.substring(16))
        .build();
  }
}
