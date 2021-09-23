package homeguard;

public class DefaultSensor extends Sensor{

  public DefaultSensor(String id, String location, String type) {
    super(id, location, type);
  }

  public String getType() {
    return super.getType();
  }

  @Override
  public String getMessage() {
    return "default";
  }

  @Override
  String tripMessagePart() {
    return "";
  }
}
