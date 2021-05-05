package paulrps.crawler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paulrps.crawler.domain.entity.User;
import paulrps.crawler.services.UserService;

import java.util.List;

@RestController
@RequestMapping("v1/user")
class UserController {
  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<User> create(@RequestBody User user) {
    return new ResponseEntity(userService.save(user), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<Void> update(@RequestBody User user) {
    userService.save(user);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("{email}")
  public ResponseEntity<Void> delete(@PathVariable String email) {
    userService.delete(email);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @GetMapping
  public ResponseEntity<List<User>> getAll(){
    return new ResponseEntity(userService.findAll(), HttpStatus.OK);
  }
}
