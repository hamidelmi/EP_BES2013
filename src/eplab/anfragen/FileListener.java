package eplab.anfragen;

import java.io.PrintWriter;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class FileListener implements UpdateListener {
	static PrintWriter output;
	String fileName;

	public FileListener(String fileName) {
		this.fileName = fileName;
		try {
			if (output == null)
				output = new PrintWriter(fileName);
		} catch (Exception ex) {
			System.out.println("ERROR:" + ex);
		}
	}

	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if (newEvents == null)
			return;

		for (EventBean event : newEvents) {
			output.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\r\n",
					event.get("ts"), event.get("player_id"),
					event.get("standing_time"), event.get("standing_distance"),
					event.get("trot_time"), event.get("trot_distance"),
					event.get("low_time"), event.get("medium_time"),
					event.get("medium_distance"), event.get("high_time"),
					event.get("high_distance"), event.get("sprint_time"),
					event.get("sprint_distance"));
		}
	}
}