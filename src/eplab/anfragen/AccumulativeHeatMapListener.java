package eplab.anfragen;
 
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class AccumulativeHeatMapListener implements UpdateListener
{
	protected int iSecs = 0;
	
	public void update(EventBean[] newEvents, EventBean[] oldEvents)
	{
		OutputStream output = null;
	    try
	    {
	      output = new FileOutputStream("C:\\Users\\SAMIR SAHU\\git\\EP_BES2013\\src\\eplab\\GUI\\output_"+ this.iSecs +".txt");
	    }
	    catch (FileNotFoundException e)
	    {
	      e.printStackTrace();
	    }
	    PrintStream printOut = new PrintStream(output);
	    System.setOut(printOut);
	    
		if (newEvents == null) {
			return;
		}
		for (EventBean theEvent : newEvents) {
			System.out.println(theEvent.getUnderlying());
		}
	}

	public void SetSeconds(int iSecs)
	{
		this.iSecs = iSecs;
	}

}