package paulrps.crawler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import paulrps.crawler.services.NotifyService;

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
    notifyService.notifyAllUsers();
    return ResponseEntity.ok().build();
  }

  @PutMapping(value = "by-user/{email}")
  public ResponseEntity<Void> notifyUser(@PathVariable String email) {
    notifyService.notifyByUserEmail(email);
    return ResponseEntity.ok().build();
  }
}
