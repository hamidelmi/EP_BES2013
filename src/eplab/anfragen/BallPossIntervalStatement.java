package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class BallPossIntervalStatement {

	private EPStatement epStatement1;
	private EPStatement epStatement2;
	
	public BallPossIntervalStatement(EPAdministrator epAdministrator, UpdateListener listener) {
		  String stmt_1 = "insert into BallPossIntervalEvent "
				  + "select ContactEventB.player_ts as ts, "
				  + "ContactEventA.palyerName as palyerName, "
				  + "eplab.anfragen.Game.getTeam(ContactEventA.palyerName) "
				  + "as teamId, ContactEventB.player_ts - ContactEventA.player_ts as time, "
				  + "1 as hit  from pattern "
				  + "[every (ContactEventA = PlayerBallContactEvent -> "
				  + "ContactEventB = PlayerBallContactEvent( ContactEventA.palyerName <> palyerName)) ]";

		  String stmt_2 = " insert into BallPossIntervalEvent "
		  		+ "select OutOfPlay.ts as ts, ContactEventA.palyerName as palyerName, "
		  		+ "eplab.anfragen.Game.getTeam(ContactEventA.palyerName) "
		  		+ "as teamId, OutOfPlay.ts - ContactEventA.player_ts as time, "
		  		+ "1 as hit  from pattern "
		  		+ "[every (ContactEventA = PlayerBallContactEvent) -> (OutOfPlay = OutOfPlayEvent ) ]";

		  
		  
		this.epStatement1 = epAdministrator.createEPL(stmt_1);
		this.epStatement1.addListener(listener);
		
		this.epStatement2 = epAdministrator.createEPL(stmt_2);
		this.epStatement2.addListener(listener);
		
	}
	
}
