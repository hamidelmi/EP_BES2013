package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class BallPossessionStatement {

	private EPStatement epStatement;
	
	public BallPossessionStatement(EPAdministrator epAdministrator, UpdateListener listener) {
<<<<<<< HEAD
		String query = "insert into BallPossessionEvent "
						+ "select EarlierEvent.player_ts as ts, "
						+ "CurrentEvent.palyerName as palyerName, "
						+ "eplab.anfragen.Game.getTeam(CurrentEvent.palyerName) as teamId, "
						+ "EarlierEvent.player_ts - CurrentEvent.player_ts as time, "
						+ "1 as hit "
						+ "from pattern [every CurrentEvent = PlayerBallContactEvent -> "
						+ " every EarlierEvent = PlayerBallContactEvent( CurrentEvent.palyerName <> palyerName) ]";
=======
		String query = "insert into BallPossessionEvent"
						+ "select EarlierEvent.player_ts as ts,"
						+ "CurrentEvent.palyerName as palyerName,"
						//+ "epdebs.game_objects.Game.GetTeamName(CurrentEvent.palyerName) as teamId,"
						+ "EarlierEvent.player_ts - CurrentEvent.player_ts as time,"
						+ "1 as hit"
						+ "from pattern [every (CurrentEvent = ContactEvent-> "
						+ "EarlierEvent = ContactEvent( CurrentEvent.palyerName <> palyerName)) ]";
>>>>>>> 3f0b74b9083d932c989cf6e3312d7b005192ac87
		
    	
		this.epStatement = epAdministrator.createEPL(query);
		this.epStatement.addListener(listener);
		
	}
	
}
