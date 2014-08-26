package epdebs.queries;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class BallTouchStatement {

	private EPStatement epStatement;
	
	public BallTouchStatement(EPAdministrator epAdministrator, UpdateListener listener) {
		String query = "insert into BallTouchEvent"
						+ "select BallEventA.ts as ts, BallEventA.sid as ballId,"
						+ "BallEventA.timeInGame as timeInGame,  BallEventA.x as x,"
						+ "BallEventA.y as y, BallEventA.z as z, BallEventA.vx as vx,"
						+ "BallEventA.vy as vy, BallEventA.vz as vz, BallEventA.ax as ax,"
						+ "BallEventA.ay as ay, BallEventA.az as az, BallEventA.abs_v as abs_v,"
						+ "(BallEventA.abs_a / Math.pow(10, 6)) as abs_a, "
						+ "(BallEventB.abs_a / Math.pow(10, 6)) as abs_b,"
						+ "from pattern [every (BallEventA = RelevantPositionEvent(entType=2 "
						+ "and Math.abs(abs_a)/Math.pow(10, 6)> 55)-> "
						+ "BallEventB = RelevantPositionEvent(entType=2 and "
						+ "BallEventA.sid = sid and abs_a < BallEventA.abs_a))]";
		
    	
		this.epStatement = epAdministrator.createEPL(query);
		this.epStatement.addListener(listener);
		
	}
	
}
