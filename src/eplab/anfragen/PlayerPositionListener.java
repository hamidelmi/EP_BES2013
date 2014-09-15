package eplab.anfragen;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

<<<<<<< HEAD:src/eplab/anfragen/PlayerPositionListener.java
public class PlayerPositionListener implements UpdateListener {
=======
public class ValidPostionListener implements UpdateListener {
>>>>>>> 3d579a8bf38697ccccbd82667354f22d359ebc6c:src/eplab/anfragen/ValidPostionListener.java

	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents == null) {
			return;
		}
		for (EventBean theEvent : newEvents) {
<<<<<<< HEAD:src/eplab/anfragen/PlayerPositionListener.java
			System.out.println("Valid position received :"
					+ theEvent.getUnderlying());
=======
//			System.out.println("Valid position received :" + theEvent.getUnderlying());
>>>>>>> 3d579a8bf38697ccccbd82667354f22d359ebc6c:src/eplab/anfragen/ValidPostionListener.java
		}
	}
}
