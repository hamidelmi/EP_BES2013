package eplab.anfragen;

import eplab.anfragen.Game;
public class AccumulativeIntensity {
	public long ts;
	public long lastIntTs;
	public int intensity;
	public long v;
	public boolean active;

	public static AccumulativeIntensity Instantiate(String name, long ts,
			long abs_v, int instanceIntensity) {
		Game game = Game.Singleton();
		AccumulativeIntensity current = game.getIntensity(name);
		if (current == null)
			current = new AccumulativeIntensity();

		if (current.active) {
			current.active = current.intensity == instanceIntensity;
			current.ts = ts;
			current.v = abs_v;
			current.intensity = instanceIntensity;
		}
		return current;
	}
}
