
package scheduler;


public class MailServiceImpl implements MailService {
	private static MailServiceImpl instance;
	
    private MailServiceImpl() {
    }

  static MailServiceImpl getInstance() {
    if (MailServiceImpl.instance == null)
      MailServiceImpl.instance = new MailServiceImpl();
    return MailServiceImpl.instance;
  }

  @Override
  public void sendMail(String address, String subject, String message) {
		// this method really sends mail
	}
}
