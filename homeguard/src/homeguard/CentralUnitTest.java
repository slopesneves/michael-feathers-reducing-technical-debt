package homeguard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CentralUnitTest {

  public static final String PACKET_DELIMETER = ",";

  @Test
  public void should_trip_sensor_alarm_on_receiving_tripped_sensor_packet() {
    //given
    CentralUnit centralUnit = new CentralUnit();
    final String sensorId = "42";
    final String sensorStatus = "TRIPPED";

    centralUnit.registerSensor(new Sensor(sensorId, "California", "dunno"));
    final String packet = String.join(PACKET_DELIMETER, sensorId, sensorStatus);

    //when
    centralUnit.parseRadioBroadcast(packet);

    //then
    final Sensor sensorFromCentralUnit = findSensorById(centralUnit, sensorId);
    Assertions.assertTrue(sensorFromCentralUnit.isTripped());

  }



  private Sensor findSensorById(CentralUnit centralUnit, String sensorId) {
    return centralUnit.getSensors()
        .stream()
        .filter(s -> sensorId.equals(s.getId()))
        .findFirst()
        .orElseThrow(() -> {
          final String message = "sensor id " + sensorId + " does not exists";
          Assertions.fail(message);
          return new IllegalStateException(message);
        });
  }

}
