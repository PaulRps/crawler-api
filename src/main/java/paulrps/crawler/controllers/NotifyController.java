package paulrps.crawler.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import paulrps.crawler.services.NotifyService;

@Slf4j
@RestController
@RequestMapping("notify")
public class NotifyController {
  private NotifyService notifyService;

  @Autowired
  public NotifyController(NotifyService notifyService) {
    this.notifyService = notifyService;
  }

  @PutMapping(value = "all")
  public ResponseEntity<Void> notifyAllUser() {
    log.info("SENDING JOB OPENINGS NOTIFICATION TO ALL USERS");
    notifyService.notifyAllUsers();
    log.info("SENT JOB OPENINGS NOTIFICATION TO ALL USERS");
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping(value = "by-user/{email}")
  public ResponseEntity<Void> notifyUser(@PathVariable String email) {
    log.info("SENDING JOB OPENINGS NOTIFICATION TO {}", email);
    notifyService.notifyByUserEmail(email);
    log.info("SENT JOB OPENINGS NOTIFICATION TO {}", email);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
