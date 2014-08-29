package eplab.anfragen;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class AccumulativeIntensityStatement {
	private EPStatement statement, statement2;

	public AccumulativeIntensityStatement(EPAdministrator admin,
			UpdateListener listener) {
		String query = "insert into accIqq "
				+ " select accI.* "
				+ " from InstantIntensityEvent iie, method:eplab.anfragen.AccumulativeIntensity.Instantiate(iie.sid,iie.ts,iie.abs_v,iie.InstantIntensity) accI";

		this.statement = admin.createEPL(query);
		this.statement2 = admin
				.createEPL("select accI.player_id as player_id,intensity, "
						+ "sum(case when intensity=0 "
						+ " then (accI.distance) else 0 end) as standing_distance, "
						+ "sum(case when intensity=0 "
						+ " then (accI.ts_stop - accI.ts_start)/1000000000 else 0 end) as standing_time,"
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
						+ " then (accI.ts_stop - accI.ts_start)/1000000000 else 0 end) as sprint_time "
						+ " from accIqq.win:time(300000 sec) accI"
						+ " group by accI.player_id");
		if (listener != null)
			this.statement2.addListener(listener);
	}
}