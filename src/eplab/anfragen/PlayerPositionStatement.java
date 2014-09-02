package eplab.anfragen;
 
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;
 
public class PlayerPositionStatement
{
	private EPStatement statement;
   
	public PlayerPositionStatement(EPAdministrator admin, UpdateListener listener)
	{
		String stmt = "insert into PlayerPositionEvent  "
				+ "select min(playerPos.ts) as start_ts, "
				+ "max(playerPos.ts) as ts,  "
				+ "playerPos.name as playerName, "
				+ "Math.round(avg(playerPos.abs_v)) as abs_v, "
				+ "Math.round(avg(playerPos.x)) as x, "
				+ "Math.round(avg(playerPos.y)) as y, "
				+ "Game.getPlayerName(sid), "
				+ "timeInGame as timeInGame "
				+ "from PositionEvent.std:groupwin(name).win:length(4) as playerPos  "
				+ "where playerPos.entType = 0 "
				+ "or playerPos.entType = 1 "
				+ "group by name"
				+ "method:GetTimeInGame(max(playerPos.ts)) Time";
		
		this.setStatement(admin.createEPL(stmt));
		this.statement.addListener(listener);
	}

	public EPStatement getStatement() {
		return statement;
	}

	public void setStatement(EPStatement statement) {
		this.statement = statement;
	}
}