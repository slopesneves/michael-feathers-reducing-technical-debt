package homeguard;

import java.util.Iterator;
import java.util.Map;

public class Diagnostics {


  public Map getSensorTestStatusMap() {
    return sensorTestStatusMap;
  }

  public Map sensorTestStatusMap;
  public boolean runningSensorTest;

  public String getSensorTestStatus() {
    return sensorTestStatus;
  }

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

  void adjustStatus(String id, String status, CentralUnit centralUnit) {
    if(runningSensorTest)
    {
      if("TRIPPED".equals(status))
      {
        sensorTestStatusMap.put(id, CentralUnit.PASS);
      }

      // check to see if test is complete
      boolean done = true;
      for(Iterator iterator = sensorTestStatusMap.values().iterator(); iterator.hasNext();)
      {
        String testStatus = (String) iterator.next();
        if(CentralUnit.PENDING.equals(testStatus))
        {
          done = false;
          break;
        }
      }

      //terminate test if complete
      if(done)
        centralUnit.terminateSensorTest();
    }
  }
}
