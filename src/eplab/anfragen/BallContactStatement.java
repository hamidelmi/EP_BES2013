package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class BallContactStatement {
	private EPStatement epStatement;

	public BallContactStatement(EPAdministrator admin, UpdateListener listener) {
		String query = "insert into BallContactEvent  "
				+ "select BallEventA.ts as ts, BallEventA.sid as ballId, "
				+ "BallEventA.x as x, BallEventA.y as y, BallEventA.z as z, BallEventA.vx as vx, "
				+ "BallEventA.vy as vy, BallEventA.vz as vz, BallEventA.ax as ax, BallEventA.ay as ay, BallEventA.az as az, "
				+ "BallEventA.abs_v as abs_v, (BallEventA.abs_a / 1000000) as abs_a, (BallEventB.abs_a / 1000000) as abs_b, "
				+ "eplab.anfragen.Game.IsGoal(BallEventA.ts, BallEventA.x, BallEventA.y,BallEventA.z, BallEventA.abs_v, "
				+ "BallEventA.vx, BallEventA.vy,BallEventA.vz) as shotongoal "
				+ "from pattern [every (BallEventA = RelevantPositionEvent("+/*entType=2 and */" Math.abs(abs_a)/1000000 > 55)-> "
				+ "BallEventB = RelevantPositionEvent("+/*entType=2 and */"BallEventA.sid = sid and abs_a < BallEventA.abs_a))]";

		this.epStatement = admin.createEPL(query);
		this.epStatement.addListener(listener);
	}
}
