package eplab.anfragen;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class NewPostionListener implements UpdateListener {

	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents == null) {
			return;
		}
		for (EventBean theEvent : newEvents) {
//			System.out.println("New position received :" + theEvent.getUnderlying());
		}
	}
}
