package eplab.anfragen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class PlayerBallPossListener implements UpdateListener {

	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents == null) {
			return;
		}
		for (EventBean theEvent : newEvents) {
			
//			System.out.println("Player "+ theEvent.get("palyerName") +" -----------------ball possession aggregated for player:"
//					+ theEvent.getUnderlying());
			String myPath = "E:\\TUD\\TUD_Study\\Event Processing\\LAB\\output\\";
			try {
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(myPath+theEvent.get("palyerName").toString(), true)));
			    out.println(theEvent.getUnderlying());
			    out.close();
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		}
	}
}
