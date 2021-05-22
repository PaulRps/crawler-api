package paulrps.crawler.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paulrps.crawler.domain.dto.UserJobFilterDto;
import paulrps.crawler.domain.entity.UserJobFilter;
import paulrps.crawler.services.UserJobFilterService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/job-filter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JobFiltersController {

  private final @NonNull UserJobFilterService userJobFilterService;

  @GetMapping
  public ResponseEntity<List<UserJobFilter>> getAll() {
    return ResponseEntity.ok(userJobFilterService.findAll());
  }

  @GetMapping("user/{email}")
  public ResponseEntity<UserJobFilter> getByUserEmail(@PathVariable String email) {
    return ResponseEntity.ok(userJobFilterService.findByUserEmail(email));
  }

  @PostMapping
  public ResponseEntity<UserJobFilter> create(@RequestBody @Valid UserJobFilterDto dto) {
    return new ResponseEntity(userJobFilterService.save(dto), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<Void> update(@RequestBody @Valid UserJobFilterDto userJobFilterDto) {
    userJobFilterService.update(userJobFilterDto);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}
