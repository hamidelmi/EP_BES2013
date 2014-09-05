package eplab.anfragen;

public class AccumulativeIntensityEvent {
	private int minutes;
	private long ts_start;
	private long ts_stop;
	private String player_id;
	private int intensity;
	private long distance;
	private long speed;

	public String toString() {
		String sEventString = this.getTs_start() + "," + this.getTs_stop()
				+ "," + this.getPlayer_id() + "," + this.getIntensity() + ","
				+ this.getDistance() + "," + this.getSpeed();

		return sEventString;
	}

	public long getTs_start() {
		return ts_start;
	}

	public void setTs_start(long ts_start) {
		this.ts_start = ts_start;
	}

	public long getTs_stop() {
		return ts_stop;
	}

	public void setTs_stop(long ts_stop) {
		this.ts_stop = ts_stop;
		this.minutes = (int) (AccumulativeIntensity.TimeToSecond(ts_stop) / 60);
	}

	public String getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(String player_id) {
		this.player_id = player_id;
	}

	public int getIntensity() {
		return intensity;
	}

	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public long getSpeed() {
		return speed;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
	}

	public int getMinutes1() {
		return minutes;
	}

	public void setMinutes1(int minute) {
		this.minutes = minute;
	}
}
