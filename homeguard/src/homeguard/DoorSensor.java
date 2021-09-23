package homeguard;

public class DoorSensor extends Sensor{

  public DoorSensor(String id, String location) {
    super(id, location, Sensor.DOOR);
  }

  public String getType() {
    return Sensor.DOOR;
  }
}
