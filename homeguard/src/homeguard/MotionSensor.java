package homeguard;

public class MotionSensor extends Sensor{

  public MotionSensor(String id, String location) {
    super(id, location, Sensor.MOTION);
  }

  public String getType() {
    return Sensor.MOTION;
  }

  @Override
  String tripMessagePart() {
    //TODO implement case
    return null;
  }
}
