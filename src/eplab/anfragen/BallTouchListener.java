package eplab.anfragen;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class BallTouchListener implements UpdateListener {

	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents == null) {
			return;
		}
		for (EventBean theEvent : newEvents) {
			//if(theEvent.get("isTowardGoal").toString()=="0")
//			try {
//				PrintWriter out = new PrintWriter("d:\\filename.txt");
//				out.append((CharSequence) theEvent.getUnderlying());
//				//out.close();
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if(theEvent.getUnderlying().toString().contains("isTowardGoal=1"))
//			System.out.println("ball touch event received at :" + theEvent.get("ts") + ":"
//					+ theEvent.getUnderlying());
		}
	}
}
