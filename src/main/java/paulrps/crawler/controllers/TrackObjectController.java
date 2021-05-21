package paulrps.crawler.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paulrps.crawler.domain.dto.TrackDataDto;
import paulrps.crawler.domain.dto.TrackObjectDto;
import paulrps.crawler.domain.entity.TrackObject;
import paulrps.crawler.services.TrackObjectService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/track-object")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TrackObjectController {

  private final @NonNull TrackObjectService trackObjectService;

  @PostMapping
  public ResponseEntity<TrackObject> save(@RequestBody @Valid TrackObjectDto trackObject) {
    return new ResponseEntity(trackObjectService.save(trackObject), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<Void> update(@RequestBody @Valid TrackObjectDto trackObject) {
    trackObjectService.update(trackObject);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    trackObjectService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("user/{email}")
  public ResponseEntity<List<TrackDataDto>> getTrackObjByUserEmail(@PathVariable String email) {
    return ResponseEntity.ok(trackObjectService.getTrackObjByUserEmail(email));
  }

  @PutMapping("inactivate/{trackcode}")
  public ResponseEntity<Void> inactiveTrackObj(@PathVariable String trackcode) {
    trackObjectService.inactivate(trackcode);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
