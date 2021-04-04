package paulrps.crawler.services;

public interface NotifyService {
  void notifyAllUsers();

  void notifyByUserEmail(String email);

  void notifyAllUsersTrack();
}
