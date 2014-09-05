package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class ValidPostionStatement {

	private EPStatement epStatement;
	
	public ValidPostionStatement(EPAdministrator epAdministrator, UpdateListener listener) {
	
		String stmt = "insert into ValidPositionEvent select * , "
						+ "current_timestamp() from NewPositionEvent Pos "
						+ " where eplab.anfragen.Game.isValidInterval(Pos.ts) = true ";
		
		this.epStatement = epAdministrator.createEPL(stmt);
		this.epStatement.addListener(listener);
		
		
	}
	
}
