package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class NewPostionStatement {

	private EPStatement epStatement;
	
	public NewPostionStatement(EPAdministrator epAdministrator, UpdateListener listener) {
		String query = "insert into NewPositionEvent "
						+ "select RawPos.sid as sid, RawPos.ts as ts, "
						+ "RawPos.x as x, RawPos.y as y, RawPos.z as z, "
						+ "RawPos.vx as vx, RawPos.vy as vy, RawPos.vz as vz, "
						+ "RawPos.ax as ax, RawPos.ay as ay, RawPos.az as az, "
						+ "RawPos.abs_v as abs_v, RawPos.abs_a as abs_a, "
						+ "eplab.anfragen.Game.getPlayerName(RawPos.sid) as playerName, " 
//						+ "Player.name as name, "
						+ "eplab.anfragen.Game.StatusInField(RawPos.x,RawPos.y,RawPos.z) as PosInField"
//						+ "debs13.challenge.game.Game.GetTimeInGame(RawPos.ts) as timeInGame"
						+ " from PositionEvent RawPos ";
//						+ "method:eplab.anfragen.Game.getPlayerIndex(RawPos.sid) Player ";
    	
		this.epStatement = epAdministrator.createEPL(query);
		this.epStatement.addListener(listener);
				
	}
	
}
