package paulrps.crawler.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paulrps.crawler.domain.dto.UserDto;
import paulrps.crawler.domain.entity.User;
import paulrps.crawler.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UserController {
  private final @NonNull UserService userService;

  @PostMapping
  public ResponseEntity<User> create(@RequestBody @Valid UserDto user) {
    return new ResponseEntity(userService.save(user), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<Void> update(@RequestBody @Valid UserDto user) {
    userService.update(user);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("{email}")
  public ResponseEntity<Void> delete(@PathVariable String email) {
    userService.delete(email);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @GetMapping
  public ResponseEntity<List<User>> getAll() {
    return new ResponseEntity(userService.findAll(), HttpStatus.OK);
  }
}
