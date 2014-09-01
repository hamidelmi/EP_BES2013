package eplab.anfragen;
 
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class AccumulativeHeatMapListener implements UpdateListener
{
	protected int iMiliSecs = 0;
   
	public void update(EventBean[] newEvents, EventBean[] oldEvents)
	{
		if (newEvents == null) {
			return;
		}
		System.out.println("Heat Map Statistics for " + this.iMiliSecs + " :");
		for (EventBean theEvent : newEvents) {
			System.out.println("Statistics: " + theEvent.getUnderlying());
		}
	}

	public void SetMinutes(int iMiliSecs)
	{
		this.iMiliSecs = iMiliSecs;
	}

}