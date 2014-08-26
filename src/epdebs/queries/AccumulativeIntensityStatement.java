package epdebs.queries;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class AccumulativeIntensityStatement {
	private EPStatement statement;

	public AccumulativeIntensityStatement(EPAdministrator admin,
			UpdateListener listener) {
		String query = // "insert into CurrentIntensityEvent "
		"select  * " + "from InstantIntensityEvent iie";
		// method:epdebs.game_objects.AccumulativeIntensity.Instantiate(iie.name,iie.ts,iie.abs_v,iie.intensity)
		// accI
		this.statement = admin.createEPL(query);
		if (listener != null)
			this.statement.addListener(listener);
	}
}