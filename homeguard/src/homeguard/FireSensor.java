package homeguard;

public class FireSensor extends Sensor{

  public FireSensor(String id, String location) {
    super(id, location, Sensor.FIRE);
  }

  public String getType() {
    return Sensor.FIRE;
  }
}
