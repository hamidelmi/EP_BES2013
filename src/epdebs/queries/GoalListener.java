package epdebs.queries;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class GoalListener implements UpdateListener
	  {
	    public void update(EventBean[] newEventBean, EventBean[] oldEventBean)
	    {
	      if (newEventBean == null)
	      {
	        return;
	      }
	      for (EventBean theEvent : newEventBean) {
	        System.out.println("Goal: " + theEvent.getUnderlying());
	      }
	    }
	    
	    private static final Log log = LogFactory.getLog(GoalListener.class);

}
