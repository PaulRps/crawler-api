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

  @PutMapping(value = "all")
  public ResponseEntity<Void> notifyAllUser() {
    notifyService.notifyAllUsers();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping(value = "user/{email}")
  public ResponseEntity<Void> notifyUser(@PathVariable String email) {
    notifyService.notifyByUserEmail(email);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("track-object")
  public ResponseEntity<Void> notifyAllUsers() {
    notifyService.notifyAllUsersTrack();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
