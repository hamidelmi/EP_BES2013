package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class IntensityStatement {
	private EPStatement statement;

	public IntensityStatement(EPAdministrator admin, UpdateListener listener) {


		String query = "INSERT INTO InstantIntensityEvent "
				+ "SELECT sid, ts, abs_v, " + "CASE WHEN abs_v < "
				+ SpeedToV(1) + " THEN 0 " + "WHEN abs_v < " + SpeedToV(11)
				+ " THEN 1" + "WHEN abs_v < " + SpeedToV(13) + " THEN 2"
				+ "WHEN abs_v < " + SpeedToV(17) + " THEN 3" + "WHEN abs_v < "
				+ SpeedToV(24) + " THEN 4" + "ELSE 5"
				+ "END as  InstantIntensity " + "FROM Event";

		this.statement = admin.createEPL(query);
		if (listener != null)
			this.statement.addListener(listener);
	}

	private int SpeedToV(int speed) {
		return (int) (speed * Math.pow(10, 6) / 3.6);
	}
}
