package eplab.bodenobjekte;

public class SensoredEntity {
	protected int[] sensorIds = null;
	protected String name = null;
	public static int iTypePlayer = 0;
	public static int iTypeGoalKeeper = 1;
	public static int iTypeBall = 2;
	public static int iTypeReferee = 3;
	protected long entityType;

	public SensoredEntity(int[] sensorIDs, String sName, int type) {
		this.sensorIds = sensorIDs;
		this.name = sName;
		this.entityType = type;
	}

	public int[] getSensorIds() {
		return this.sensorIds;
	}

	public String getName() {
		return this.name;
	}

	public long getEntityType() {
		return this.entityType;
	}
}
