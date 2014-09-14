package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class IntensityStatement {
	private EPStatement statement;

	public IntensityStatement(EPAdministrator admin, UpdateListener listener) {

//		.win:time(5 min)
		String query = "SELECT e.sid FROM pattern [every e = Event(abs_v<=1) -> (Event(sid = e.sid, abs_v<=1) and not Event(sid = e.sid, abs_v>1))"
				+ "-> (Event(sid = e.sid, abs_v<=1) and not Event(sid = e.sid, abs_v>1))"
				+ " where timer:within(5 minutes)] q";

		this.statement = admin.createEPL(query);
//		this.statement = admin.createPattern(query);
	
		if (listener != null)
			this.statement.addListener(listener);
	}

	private int SpeedToV(int speed) {
		return (int) (speed * Math.pow(10, 6) / 3.6);
	}
}
