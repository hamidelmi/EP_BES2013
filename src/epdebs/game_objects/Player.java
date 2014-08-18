package epdebs.game_objects;

public class Player extends BaseObject {
	public Player(int leftLeg, int rightLeg, String name) {
		super(new int[] { leftLeg, rightLeg }, name);
	}
}
