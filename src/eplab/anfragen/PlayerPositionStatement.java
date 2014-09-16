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
				+ "select eplab.anfragen.Game.getPlayerName(playerPos.sid) as playerName "
				+ "from PositionEvent.std:groupwin(sid).win:length(4) as playerPos";
//				+ "group by playerName ";
		
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