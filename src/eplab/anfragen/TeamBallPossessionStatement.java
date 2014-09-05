package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class TeamBallPossessionStatement {

	private EPStatement epStatement;
	
	public TeamBallPossessionStatement(EPAdministrator epAdministrator, UpdateListener listener) {
		String query =  "insert into TeamBallPossEvent " 
						+ " select max(ts), teamId, "
						+ " sum(time) as time " 
//						+ " (sum(time)/ debs13.challenge.game.Game.GetTimeInGame(ts))*100.0 as time_precent "
						+ " from BallPossIntervalEvent.win:time(300000 sec) group by teamId ";
	    
		this.epStatement = epAdministrator.createEPL(query);
		this.epStatement.addListener(listener);
		
	}
	
}
