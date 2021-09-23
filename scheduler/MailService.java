
package scheduler;


public class MailService {
	private static MailService instance;
	
    private MailService() {
    }

  static MailService getInstance() {
    if (MailService.instance == null)
      MailService.instance = new MailService();
    return MailService.instance;
  }

  public void sendMail(String address, String subject, String message) {
		// this method really sends mail
    System.out.println("oh you sent an email to" + address);
	}
}
