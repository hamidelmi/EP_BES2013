package epdebs.game_objects;

public class Position {
	protected long x, y, z;

	public Position(long x, long y, long z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public long getX() {
		return this.x;
	}

	public long getY() {
		return this.y;
	}

	public long getZ() {
		return this.z;
	}

}
