package eplab.anfragen;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class ContactListener implements UpdateListener {
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents == null) {
			return;
		}
		for (EventBean theEvent : newEvents) {
//			System.out.println("Contact event received:"
//					+ theEvent.getUnderlying());
		}
	}

	//private static final Log log = LogFactory.getLog(EnrichPosListener.class);
}
