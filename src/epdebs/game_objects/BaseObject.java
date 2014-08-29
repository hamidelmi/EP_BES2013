package epdebs.game_objects;

public class BaseObject {
	protected int[] sensorId;
	protected String name;

	public BaseObject(int[] sensorId, String name) {
		this.sensorId = sensorId;
		this.name = name;
	}

	public int[] getSensorId() {
		return this.sensorId;
	}

	public String getName() {
		return this.name;
	}

	public boolean hasSensorId(int id) {
		for (int item : sensorId)
			if (id == item)
				return true;
		return false;
	}
}
