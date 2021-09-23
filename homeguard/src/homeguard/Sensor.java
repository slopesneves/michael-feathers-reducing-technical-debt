package homeguard;

public abstract class Sensor
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
		return this.getLocation() + " is " + this.tripMessagePart();
	}

	abstract String tripMessagePart();

	void adjustStatus(String status) {
		if("TRIPPED".equals(status))
			trip();
		else
			reset();
	}
}
