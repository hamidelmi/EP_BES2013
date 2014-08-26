package epdebs.queries;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class PositionListener implements UpdateListener {
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents == null) {
			return;
		}
		for (EventBean theEvent : newEvents) {
//			System.out.println("position received:"
//					+ theEvent.getUnderlying());
		}
	}

	//private static final Log log = LogFactory.getLog(PositionListener.class);
}
