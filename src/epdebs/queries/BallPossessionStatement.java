package epdebs.queries;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class BallPossessionStatement {

	private EPStatement epStatement1, epStatement2;

	public BallPossessionStatement(EPAdministrator epAdministrator, UpdateListener listener) {
		String query1 = "insert into BallPossessionEvent "
				+ "select ContactEventB.player_ts as ts,  ContactEventA.palyerName as palyerName, epdebs.parser.Match.GetTeamFromPlayerName(ContactEventA.palyerName) as teamId, ContactEventB.player_ts - ContactEventA.player_ts as time,  1 as hit  from pattern [every (ContactEventA = ContactEvent-> ContactEventB = ContactEvent( ContactEventA.palyerName <> palyerName)) ]";

		String query2 = " insert into BallPossessionEvent "
				+ "select OutOfPlay.ts as ts, ContactEventA.palyerName as palyerName, epdebs.parser.Match.GetTeamFromPlayerName(ContactEventA.palyerName) as teamId, OutOfPlay.ts - ContactEventA.player_ts as time, 1 as hit  from pattern [every (ContactEventA = ContactEvent-> OutOfPlay = PauseIntervalEvent ) ]";

		this.epStatement1 = epAdministrator.createEPL(query1);
		this.epStatement2 = epAdministrator.createEPL(query2);
		this.epStatement1.addListener(listener);
		this.epStatement2.addListener(listener);
	}
}
