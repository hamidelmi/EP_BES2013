package eplab.bodenobjekte;

public class Player extends BaseObject {
	public Player(int leftLeg, int rightLeg, String name) {
		super(new int[] { leftLeg, rightLeg }, name);
	}
}
