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
					+ "select e.ts/1000000000 as ts, "
					+ "eplab.anfragen.Game.getPlayerName(pe.sid) as playerName, "
					+ "hm.resolution as resolution, "
					+ "eplab.anfragen.Game.GetTimeInGame(e.ts) as timeInGame, "
					+ "eplab.bodenobjekte.Grid.GetLeftX(e.x, hm.resWidth, hm.cellWidth, hm.cellWidthAdj) as cell_x1, "
					+ "eplab.bodenobjekte.Grid.GetLeftY(e.y, hm.resHeight, hm.cellHeight, hm.cellHeightAdj) as cell_y1, "
					+ "eplab.bodenobjekte.Grid.GetRightX(e.x, hm.resWidth, hm.cellWidth, hm.cellWidthAdj) as cell_x2, "
					+ "eplab.bodenobjekte.Grid.GetRightY(e.y, hm.resHeight, hm.cellHeight, hm.cellHeightAdj) as cell_y2 "
					+ "from method:eplab.bodenobjekte.Grid.getGrid() hm , Event.std:groupwin(sid).win:length(4) as pe "
					+ "where eplab.anfragen.Game.getPlayerName(pe.sid) IS NOT NULL "
					+ "and pe.ts > " + eplab.anfragen.Game.tsStartFirstHalf;
 
		this.statement = admin.createEPL(stmt);
//		this.statement.addListener(listener);
	}
}