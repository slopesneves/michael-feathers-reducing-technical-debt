/*
 * Created by IntelliJ IDEA.
 * User: bbutton
 * Date: Aug 1, 2002
 * Time: 9:34:33 AM
 * To change template for new class use 
 * Code Style | Class Templates options (Tools | IDE Options).
 */

import java.io.IOException;
import java.io.OutputStream;

public class AddEmployeeCmd extends Command {
    String name;
    String address;
    String city;
    String state;
    String yearlySalary;

    private static final byte[] commandChar = {0x02};

    public AddEmployeeCmd(String name, String address, String city, String state, int yearlySalary) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.yearlySalary = Integer.toString(yearlySalary);
    }

    @Override
    protected byte[] getCommandChar() {
        return AddEmployeeCmd.commandChar;
    }

    @Override
    protected String[] getFields() {

        return new String[]{name, address, city, state, yearlySalary};
    }

}

