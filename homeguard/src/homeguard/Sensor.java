package homeguard;

public class Sensor
{
	public static final String DOOR = "door";
	public static final String MOTION = "motion";
	public static final String WINDOW = "window";
	public static final String FIRE = "fire";

	private String id;
	private String location;
	private String type;
	private boolean tripped = false;

	public Sensor(String id, String location, String type)
	{
		this.id = id;
		this.location = location;
		this.type = type;
	}

	public String getId()
	{
		return id;
	}

	public String getType()
	{
		return type;
	}

	public String getLocation()
	{
		return location;
	}

	private void trip()
	{
		tripped = true;
	}

	private void reset()
	{
		tripped = false;
	}

	public boolean isTripped()
	{
		return tripped;
	}

	public String getMessage()
	{
		String message = "default";
		if(!isTripped())
		{
			if(getType().equals(WINDOW))
				return getLocation() + " is sealed";
			else if(getType().equals(MOTION))
				return getLocation() + " is motionless";
			else if(getType().equals(FIRE))
				return getLocation() + " temperature is normal";
		}
		else
		{
			if(getType().equals(WINDOW))
				return getLocation() + " is ajar";
			else if(getType().equals(MOTION))
				return "Motion detected in " + getLocation();
			else if(getType().equals(FIRE))
				return getLocation() + " is on FIRE!";
		}
		return message;
	}

	void adjustStatus(String status) {
		if("TRIPPED".equals(status))
			trip();
		else
			reset();
	}
}
