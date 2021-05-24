package paulrps.crawler.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paulrps.crawler.domain.dto.JobFilterDto;
import paulrps.crawler.domain.entity.JobFilter;
import paulrps.crawler.services.JobFilterService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/job-filter")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JobFiltersController {

  private final @NonNull JobFilterService jobFilterService;

  @GetMapping
  public ResponseEntity<List<JobFilter>> getAll() {
    return ResponseEntity.ok(jobFilterService.findAll());
  }

  @GetMapping("user/{email}")
  public ResponseEntity<JobFilter> getByUserEmail(@PathVariable String email) {
    return ResponseEntity.ok(jobFilterService.findByUserEmail(email));
  }

  @PostMapping
  public ResponseEntity<JobFilter> create(@RequestBody @Valid JobFilterDto dto) {
    return new ResponseEntity(jobFilterService.save(dto), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<Void> update(@RequestBody @Valid JobFilterDto jobFilterDto) {
    jobFilterService.update(jobFilterDto);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}
