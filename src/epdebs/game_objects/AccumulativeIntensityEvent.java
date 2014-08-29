package epdebs.game_objects;

public class AccumulativeIntensityEvent {
	private long ts_start;
	private long ts_stop;
	private int player_id;
	private int intensity;
	private long distance;
	private long speed;
	
	public String toString()
	  {
	    String sEventString = this.getTs_start() + "," + this.getTs_stop() + "," + this.getPlayer_id() + "," + this.getIntensity() + "," + this.getDistance() + "," + this.getSpeed();

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
	}

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
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
}
