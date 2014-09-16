package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class TeamBallPossessionStatement {

	private EPStatement epStatement1;
	private EPStatement epStatement2;
	
	public TeamBallPossessionStatement(EPAdministrator epAdministrator, UpdateListener listener) {
		String query =  "insert into TeamBallPossIntervalEvent " 
						+ " select max(ts) as ts, teamId, "
						+ " sum(time) as time"
						+ " from BallPossIntervalEvent.win:ext_timed(ts/1000000000, 5 minutes)"
						+ " group by teamId";
//						+ " order by teamId, time ";
//						+ " output last every 1 ";
	    
		this.epStatement1 = epAdministrator.createEPL(query);
//		this.epStatement1.addListener(listener);
		
		query =  "insert into TeamBallPossEvent " 
				+ " select EventA.ts as ts, EventA.teamId as teamId, "
				+ " case  when EventA.teamId='teamA' then EventA.time"
				+ "       	when EventB.teamId='teamA' then EventB.time end as timeA, "
				+ " case  when EventA.teamId='teamB' then EventA.time"
				+ "       	when EventB.teamId='teamB' then EventB.time end as timeB, "
				+ " (EventA.time/(EventA.time + EventB.time))*100 as time_precent"
				+ " from pattern [ every ( EventA = TeamBallPossIntervalEvent   -> "
				+ " EventB = TeamBallPossIntervalEvent ( EventA.teamId != EventB.teamId"
				+ " and EventB.ts IS NOT NULL))]";
//				+ " group by EventA.teamId ";

		
		this.epStatement2 = epAdministrator.createEPL(query);
		this.epStatement2.addListener(listener);
		
		
	}
	
}
