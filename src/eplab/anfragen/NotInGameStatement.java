package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class NotInGameStatement {
	private EPStatement epStatement;

	public NotInGameStatement(EPAdministrator epAdministrator, UpdateListener listener) {
		String query = "insert into NotInGameEvent select Pos.ts as ts , current_timestamp() "
				+ "from PositionEvent Pos "
				+ "where eplab.anfragen.Game.IsActiveGame(Pos.ts) = false "
				+ "or (Pos.getLocation = 3) ";

		this.epStatement = epAdministrator.createEPL(query);
		this.epStatement.addListener(listener);
	}

}
