/*
 * Created by IntelliJ IDEA.
 * User: bbutton
 * Date: Jul 31, 2002
 * Time: 11:30:29 PM
 * To change template for new class use
 * Code Style | Class Templates options (Tools | IDE Options).
 */

public class LoginCommand extends Command {

    private String userName;
    private String passwd;
    private static final byte[] commandChar = {0x01};

    public LoginCommand(String userName, String passwd) {
        this.userName = userName;
        this.passwd = passwd;
    }

    @Override
    protected byte[] getCommandChar() {
        return LoginCommand.commandChar;
    }

    @Override
    protected String[] getFields() {
        return new String[]{userName, passwd};
    }
}


