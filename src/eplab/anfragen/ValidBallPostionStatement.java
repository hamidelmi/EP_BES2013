package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class ValidBallPostionStatement {

	private EPStatement epStatement;
	
	public ValidBallPostionStatement(EPAdministrator epAdministrator, UpdateListener listener) {
		String query = "insert into ValidBallPostionEvent "
						+ "select RawPos.sid as sid, RawPos.ts as ts, "
						+ "RawPos.x as x, RawPos.y as y, RawPos.z as z, "
						+ "RawPos.vx as vx, RawPos.vy as vy, RawPos.vz as vz, "
						+ "RawPos.ax as ax, RawPos.ay as ay, RawPos.az as az, "
						+ "RawPos.abs_v as abs_v, RawPos.abs_a as abs_a, "
						+ "eplab.anfragen.Game.StatusInField(RawPos.x,RawPos.y,RawPos.z) as PosInField "
						+ "from PositionEvent RawPos";
    	
		this.epStatement = epAdministrator.createEPL(query);
		this.epStatement.addListener(listener);
		
	}
	
}
