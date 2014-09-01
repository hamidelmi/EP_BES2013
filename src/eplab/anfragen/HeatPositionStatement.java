package eplab.anfragen;
 
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;
 
public class HeatPositionStatement
{
	private EPStatement statement;
   
	public HeatPositionStatement(EPAdministrator admin, UpdateListener listener)
	{
		String stmt = "insert into HeatPositionEvent "
					+ "select pe.ts as ts, "
					+ "pe.timeInGame as timeInGame, "
					+ "pe.playerName as playerName, "
					+ "hm.resolution as resolution, "
					+ "Grid.GetLeftX(pe.x, hm.resWidth, hm.cellWidth, hm.cellWidthAdj) as cell_x1, "
					+ "Grid.GetLeftY(pe.y, hm.resHeight, hm.cellHeight, hm.cellHeightAdj) as cell_y1, "
					+ "Grid.GetRightX(pe.x, hm.resWidth, hm.cellWidth, hm.cellWidthAdj) as cell_x2, "
					+ "Grid.GetRightY(pe.y, hm.resHeight, hm.cellHeight, hm.cellHeightAdj) as cell_y2 "
					+ "from method:Grid.getGrid() hm,  NormPosEvent pe ";
 
		this.statement = admin.createEPL(stmt);
		this.statement.addListener(listener);
	}
}