package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class AccumulativeIntensityStatement {

	public AccumulativeIntensityStatement(EPAdministrator admin,
			UpdateListener listener) {
		EPStatement statement, statement2;
		String query = "INSERT INTO accIqq "
				+ " select accI.* "
				+ " from Event, method:eplab.anfragen.AccumulativeIntensity.Instantiate(Event.sid,Event.ts,Event.abs_v) accI";

		statement = admin.createEPL(query);
		statement2 = admin
				.createEPL("select max(accI.ts_stop) as ts,accI.player_id as player_id,"
						+ "sum(case when intensity=0 "
						+ " then (accI.distance) else 0 end) as standing_distance, "
						+ "sum(case when intensity=0 "
						+ " then (accI.ts_stop - accI.ts_start) else 0 end) as standing_time,"
						+ "sum(case when intensity=1 "
						+ " then (accI.distance) else 0 end) as trot_distance, "
						+ "sum(case when intensity=1 "
						+ " then (accI.ts_stop - accI.ts_start) else 0 end) as trot_time,"
						+ "sum(case when intensity=2 "
						+ " then (accI.distance) else 0 end) as low_distance, "
						+ "sum(case when intensity=2 "
						+ " then (accI.ts_stop - accI.ts_start) else 0 end) as low_time,"
						+ "sum(case when intensity=3 "
						+ " then (accI.distance) else 0 end) as medium_distance, "
						+ "sum(case when intensity=3 "
						+ " then (accI.ts_stop - accI.ts_start) else 0 end) as medium_time,"
						+ "sum(case when intensity=4 "
						+ " then (accI.distance) else 0 end) as high_distance, "
						+ "sum(case when intensity=4 "
						+ " then (accI.ts_stop - accI.ts_start) else 0 end) as high_time,"
						+ "sum(case when intensity=5 "
						+ " then (accI.distance) else 0 end) as sprint_distance, "
						+ "sum(case when intensity=5 "
						+ " then (accI.ts_stop - accI.ts_start) else 0 end) as sprint_time "
						+ " from accIqq.win:ext_timed(ts_start,5 minutes) accI"
						+ " group by accI.player_id");
		if (listener != null)
			statement2.addListener(listener);
	}
}