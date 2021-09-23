package scheduler;

public interface MailService {

  static MailServiceImpl getInstance() {
      return MailServiceImpl.getInstance();
  }

  void sendMail(String address, String subject, String message);
}
