package epdebs.parser;

public class CurrentIntensity {
	public long ts;
	public long lastIntTs;
	public int intensity;
	public long v;
	public boolean active;

	public CurrentIntensity(long ts, int intensity, long abs_v, boolean active) {
		this.ts = ts;
		this.intensity = intensity;
		this.v = abs_v;
		this.active = false;

		this.lastIntTs = ts;
	}
}
