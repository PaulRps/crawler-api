package paulrps.crawler.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import paulrps.crawler.domain.dto.WebPageDataDto;
import paulrps.crawler.domain.entity.User;
import paulrps.crawler.services.JobService;

@RestController
@RequestMapping(value = "jobs")
public class JobController {

  private JobService jobService;

  @Autowired
  JobController(final JobService jobService) {
    this.jobService = jobService;
  }

  @GetMapping
  public ResponseEntity<Map<User, List<WebPageDataDto>>> getAll() {
    return ResponseEntity.ok(jobService.getAll());
  }

  @GetMapping(value = "by-user/{email}")
  public ResponseEntity<List<WebPageDataDto>> getByUserEmail(@PathVariable String email) {
    return ResponseEntity.ok(jobService.getByUserEmail(email));
  }
}
