package homeguard;

public class WindowSensor extends Sensor{

  public WindowSensor(String id, String location) {
    super(id, location, Sensor.WINDOW);
  }

  public String getType() {
    return Sensor.WINDOW;
  }


  protected String tripMessagePart() {
    return this.isTripped() ? "ajar" : "sealed";
  }
}
