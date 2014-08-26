package epdebs.queries;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import com.espertech.esper.client.Configuration;

import epdebs.game_objects.Game;
import epdebs.parser.Event;
import epdebs.parser.DataFileParser;


import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

public class InitializeQueries {

	public void run() {
		
		new Game();
		Configuration configuration = new Configuration();
//		configuration.getEngineDefaults().getThreading().setInternalTimerEnabled(false);
		
		configuration.addEventType("PositionEvent", Event.class.getName());
		
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(configuration);
		
		
		
		ValidPostionStatement vp = new ValidPostionStatement(epService.getEPAdministrator(), new ValidPostionListener());
		
		DataFileParser df = new DataFileParser();
		Event currentEvent = df.createNewEvent();
		 while (currentEvent != null)
		 {
		 epService.getEPRuntime().sendEvent(currentEvent);
		 currentEvent = df.createNewEvent();
		 }
		
	}
	
	public static void main(String[] args)
	  {
	    OutputStream output = null;
	    try
	    {
	      output = new FileOutputStream("output.txt");
	    }
	    catch (FileNotFoundException e)
	    {
	      e.printStackTrace();
	    }
	    PrintStream printOut = new PrintStream(output);
	    System.setOut(printOut);
	    
	    InitializeQueries startQuery = new InitializeQueries();
	    startQuery.run();
	  }
	

}
