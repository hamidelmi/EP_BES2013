package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class PauseIntervalStatement {
	private EPStatement epStatement;

	public PauseIntervalStatement(EPAdministrator epAdministrator, UpdateListener listener) {
		String query = "insert into PauseIntervalEvent select Pos.ts as ts, current_timestamp() "
				+ "from PositionEvent Pos "
				+ "where (Pos.getLocation = 3) ";
		this.epStatement = epAdministrator.createEPL(query);
		this.epStatement.addListener(listener);
	}
}
