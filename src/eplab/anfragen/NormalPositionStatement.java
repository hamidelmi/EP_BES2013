package eplab.anfragen;
 
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;
 
public class NormalPositionStatement
{
	private EPStatement statement;
   
	public NormalPositionStatement(EPAdministrator admin)
	{
		String stmt = "insert into NormPosEvent  "
				+ "select min(playerPos.ts) as start_ts, "
				+ "max(playerPos.ts) as ts,  "
				+ "playerPos.name as playerName, "
				+ "Math.round(avg(playerPos.abs_v)) as abs_v, "
				+ "Math.round(avg(playerPos.x)) as x, "
				+ "Math.round(avg(playerPos.y)) as y, "
				+ "max(timeInGame) as timeInGame "
				+ "from PositionEvent.std:groupwin(name).win:length(4) as playerPos  "
				+ "where playerPos.entType = 0 "
				+ "or playerPos.entType = 1 "
				+ "group by name";
		
		this.statement = admin.createEPL(stmt);
	}
}