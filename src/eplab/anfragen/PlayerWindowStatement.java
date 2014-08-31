package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class PlayerWindowStatement {

	private EPStatement epStatement;
	private EPStatement createwindow;
	
	  
	public PlayerWindowStatement(EPAdministrator epAdministrator) {
		String query = "create window PlayerWindow.std:unique(name) as PositionEvent";
	    
	    this.createwindow = epAdministrator.createEPL(query);
	    

	    query = "insert into PlayerWindow select * from PositionEvent "
	    		+ "where eplab.anfragen.Game.getPlayer(sid) != null and PosInField <> -1 ";
	    
	    this.epStatement = epAdministrator.createEPL(query);
		
	}
	
}
