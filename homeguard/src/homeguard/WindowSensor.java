package homeguard;

public class WindowSensor extends Sensor{

  public WindowSensor(String id, String location) {
    super(id, location, Sensor.WINDOW);
  }

  public String getType() {
    return Sensor.WINDOW;
  }

  @Override
  public String getMessage() {
    return getLocation() + " is " + tripMessagePart();
  }

  private String tripMessagePart() {
    return this.isTripped() ? "ajar" : "sealed";
  }
}
