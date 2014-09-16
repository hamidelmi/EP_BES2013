package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class ContactStatement {

	private EPStatement epStatement;

	public ContactStatement(EPAdministrator epAdministrator,
			UpdateListener listener) {
		String query = "on BallContactEvent as ball insert into ContactEvent "
				+ "select win.playerName as playerName, win.sid as sid, win.x as player_x, win.y as player_y, win.z as player_z, "
				+ "win.vx as player_vx, win.vy as player_vy, win.vz as player_vz, win.ts as player_ts,  "
				+ "win.getLocation as getLocation, ball.ballId as ball_id, ball.ts as ball_ts, ball.isTowardGoal as isTowardGoal, "
				+ "eplab.anfragen.Game.GetDistance(win.x ,ball.x, win.y ,ball.y, win.z ,ball.z) as getDistance "
				+ "from PlayerLocation as win "
				+ "where sid is not 13 and sid is not 14 and sid is not 97 and sid is not 98 and sid is not 61 and sid is not 62 and sid is not 99 and sid is not 100 "
				+ "and sid is not 4 and sid is not 8 and sid is not 10 and sid is not 12 "
				+ "order by getDistance, player_ts limit 1";

		this.epStatement = epAdministrator.createEPL(query);
		this.epStatement.addListener(listener);
	}
}
