package paulrps.crawler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paulrps.crawler.domain.dto.TrackingDataDto;
import paulrps.crawler.domain.entity.TrackedObject;
import paulrps.crawler.services.TrackedObjectService;

import java.util.List;

@RestController
@RequestMapping("tracking-object")
public class TrackingObjectController {

  private TrackedObjectService trackedObjectService;

  @Autowired
  public TrackingObjectController(TrackedObjectService trackedObjectService) {
    this.trackedObjectService = trackedObjectService;
  }

  @PostMapping
  public ResponseEntity<TrackedObject> save(TrackedObject trackedObject) {
    return new ResponseEntity(trackedObjectService.save(trackedObject), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<Void> update(TrackedObject trackedObject) {
    trackedObjectService.update(trackedObject);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    trackedObjectService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("user/{email}")
  public ResponseEntity<List<TrackingDataDto>> parseByUserEmail(@PathVariable String email) {
    return ResponseEntity.ok(trackedObjectService.parseByUserEmail(email));
  }
}
