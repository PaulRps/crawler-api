package paulrps.crawler.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import paulrps.crawler.services.NotifyService;

@RestController
@RequestMapping("v1/notify")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotifyController {
  @NonNull private final NotifyService notifyService;

  @PutMapping(value = "job-opening/user/all")
  public ResponseEntity<Void> notifyAllUser() {
    notifyService.notifyJobsAllUsers();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping(value = "job-opening/user/{email}")
  public ResponseEntity<Void> notifyUser(@PathVariable String email) {
    notifyService.notifyJobsByUserEmail(email);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("track-object/user/all")
  public ResponseEntity<Void> notifyAllUsers() {
    notifyService.notifyTrackObjectAllUsers();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
