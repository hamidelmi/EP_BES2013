package eplab.anfragen;
 
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;
 
public class HeatMapDeltaPositionStatement
{
	private EPStatement statement;
	public HeatMapDeltaPositionStatement(EPAdministrator admin, UpdateListener listener)
	{
		String stmt = "insert into HeatMapDeltaPositionEvent "
					+ "select "
					+ "(b.ts - a.ts) as dts, "
					+ "b.ts as currts, "
					+ "a.playerName as playerName, "
					+ "b.timeInGame as timeInGame, "
					+ "a.cell_x1 as cell_x1, "
					+ "a.cell_y1 as cell_y1, "
					+ "a.cell_x2 as cell_x2, "
					+ "a.cell_y2 as cell_y2, "
					+ "a.resolution as resolution "
					+ "from pattern  [ every a=HeatPositionEvent "
					+ "-> b=HeatPositionEvent(b.playerName=a.playerName "
					+ "and ((b.cell_x1 != a.cell_x1) "
					+ "  or (b.cell_x2 != a.cell_x2) "
					+ "  or (b.cell_y1 != a.cell_y1) "
					+ "  or (b.cell_y2 != a.cell_y2)) )]"
					+ " order by playerName, dts";
	     
		this.statement = admin.createEPL(stmt);
//		this.statement.addListener(listener);
	}
}