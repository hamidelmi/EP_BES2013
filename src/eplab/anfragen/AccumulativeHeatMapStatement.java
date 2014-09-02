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
	private EPStatement statement_total;
   
	public AccumulativeHeatMapStatement(EPAdministrator admin)
	{
		String stmt_10sec = GetAggregationString(10000);
		this.statement_10sec = admin.createEPL(stmt_10sec);
		
		String stmt_1min = GetAggregationString(60000);
		this.statement_1min = admin.createEPL(stmt_1min);
     
		String stmt_5min = GetAggregationString(300000);
		this.statement_5min = admin.createEPL(stmt_5min);
		
		String stmt_10min = GetAggregationString(600000);
		this.statement_10min = admin.createEPL(stmt_10min);
     
		String stmt_total = GetAggregationString(0);
		this.statement_total = admin.createEPL(stmt_total);
	}
  
	public String GetAggregationString(int iNumOfMiliSecs)
	{
		String minutes = null;
		String winlength = "";
		String totaltime = "";
		switch (iNumOfMiliSecs)
		{
			case 10000: 
				minutes = "1";
				winlength = ".win:time(10 sec)";
				totaltime = "10000";
				break;
			case 60000: 
				minutes = "1";
				winlength = ".win:time(60 sec)";
				totaltime = "60000";
				break;
			case 300000: 
				minutes = "5";
				winlength = ".win:time(300 sec)";
				totaltime = "300000";
				break;
			case 600000: 
				minutes = "10";
				winlength = ".win:time(600 sec)";
				totaltime = "600000";
				break;
			default: 
				minutes = "total";
				winlength = "";
				totaltime = "max(dEvent.timeInGame)";
		}
		String resultString = "insert into AggHeatMapEvent_" + minutes 
							+ " select dEvent.playerName as playerName, "
							+ " dEvent.resolution as resolution, " 
							+ " dEvent.cell_x1 as cell_x1, "
							+ " dEvent.cell_y1 as cell_y1, " 
							+ " dEvent.cell_x2 as cell_x2, "
							+ " dEvent.cell_y2 as cell_y2, " 
							+ " (sum(dEvent.dts) / " + totaltime + ") * 100.0  as percent_time_in_time_cell" 
							+ " from DeltaHPEvent" + winlength + " as dEvent" 
							+ " group by playerName, resolution, cell_x1, cell_y1, cell_x2, cell_y2" 
							+ " output last every 1 sec"
							+ " order by playerName, resolution";

		return resultString;
	}

	public void addListener(UpdateListener listener, int iMiliSecs)
	{
		((AccumulativeHeatMapListener)listener).SetMinutes(iMiliSecs);
		switch (iMiliSecs)
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
			default: 
				this.statement_total.addListener(listener);
		}
	}
}