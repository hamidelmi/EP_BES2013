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
					+ "eplab.anfragen.Game.getPlayerName(sid) as playerName, "
					+ "hm.resolution as resolution, "
					+ "eplab.anfragen.Game.GetTimeInGame(pe.ts) as timeInGame, "
					+ "eplab.bodenobjekte.Grid.GetLeftX(pe.x, hm.resWidth, hm.cellWidth, hm.cellWidthAdj) as cell_x1, "
					+ "eplab.bodenobjekte.Grid.GetLeftY(pe.y, hm.resHeight, hm.cellHeight, hm.cellHeightAdj) as cell_y1, "
					+ "eplab.bodenobjekte.Grid.GetRightX(pe.x, hm.resWidth, hm.cellWidth, hm.cellWidthAdj) as cell_x2, "
					+ "eplab.bodenobjekte.Grid.GetRightY(pe.y, hm.resHeight, hm.cellHeight, hm.cellHeightAdj) as cell_y2 "
					+ "from method:eplab.bodenobjekte.Grid.getGrid() hm , PositionEvent.std:groupwin(sid).win:length(4) as pe";
 
		this.statement = admin.createEPL(stmt);
		this.statement.addListener(listener);
	}
}