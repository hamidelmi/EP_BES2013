package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class BallPossessionStatement {

	private EPStatement epStatement;
	
	public BallPossessionStatement(EPAdministrator epAdministrator, UpdateListener listener) {
		String query = "insert into BallPossessionEvent"
						+ "select EarlierEvent.player_ts as ts,"
						+ "CurrentEvent.palyerName as palyerName,"
						+ "epdebs.game_objects.Game.GetTeamName(CurrentEvent.palyerName) as teamId,"
						+ "EarlierEvent.player_ts - CurrentEvent.player_ts as time,"
						+ "1 as hit"
						+ "from pattern [every (CurrentEvent = ContactEvent-> "
						+ "EarlierEvent = ContactEvent( CurrentEvent.palyerName <> palyerName)) ]";
		
    	
		this.epStatement = epAdministrator.createEPL(query);
		this.epStatement.addListener(listener);
		
	}
	
}
