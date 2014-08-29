package epdebs.game_objects;

public class AccumulativeIntensity {
	public long ts;
	public long lastIntTs;
	public int intensity;
	public long v;
	public boolean active;

	public static AccumulativeIntensityEvent Instantiate(int sensorId, long ts,
			long abs_v, int instanceIntensity) {
		Game game = Game.Singleton();
		AccumulativeIntensityEvent result = new AccumulativeIntensityEvent();
		AccumulativeIntensity current = game.getIntensity(sensorId);
		if (current == null)
			current = new AccumulativeIntensity();

		if (current.active || IsMoreThanASecond(current.lastIntTs, ts)) {
			long speed = current.v;
			long delta = ts - current.ts;
			long distance = GetDistance(speed, delta);

			result.setTs_start(current.ts);
			result.setTs_stop(ts);
			result.setPlayer_id(sensorId);
			result.setIntensity(current.intensity);
			result.setDistance(distance);
			result.setSpeed(speed);

			current.v = abs_v;

			current.active = current.intensity == instanceIntensity;
			current.ts = ts;
			current.v = abs_v;
			current.intensity = instanceIntensity;
		} else if (current.intensity != instanceIntensity) {
			current.lastIntTs = ts;
		}
		return result;
	}

	public static long GetDistance(long velocity, long lTimeDelta) {
		return velocity * (lTimeDelta / 1000000000L);
	}

	public static Double ElapsedSeconds(long tsStart, long tsEnd) {
		double seconds = (tsEnd - tsStart) * Math.pow(10.0D, -12.0D);

		return new Double(seconds);
	}

	public static boolean IsMoreThanASecond(long lStart, long lCur) {
		if (ElapsedSeconds(lStart, lCur).doubleValue() > 1.0D) {
			return true;
		}

		return false;
	}
}
