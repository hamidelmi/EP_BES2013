package queries;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class BallPossessionStatement {

	private EPStatement statement;
	
	public BallPossessionStatement(EPAdministrator admin, UpdateListener listener) {
		String query = "insert into BallPossessionEvent"
						+ "select EarlierEvent.player_ts as ts,"
						+ "CurrentEvent.palyerName as palyerName,"
						+ "epdebs.game_objects.Game.GetTeamName(CurrentEvent.palyerName) as teamId,"
						+ "EarlierEvent.player_ts - CurrentEvent.player_ts as time,"
						+ "1 as hit"
						+ "from pattern [every (CurrentEvent = ContactEvent-> "
						+ "EarlierEvent = ContactEvent( CurrentEvent.palyerName <> palyerName)) ]";
		
    	
		this.statement = admin.createEPL(query);
		this.statement.addListener(listener);
		
	}
	
}
