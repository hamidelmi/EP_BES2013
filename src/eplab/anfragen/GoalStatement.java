package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class GoalStatement {
	private EPStatement epsStatement1;
	private EPStatement epsStatement2;

	public GoalStatement(EPAdministrator epAdministrator,
			UpdateListener listener) {

		//timer:interval(10 sec)
		 String query1 = "insert into GoalEvent "
		 + "select * from ContactEvent b where b.isTowardGoal = 1";

		 String query2 = "select sid, player_ts, player_x, player_y from GoalEvent";

		this.epsStatement1 = epAdministrator.createEPL(query1);
		this.epsStatement2 = epAdministrator.createEPL(query2);
		this.epsStatement2.addListener(listener);
	}
}