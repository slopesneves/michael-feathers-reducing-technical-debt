package homeguard;

public class MotionSensor extends Sensor{

  public MotionSensor(String id, String location) {
    super(id, location, Sensor.MOTION);
  }

  public String getType() {
    return Sensor.MOTION;
  }
}
