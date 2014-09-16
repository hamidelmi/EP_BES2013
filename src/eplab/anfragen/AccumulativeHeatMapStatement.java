package eplab.anfragen;
 
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;
 
public class AccumulativeHeatMapStatement
{
	private EPStatement statement_10sec;
	private EPStatement statement_1min;
	private EPStatement statement_5min;
	private EPStatement statement_10min;
   
	public AccumulativeHeatMapStatement(EPAdministrator admin, UpdateListener listener, int iSecs)
	{
		String stmt_10sec = GetAggregationString(10);
		this.statement_10sec = admin.createEPL(stmt_10sec);
		
		String stmt_1min = GetAggregationString(60);
		this.statement_1min = admin.createEPL(stmt_1min);
     
		String stmt_5min = GetAggregationString(300);
		this.statement_5min = admin.createEPL(stmt_5min);
		
		String stmt_10min = GetAggregationString(600);
		this.statement_10min = admin.createEPL(stmt_10min);
     
		this.addListener(listener, iSecs);
	}
  
	public String GetAggregationString(int iNumOfSecs)
	{
		String seconds = null;
		String winlength = "";
		String totaltime = "";
		switch (iNumOfSecs)
		{
			case 10: 
				seconds = "10";
				winlength = ".win:ext_timed(currts, 10 sec)";
				totaltime = "10";
				break;
			case 60: 
				seconds = "60";
				winlength = ".win:time(60 sec)";
				totaltime = "60";
				break;
			case 300: 
				seconds = "300";
				winlength = ".win:time(300 sec)";
				totaltime = "300";
				break;
			case 600: 
				seconds = "600";
				winlength = ".win:time(600 sec)";
				totaltime = "600";
				break;
		}
		String resultString = "insert into AccumulativeHeatMapEvent_" + seconds 
							+ " select dEvent.currts as ts, "
							+ " dEvent.timeInGame as timeInGame, "
							+ " dEvent.playerName as playerName, "
							+ " dEvent.cell_x1 as cell_x1, "
							+ " dEvent.cell_y1 as cell_y1, "
							+ " dEvent.cell_x2 as cell_x2, "
							+ " dEvent.cell_y2 as cell_y2, "
							+ " sum(dEvent.dts)  as time_in_cell, "
							+ " (sum(dEvent.dts) / " + totaltime + ") * 100.0  as percent_time_in_time_cell "
							+ " from HeatMapDeltaPositionEvent" + winlength + " as dEvent" 
							+ " group by playerName, cell_x1, cell_y1, cell_x2, cell_y2" 
							+ " output last every 1 min"
							+ " order by playerName";

		return resultString;
	}

	public void addListener(UpdateListener listener, int iSecs)
	{
		((AccumulativeHeatMapListener)listener).SetSeconds(iSecs);
		switch (iSecs)
		{
			case 10000: 
				this.statement_10sec.addListener(listener);
				break;
			case 60000: 
				this.statement_1min.addListener(listener);
				break;
			case 300000: 
				this.statement_5min.addListener(listener);
				break;
			case 600000: 
				this.statement_10min.addListener(listener);
				break;
		}
	}
}