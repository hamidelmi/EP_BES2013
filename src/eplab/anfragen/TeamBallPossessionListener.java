package eplab.anfragen;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class TeamBallPossessionListener implements UpdateListener {

	 
	  public void update(EventBean[] newEvents, EventBean[] oldEvents)
	  {
	    if (newEvents == null) {
	      return;
	    }
	    System.out.println("Team possession statistics for 5 min window :");
	    for (EventBean theEvent : newEvents) {
	      System.out.println("Aggs received:" + theEvent.getUnderlying());
	    }
	  }
	 
}
