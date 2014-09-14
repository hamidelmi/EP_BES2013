package eplab.anfragen;

public class AccumulativeIntensity {
	static final long TIMEFACTOR = 1000000000;
	public long ts;
	public long lastIntTs;
	public int intensity;
	public long v;
	public boolean active;

	public static AccumulativeIntensityEvent Instantiate(int sensorId, long ts,
			long abs_v) {
		int instanceIntensity = 5;

		if (abs_v <= AccumulativeIntensity.SpeedToV(1))
			instanceIntensity = 0;
		else if (abs_v <= AccumulativeIntensity.SpeedToV(11))
			instanceIntensity = 1;
		else if (abs_v <= AccumulativeIntensity.SpeedToV(13))
			instanceIntensity = 2;
		else if (abs_v <= AccumulativeIntensity.SpeedToV(17))
			instanceIntensity = 3;
		else if (abs_v <= AccumulativeIntensity.SpeedToV(24))
			instanceIntensity = 4;

		Game game = Game.Singleton();
		AccumulativeIntensityEvent result = new AccumulativeIntensityEvent();
		AccumulativeIntensity current = game.getIntensity(sensorId);
		if (current == null)
			current = new AccumulativeIntensity();

		if (current.active || (TimeDiff(current.lastIntTs, ts) > 1)) {
			long speed = current.v;
			long delta = ts - current.ts;
			long distance = VToDistance(speed, delta);

			result.setTs_start(current.ts / TIMEFACTOR);
			result.setTs_stop(ts / TIMEFACTOR);
			result.setPlayer_id(game.getPlayer(sensorId).name);
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

	public static long VToDistance(long v, long time) {
		return v * (time / TIMEFACTOR);
	}

	public static double TimeDiff(long s, long e) {
		return (e - s) * Math.pow(10, -12);
	}

	public static long TimeToSecond(long ts) {
		ts = ts - (10629342490369879L / TIMEFACTOR);
		// System.out.println(ts * 1.66666667D * Math.pow(10, -14 + 9));
		return (long) (ts * 1 * Math.pow(10, -12 + 9));
	}

	public static String TimeToString(long ts) {
		ts = ts - (10629342490369879L / TIMEFACTOR);
		// System.out.println(ts * 1.66666667D * Math.pow(10, -14 + 9));
		return String.format("%.2f", ts * 1 * Math.pow(10, -12 + 9));
	}
}
