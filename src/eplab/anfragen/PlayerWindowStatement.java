package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class PlayerWindowStatement {

	private EPStatement epStatement;
	private EPStatement createwindow;
	
	  
	public PlayerWindowStatement(EPAdministrator epAdministrator) {
		String query = "create window PlayerWindow.std:unique(playerName) as NewPositionEvent";
	    
	    this.createwindow = epAdministrator.createEPL(query);

	    query = "insert into PlayerWindow select * from NewPositionEvent "
	    		+ "where eplab.anfragen.Game.getPlayerName(sid) IS NOT NULL "
	    		+ "and PosInField <> '-1' ";
	    
	    this.epStatement = epAdministrator.createEPL(query);
		
	}
	
}