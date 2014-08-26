package epdebs.queries;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class ValidPostionStatement {

	private EPStatement epStatement;
	
	public ValidPostionStatement(EPAdministrator epAdministrator, UpdateListener listener) {
		String query = "insert into ValidPostionEvent "
						+ "select RawPos.sid as sid, RawPos.ts as ts, "
						+ "RawPos.x as x, RawPos.y as y, RawPos.z as z, "
						+ "RawPos.vx as vx, RawPos.vy as vy, RawPos.vz as vz, "
						+ "RawPos.ax as ax, RawPos.ay as ay, RawPos.az as az, "
						+ "RawPos.abs_v as abs_v, RawPos.abs_a as abs_a "
//						+ "Entity.name as name, Entity.entityType as entType,"
//						+ "debs13.challenge.game.Game.StatusInField(RawPos.x,RawPos.y,RawPos.z) as PosInField,"
//						+ "debs13.challenge.game.Game.GetTimeInGame(RawPos.ts) as timeInGame"
						+ "from PositionEvent RawPos";
//						+ "method:debs13.challenge.game.Game.GetEntityFromSensorIds(sid) Entity ";
    	
		this.epStatement = epAdministrator.createEPL(query);
		this.epStatement.addListener(listener);
		
	}
	
}
