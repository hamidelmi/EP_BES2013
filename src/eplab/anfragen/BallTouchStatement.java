
package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class BallTouchStatement {

	private EPStatement epStatement;
	
	public BallTouchStatement(EPAdministrator epAdministrator, UpdateListener listener) {
		String query = "insert into BallTouchEvent "
						+ "select BallEventA.ts as ts, BallEventA.sid as ballId, "
						+ "BallEventA.x as x, "
						+ "BallEventA.y as y, BallEventA.z as z, BallEventA.vx as vx, "
						+ "BallEventA.vy as vy, BallEventA.vz as vz, BallEventA.ax as ax, "
						+ "BallEventA.ay as ay, BallEventA.az as az, BallEventA.abs_v as abs_v, "
						+ "(BallEventA.abs_a / 1000000) as abs_a, "
						+ "(BallEventB.abs_a / 1000000) as abs_b, "
						+ "BallEventA.playerName as playerName, "
						+ "eplab.anfragen.Game.IsTowardGoal(BallEventA.ts, BallEventA.x, BallEventA.y,BallEventA.z, BallEventA.abs_v, "
						+ "BallEventA.vx, BallEventA.vy,BallEventA.vz) as isTowardGoal "
						+ "from pattern [every ( BallEventA = ValidPositionEvent( eplab.anfragen.Game.isBall(cast(sid,int)) = true "
						+ "and Math.abs(abs_a)/ 1000000 > 55) -> "
						+ " BallEventB = ValidPositionEvent( eplab.anfragen.Game.isBall(cast(sid,int)) = true and "
						+ "BallEventA.sid = BallEventB.sid and BallEventB.abs_a < BallEventA.abs_a))]";
		
		this.epStatement = epAdministrator.createEPL(query);
		this.epStatement.addListener(listener);
		
	}
	
}

