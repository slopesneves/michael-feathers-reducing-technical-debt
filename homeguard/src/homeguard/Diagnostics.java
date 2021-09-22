package homeguard;

import java.util.Iterator;
import java.util.Map;

public class Diagnostics {


  public Map sensorTestStatusMap;
  public boolean runningSensorTest;
  public String sensorTestStatus;

  public void terminate() {
    runningSensorTest = false;

    // look at individual sensor status to determine the overall test status
    sensorTestStatus = CentralUnit.PASS;
    for(Iterator iterator = sensorTestStatusMap.values().iterator(); iterator.hasNext();)
    {
      String status = (String) iterator.next();
      if(status.equals(CentralUnit.PENDING))
      {
        sensorTestStatus = CentralUnit.FAIL;
        break;
      }
    }
  }
}
