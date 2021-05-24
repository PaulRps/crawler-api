package paulrps.crawler.services;

public interface NotifyService {
  void notifyJobsAllUsers();

  void notifyJobsByUserEmail(String email);

  void notifyTrackObjectAllUsers();
}
