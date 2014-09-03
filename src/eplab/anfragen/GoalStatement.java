package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class GoalStatement {
	private EPStatement epsStatement1;
	private EPStatement epsStatement2;

	public GoalStatement(EPAdministrator epAdministrator,
			UpdateListener listener) {
//		String query = "insert into GoalEvent "
//				+ "select * from pattern[GoalEvent= BallContactEvent(shotongoal = 1) "
//				// + "->ballPositionEvent=RelevantPositionEvent(entType = 2) "
//				+ "until (BallContactEvent or NotInGameEvent)] ";

		 String query1 = "insert into GoalEvent "
		 + "select * from BallContactEvent b where b.shotongoal = 1";
		 
		 String query2 = "select ts, x,y,z from GoalEvent";
//GoalEvent.win:time(1 min)
		this.epsStatement1 = epAdministrator.createEPL(query1);
		this.epsStatement2 = epAdministrator.createEPL(query2);
		this.epsStatement2.addListener(listener);
	}
}
