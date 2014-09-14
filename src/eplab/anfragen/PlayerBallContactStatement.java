package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class PlayerBallContactStatement {

	private EPStatement epStatement;
	
	public PlayerBallContactStatement(EPAdministrator epAdministrator, UpdateListener listener) {
		String query = 	"on BallTouchEvent as ball "
						+ "insert into PlayerBallContactEvent select  "
						+ "win.x as player_x, win.y as player_y, win.z as player_z, "
						+ "win.playerName as palyerName, win.PosInField as PosInField, "
						+ "win.vx as player_vx, win.vy as player_vy, win.vz as player_vz, "
						+ "win.ts as player_ts,  "
						+ "ball.ballId as ball_id, ball.ts as ball_ts, "
						+ "eplab.anfragen.Game.GetEuclideanDistance(win.x , win.y , win.z , ball.x , ball.y , ball.z) as distance "
						+ "from PlayerWindow as win where "
						+ "eplab.anfragen.Game.GetEuclideanDistance(win.x , win.y , win.z , ball.x , ball.y , ball.z) < 10000 "
						+ "order by distance, player_ts limit 1";
    	
		this.epStatement = epAdministrator.createEPL(query);
		this.epStatement.addListener(listener);
		
	}
	
}
