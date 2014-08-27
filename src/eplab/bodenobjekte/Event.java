package eplab.bodenobjekte;

public class Event {
	String sid;
	long ts;
	int x;
	int y;
	int z;
	int abs_v;
	int vx;
	int vy;
	int vz;
	int abs_a;
	int ax;
	int ay;
	int az;

	public String getsid() {
		return this.sid;
	}

	public long getts() {
		return this.ts;
	}

	public int getx() {
		return this.x;
	}

	public int gety() {
		return this.y;
	}

	public int getz() {
		return this.z;
	}

	public int getabs_v() {
		return this.abs_v;
	}

	public int getvx() {
		return this.vx;
	}

	public int getvy() {
		return this.vy;
	}

	public int getvz() {
		return this.vz;
	}

	public int getabs_a() {
		return this.abs_a;
	}

	public int getax() {
		return this.ax;
	}

	public int getay() {
		return this.ay;
	}

	public int getaz() {
		return this.az;
	}

	public Event(String sid, long ts, int x, int y, int z, int abs_v, int vx,
			int vy, int vz, int abs_a, int ax, int ay, int az) 
	{
		this.sid = sid;
		this.ts = ts;
		this.x = x;
		this.y = y;
		this.z = z;
		this.abs_v = abs_v;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
		this.abs_a = abs_a;
		this.ax = ax;
		this.ay = ay;
		this.az = az;
	}

	public String toString() 
	{
		return
		this.sid + "," 
		+ this.ts + "," 
		+ this.x + "," 
		+ this.y + ","
		+ this.z + "," 
		+ this.abs_v + "," 
		+ this.vx + "," 
		+ this.vy + "," 
		+ this.vz + "," 
		+ this.abs_a + "," 
		+ this.ax + ","
		+ this.ay + "," 
		+ this.az;
	}

}
