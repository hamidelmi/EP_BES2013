package epdebs.queries;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class RelevantPositionStatement {

	private EPStatement epStatement;

	public RelevantPositionStatement(EPAdministrator epAdministrator, UpdateListener listener) {
		String query = "insert into RelevantPositionEvent select * , current_timestamp() "
				+ "from PositionEvent Pos";//  where epdebs.parser.Match.IsActiveGame(Pos.ts) = true ";

		this.epStatement = epAdministrator.createEPL(query);
		this.epStatement.addListener(listener);
	}
}
