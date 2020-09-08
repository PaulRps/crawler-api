package paulrps.crawler.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paulrps.crawler.domain.dto.WebPageDataDto;
import paulrps.crawler.services.JobService;

@RestController
@RequestMapping(value = "jobs")
public class JobController {

  private JobService jobService;

  @Autowired
  JobController(final JobService jobService) {
    this.jobService = jobService;
  }

  @GetMapping(value = "/{userEmail}")
  public ResponseEntity<List<WebPageDataDto>> getJobs(@PathVariable String userEmail) {
    return ResponseEntity.ok(jobService.getFilteredJobs(userEmail));
  }

  @PostMapping(value = "notify-all")
  public ResponseEntity<Void> notifyAllUser() {
    jobService.notifyAllUsers();
    return ResponseEntity.ok().build();
  }
}
