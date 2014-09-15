package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class OutOfPlayStatement {

	private EPStatement epStatement;
	
	public OutOfPlayStatement(EPAdministrator epAdministrator, UpdateListener listener) {
		String query = "insert into OutOfPlayEvent select Pos.ts as ts, "
				+ " current_timestamp() from NewPositionEvent Pos "
				+ "  where ( eplab.anfragen.Game.isValidInterval(Pos.ts) = false )"
				+ " or ( eplab.anfragen.Game.isBall(sid) = true and "
				+ " Pos.PosInField = '-1' )";
		
		this.epStatement = epAdministrator.createEPL(query);
		this.epStatement.addListener(listener);
	}
	
}
