package homeguard;

import java.util.*;

public class CentralUnit
{
	// sensor test status options
	public static final String PASS = "PASS";
	public static final String FAIL = "FAIL";
	public static final String PENDING = "pending";
	public static final String READY = "ready";

	private boolean armed = false;
	private String securityCode;
	private List sensors = new LinkedList();
	private HomeGuardView view = new TextView();
	private AudibleAlarm audibleAlarm = new TextAudibleAlarm();

	// members to help with sensor tests
	private Diagnostics diagnostics = new Diagnostics();

	public boolean isArmed()
	{
		return armed;
	}

	public void arm()
	{
		armed = true;
	}

	public void setSecurityCode(String code)
	{
		securityCode = code;
	}

	public boolean isValidCode(String code)
	{
		return securityCode.equals(code);
	}

	public void enterCode(String code)
	{
		if(isValidCode(code))
		{
			armed = false;
			audibleAlarm.silence();
		}
	}

	public boolean audibleAlarmIsOn()
	{
		return false;
	}

	public List getSensors()
	{
		return sensors;
	}

	public void registerSensor(Sensor sensor)
	{
		sensors.add(sensor);
	}

	public void setView(HomeGuardView view)
	{
		this.view = view;
	}

	public void setAudibleAlarm(AudibleAlarm alarm)
	{
		audibleAlarm = alarm;
	}

	public void parseRadioBroadcast(String packet)
	{
		//parse the packet
		String[] tokens = packet.split(",");
		String id = tokens[0];
		String status = tokens[1];

		// find sensor with id
		Sensor sensor = null;
		for(Iterator iterator = sensors.iterator(); iterator.hasNext();)
		{
			Sensor s = (Sensor) iterator.next();
			if(s.getId().equals(id))
			{
				sensor = s;
				break;
			}
		}

		//trip or reset sensor
		if(sensor != null)
		{
			if("TRIPPED".equals(status))
				sensor.trip();
			else
				sensor.reset();
		}

		//get the message from the sensor and display it
		String message = getSensorMessage(sensor);
		view.showMessage(message);

		// sound the alarm if armed
		if(isArmed())
			audibleAlarm.sound();

		// check if a sensor test is running and adjust status
		if(diagnostics.runningSensorTest)
		{
			if("TRIPPED".equals(status))
			{
				diagnostics.sensorTestStatusMap.put(id, PASS);
			}

			// check to see if test is complete
			boolean done = true;
			for(Iterator iterator = diagnostics.sensorTestStatusMap.values().iterator(); iterator.hasNext();)
			{
				String testStatus = (String) iterator.next();
				if(PENDING.equals(testStatus))
				{
					done = false;
					break;
				}
			}

			//terminate test if complete
			if(done)
				terminateSensorTest();
		}
	}

	public void runSensorTest()
	{
		diagnostics.runningSensorTest = true;
		diagnostics.sensorTestStatus = PENDING;

		// initialize the status map
		diagnostics.sensorTestStatusMap = new HashMap();
		for(Iterator iterator = sensors.iterator(); iterator.hasNext();)
		{
			Sensor sensor = (Sensor) iterator.next();
			diagnostics.sensorTestStatusMap.put(sensor.getId(), PENDING);
		}
	}

	// used during sensor test
	public void terminateSensorTest()
	{
		diagnostics.terminate();
	}

	// used during sensor test
	public String getSesnsorTestStatus()
	{
		return diagnostics.sensorTestStatus;
	}

	// used during sensor test
	public Map getSensorTestStatusMap()
	{
		return diagnostics.sensorTestStatusMap;
	}

	public String getSensorMessage(Sensor sensor)
	{
		String message = "default";
		if(!sensor.isTripped())
		{
			if(sensor.getType().equals(Sensor.DOOR))
				return sensor.getLocation() + " is closed";
			else if(sensor.getType().equals(Sensor.WINDOW))
				return sensor.getLocation() + " is sealed";
			else if(sensor.getType().equals(Sensor.MOTION))
				return sensor.getLocation() + " is motionless";
			else if(sensor.getType().equals(Sensor.FIRE))
				return sensor.getLocation() + " temperature is normal";
		}
		else
		{
			if(sensor.getType().equals(Sensor.DOOR))
				return sensor.getLocation() + " is open";
			else if(sensor.getType().equals(Sensor.WINDOW))
				return sensor.getLocation() + " is ajar";
			else if(sensor.getType().equals(Sensor.MOTION))
				return "Motion detected in " + sensor.getLocation();
			else if(sensor.getType().equals(Sensor.FIRE))
				return sensor.getLocation() + " is on FIRE!";
		}
		return message;
	}
}
