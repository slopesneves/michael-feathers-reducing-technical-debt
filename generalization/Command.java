import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public abstract class Command {

  static final byte[] header = {(byte)0xde, (byte)0xad};
  static final int SIZE_LENGTH = 1;
  static final int CMD_BYTE_LENGTH = 1;
  static final byte[] footer = {(byte)0xbe, (byte)0xef};

  private int getSize() {
    return this.getHeader().length
        + SIZE_LENGTH + CMD_BYTE_LENGTH
        + footer.length
        + fieldsSize();
  }

  private int fieldsSize() {
    return Arrays.stream(this.getFields())
        .mapToInt(field -> field.length() + 1)
        .sum();
  }

  protected byte[] getHeader() {
    return header;
  }

  public final void write(OutputStream os) throws IOException {
    writeHeader(os);

    writeCommandFields(os);

    writeFooter(os);
  }

  private void writeHeader(OutputStream os) throws IOException {
    os.write(header);
    os.write(getSize());
    os.write(getCommandChar());
  }

  private void writeFooter(OutputStream os) throws IOException {
    os.write(footer);
  }

  private void writeCommandFields(OutputStream os) throws IOException {
    for(String field : getFields()) {
      os.write(field.getBytes());
      os.write(0x00);
    }
  }

  protected abstract byte[] getCommandChar();

  protected abstract String[] getFields();

}
