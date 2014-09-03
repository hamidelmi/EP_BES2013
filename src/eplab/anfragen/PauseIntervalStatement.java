package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class PauseIntervalStatement {
	private EPStatement epStatement;

	public PauseIntervalStatement(EPAdministrator epAdministrator, UpdateListener listener) {
		String query = "insert into PauseIntervalEvent select Pos.ts as ts, "+/*Pos.entType as entType , */"current_timestamp() "
				+ "from PositionEvent Pos "
				+ "where ("/*Pos.entType = 2 and */+"Pos.PosInField = -1) ";
				//+ "where eplab.anfragen.Game.IsActiveGame(Pos.ts) = false or (Pos.entType = 2 and Pos.PosInField = -1) ";
		this.epStatement = epAdministrator.createEPL(query);
		this.epStatement.addListener(listener);
	}
}
