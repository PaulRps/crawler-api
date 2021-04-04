package paulrps.crawler.util;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import paulrps.crawler.domain.dto.TrackingDataDto;
import paulrps.crawler.domain.dto.TrackingEventDto;
import paulrps.crawler.domain.entity.TrackedObject;
import paulrps.crawler.services.TrackingService;

import java.util.*;

@Setter
@Getter
public class CorreiosWebPageParser implements WebPageParser<TrackingDataDto>, TrackingService {

  private List<TrackedObject> trackedObjects;

  public CorreiosWebPageParser() {
    super();
  }

  @Override
  public List<TrackingDataDto> parseData(String url) {
    Optional.ofNullable(trackedObjects)
        .orElseThrow(() -> new RuntimeException("tracked objects field is null"));

    Map<String, String> data = new TreeMap<>();
    data.put("acao", "track");
    data.put("btnPesq", "Buscar");

    List<TrackingDataDto> result = new ArrayList<>();

    trackedObjects.forEach(
        obj -> {
          data.put("objetos", obj.getTrackingCode());
          result.add(
              TrackingDataDto.builder()
                  .trackingCode(obj.getTrackingCode())
                  .events(parseByCode(url, data))
                  .build());
        });

    return result;
  }

  private List<TrackingEventDto> parseByCode(String url, Map<String, String> data) {
    Optional<Document> document = WebPageScraper.getDocument(url, Connection.Method.POST, data);
    List<TrackingEventDto> result = new ArrayList<>();
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

  private TrackingEventDto getWebPageData(Elements row) {
    String text = row.text();
    return TrackingEventDto.builder()
        .dtEvent(text.substring(0, 16))
        .description(text.substring(16))
        .build();
  }
}
