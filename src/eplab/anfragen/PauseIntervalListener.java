package eplab.anfragen;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class PauseIntervalListener 
implements UpdateListener
{
public void update(EventBean[] newEvents, EventBean[] oldEvents)
{
  if (newEvents == null) {
    return;
  }
  for (EventBean theEvent : newEvents)
  {
    double tsInGame = Double.parseDouble(theEvent.get("ts").toString());
    //System.out.println("Game Pause Event pos received: at " + tsInGame + " : " + theEvent.getUnderlying());
  }
}

//private static final Log log = LogFactory.getLog(PauseIntervalListener.class);
}
