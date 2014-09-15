package eplab.anfragen;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class RelevantPositionListener implements UpdateListener {
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents == null) {
			return;
		}
		for (EventBean theEvent : newEvents) {
			double tsInGame = Double.parseDouble(theEvent.get("ts").toString());
//			System.out.println("RelevantRawPositionEvent pos received: at "
//					+ tsInGame + " : " + theEvent.getUnderlying());
		}
	}

	private static final Log log = LogFactory
			.getLog(RelevantPositionListener.class);
}
