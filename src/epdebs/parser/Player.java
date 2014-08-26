package epdebs.parser;

public class Player extends SensoredEntity {
	protected CurrentIntensity currentIntesity;

	public Player(int[] sensorIDs, String sName, int iPlayerType) {
		super(sensorIDs, sName, iPlayerType);
	}

	public void setCurrentIntesity(CurrentIntensity curInt) {
		this.currentIntesity = curInt;
	}

	public CurrentIntensity getCurrentIntensity() {
		return this.currentIntesity;
	}
}
