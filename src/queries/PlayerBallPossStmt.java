package queries;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class PlayerBallPossStmt {

	private EPStatement statement;
	
	public PlayerBallPossStmt(EPAdministrator admin, UpdateListener listener) {
		String query = "insert into PlayerBallPossEvent"
						+ "select max(ts) as ts,"
						+ "palyerName,"
						+ "sum(time) as time,"
						+ "sum(hit) as hits"
						+ "from BallPossessionEvent group by palyerName"
						+ "output last every 1 events";
    	
		this.statement = admin.createEPL(query);
		this.statement.addListener(listener);
		
	}
	
}
