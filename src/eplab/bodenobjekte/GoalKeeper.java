package eplab.bodenobjekte;

public class GoalKeeper extends Player {
	public GoalKeeper(int leftLeg, int rightLeg, int leftHand, int rightHand,
			String name) {
		super(leftLeg, rightLeg, name);
		sensorId = new int[] { leftLeg, rightLeg, leftHand, rightHand };
	}
}
