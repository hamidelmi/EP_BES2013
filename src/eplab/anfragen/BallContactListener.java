package eplab.anfragen;


import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import java.io.PrintStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BallContactListener
  implements UpdateListener
{
  public void update(EventBean[] newEvents, EventBean[] oldEvents)
  {
    if (newEvents == null) {
      return;
    }
    for (EventBean theEvent : newEvents) {
//      System.out.println("Ball contact event received at " + theEvent.get("ts") + " : " + theEvent.getUnderlying());
    }
  }
  
  //private static final Log log = LogFactory.getLog(EnrichPosListener.class);
}
