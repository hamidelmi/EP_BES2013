package epdebs.queries;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class AccumulativeIntensityStatement {
	private EPStatement statement;

	public AccumulativeIntensityStatement(EPAdministrator admin,
			UpdateListener listener) {
		String query = "insert into CurrentIntensityEvent "
				+ "select  curIntMessage.* "
				+ "from InstantIntensity intEvent method:debs13.challenge.game.Game.GetCurrentIntensityMessage(intEvent.name,intEvent.ts,intEvent.intensity,intEvent.abs_v) curIntMessage where not curIntMessage.player_id = 'ignore' ";
		this.statement = admin.createEPL(query);
		if (listener != null)
			this.statement.addListener(listener);
	}
}