package epdebs.queries;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class PositionStatement {
	private EPStatement epStatement;

	public PositionStatement(EPAdministrator epAdministrator,
			UpdateListener listener) {

		String query = "insert into PositionEvent "
				+ "select eventPosition.sid as sid, eventPosition.ts as ts, eventPosition.x as x, eventPosition.y as y, eventPosition.z as z, "
				+ "eventPosition.vx as vx, eventPosition.vy as vy, eventPosition.vz as vz, eventPosition.ax as ax, eventPosition.ay as ay, eventPosition.az as az, "
				+ "eventPosition.abs_v as abs_v, eventPosition.abs_a as abs_a, Entity.name as name, Entity.entityType as entType, "
				+ "epdebs.parser.Match.StatusInField(eventPosition.x,eventPosition.y,eventPosition.z) as PosInField , "
				+ "epdebs.parser.Match.GetTimeInGame(eventPosition.ts) as timeInGame "
				+ "from Event eventPosition , method:epdebs.parser.Match.GetEntityFromSensorIds(sid) Entity";

/*		String query = "select sid, ts, abs_v, " + "CASE WHEN abs_v < " + SpeedToV(1)
				+ " THEN 0 " + "WHEN abs_v < " + SpeedToV(11) + " THEN 1"
				+ "WHEN abs_v < " + SpeedToV(13) + " THEN 2" + "WHEN abs_v < "
				+ SpeedToV(17) + " THEN 3" + "WHEN abs_v < " + SpeedToV(24)
				+ " THEN 4" + "ELSE 5" + "END as  InstantIntensity " + "FROM Event";*/
		this.epStatement = epAdministrator.createEPL(query);
		this.epStatement.addListener(listener);
	}
	private int SpeedToV(int speed) {
		return (int) (speed * Math.pow(10, 6) / 3.6);
	}
}
