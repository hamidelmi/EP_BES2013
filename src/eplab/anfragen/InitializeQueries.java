package eplab.anfragen;

/*
 * This class is used for initializing the project and the queries 
 * i.e. fetch and event from dataFileParser and create objects of all statements and 
 * feed the generated stream to the listeners and generate output
 * 
 */


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import com.espertech.esper.client.Configuration;

import eplab.bodenobjekte.Event;
import eplab.anfragen.DataFileParser;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

public class InitializeQueries {

	public void initialize() {
		
		new Game();
		
		Configuration configuration = new Configuration();
//		configuration.getEngineDefaults().getThreading().setInternalTimerEnabled(false);
		
		configuration.addEventType("Event", Event.class.getName());
		
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(configuration);
		
		new HeatPositionStatement(epService.getEPAdministrator(), new HeatPositionListener());
		
		new HeatMapDeltaPositionStatement(epService.getEPAdministrator(), new HeatMapDeltaPositionListener());
		
		new AccumulativeHeatMapStatement(epService.getEPAdministrator(), new AccumulativeHeatMapListener(), 10000);
		new AccumulativeHeatMapStatement(epService.getEPAdministrator(), new AccumulativeHeatMapListener(), 60000);
		new AccumulativeHeatMapStatement(epService.getEPAdministrator(), new AccumulativeHeatMapListener(), 300000);
		new AccumulativeHeatMapStatement(epService.getEPAdministrator(), new AccumulativeHeatMapListener(), 600000);
		
		
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
	    new InitializeQueries().initialize();
	  }
	

}