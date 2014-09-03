package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class PlayerLocationStatement {

	private EPStatement createwindow, insertwindow;

	public PlayerLocationStatement(EPAdministrator epAdministrator,
			UpdateListener listener) {
		String queryCreate = "create window PlayerLocation.std:unique(ax) as PositionEvent";
		String queryInsert = "insert into PlayerLocation select * from PositionEvent where" + /*entType in (0,1) and*/" PosInField <> -1 ";
		// String queryInsert = "insert into PlayerLocation select * from PositionEvent where PosInField <> -1 ";

		this.createwindow = epAdministrator.createEPL(queryCreate);
		this.insertwindow = epAdministrator.createEPL(queryInsert);
		this.createwindow.addListener(listener);
		this.insertwindow.addListener(listener);
	}
}