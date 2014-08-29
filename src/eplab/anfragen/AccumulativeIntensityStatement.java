package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class AccumulativeIntensityStatement {
	private EPStatement statement;

	public AccumulativeIntensityStatement(EPAdministrator admin,
			UpdateListener listener) {
		String query = // "insert into CurrentIntensityEvent "
		// String query = "insert into AggIntPlayerEvent_"
		// + 5
		// + " "
		"select accI.player_id as player_id,intensity, "
				+ "sum(case when intensity=0 "
				+ " then (accI.distance) else 0 end) as standing_distance, "
				+ "sum(case when intensity=0 "
				+ " then (accI.ts_stop - accI.ts_start)/1000000000 else 0 end) as standing_time,	 "
				+ "sum(case when intensity=1 "
				+ " then (accI.distance) else 0 end) as trot_distance, "
				+ "sum(case when intensity=1 "
				+ " then (accI.ts_stop - accI.ts_start)/1000000000 else 0 end) as trot_time,"
				+ "sum(case when intensity=2 "
				+ " then (accI.distance) else 0 end) as low_distance, "
				+ "sum(case when intensity=2 "
				+ " then (accI.ts_stop - accI.ts_start)/1000000000 else 0 end) as low_time,"
				+ "sum(case when intensity=3 "
				+ " then (accI.distance) else 0 end) as medium_distance, "
				+ "sum(case when intensity=3 "
				+ " then (accI.ts_stop - accI.ts_start)/1000000000 else 0 end) as medium_time,"
				+ "sum(case when intensity=4 "
				+ " then (accI.distance) else 0 end) as high_distance, "
				+ "sum(case when intensity=4 "
				+ " then (accI.ts_stop - accI.ts_start)/1000000000 else 0 end) as high_time,"
				+ "sum(case when intensity=5 "
				+ " then (accI.distance) else 0 end) as sprint_distance, "
				+ "sum(case when intensity=5 "
				+ " then (accI.ts_stop - accI.ts_start)/1000000000 else 0 end) as sprint_time"
				// + " from CurrentIntensityEvent.win:time(300000 sec) as accI "
				// + "group by accI.player_id";
				// "select accI.* "
				+ " from InstantIntensityEvent iie, method:epdebs.game_objects.AccumulativeIntensity.Instantiate(iie.sid,iie.ts,iie.abs_v,iie.InstantIntensity) accI";
		// accI
		this.statement = admin.createEPL(query);
		if (listener != null)
			this.statement.addListener(listener);
	}
}