package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class GoalStatement {
	private EPStatement epsStatement;

	public GoalStatement(EPAdministrator epAdministrator,
			UpdateListener listener) {
		String query = "insert into GoalEvent "
				+ "select * from pattern[GoalEvent= BallContactEvent(shotongoal = 1) "
				// + "->ballPositionEvent=RelevantPositionEvent(entType = 2) "
				+ "until (BallContactEvent or NotInGameEvent)] ";

		// String query = "insert into GoalEvent "
		// + "select * from BallContactEvent b where b.shotongoal = 1 ";

		this.epsStatement = epAdministrator.createEPL(query);
		this.epsStatement.addListener(listener);
	}
}
