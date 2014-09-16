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
			
			try {
			    PrintWriter out = new PrintWriter(new BufferedWriter
			    		(new FileWriter(eplab.anfragen.Settings.playersPossOutPath+theEvent
			    				.get("playerName").toString(), true)));
			    out.println(theEvent.getUnderlying());
			    out.close();
			} catch (IOException e) {
			}
		}
	}
}
