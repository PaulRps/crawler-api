package paulrps.crawler.services;

import paulrps.crawler.domain.dto.TrackDataDto;
import paulrps.crawler.domain.dto.TrackObjectDto;
import paulrps.crawler.domain.entity.TrackObject;

import java.util.List;

public interface TrackObjectService {

  TrackObject save(TrackObjectDto trackObject);

  TrackObject update(TrackObjectDto trackObjectDto);

  void inactivate(String trackCode);

  void delete(String id);

  List<TrackDataDto> getTrackObjByUserEmail(String email);
}
