package eplab.anfragen;

public class AccumulativeIntensity {
	public long ts;
	public long lastIntTs;
	public int intensity;
	public long v;
	public boolean active;

	public static AccumulativeIntensityEvent Instantiate(int sensorId, long ts,
			long abs_v) {
		int instanceIntensity = 5;

		if (abs_v < AccumulativeIntensity.SpeedToV(1))
			instanceIntensity = 0;
		else if (abs_v < AccumulativeIntensity.SpeedToV(11))
			instanceIntensity = 1;
		else if (abs_v < AccumulativeIntensity.SpeedToV(13))
			instanceIntensity = 2;
		else if (abs_v < AccumulativeIntensity.SpeedToV(17))
			instanceIntensity = 3;
		else if (abs_v < AccumulativeIntensity.SpeedToV(24))
			instanceIntensity = 5;
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

	public static int SpeedToV(long speed) {
		return (int) (speed * Math.pow(10, 6) / 3.6);
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
