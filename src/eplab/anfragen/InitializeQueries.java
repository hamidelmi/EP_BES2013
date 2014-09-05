<<<<<<< HEAD
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
		
		configuration.addEventType("PositionEvent", Event.class.getName());
		
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(configuration);
		
		 new NewPostionStatement(epService.getEPAdministrator(), new NewPostionListener());
		
		 new ValidPostionStatement(epService.getEPAdministrator(), new ValidPostionListener());

		 new OutOfPlayStatement(epService.getEPAdministrator(), new OutOfPlayListener());

		 new PlayerWindowStatement(epService.getEPAdministrator());

		 new BallTouchStatement(epService.getEPAdministrator(), new BallTouchListener());
		 
		 new PlayerBallContactStatement(epService.getEPAdministrator(), new PlayerBallContactListener());

		 new BallPossessionStatement(epService.getEPAdministrator(), new BallPossessionListener());

		 new BallPossIntervalStatement(epService.getEPAdministrator(), new BallPossIntervalListener());

		 new PlayerBallPossStatement(epService.getEPAdministrator(), new PlayerBallPossListener());

		 new TeamBallPossessionStatement(epService.getEPAdministrator(), new TeamBallPossessionListener());

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
	//    System.setOut(printOut);
	    
	    new InitializeQueries().initialize();
	  }
	

}
=======
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
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

import eplab.bodenobjekte.Event;
import eplab.bodenobjekte.GameIntervals;

public class InitializeQueries {

	public void initialize() {

		GameIntervals gameIntervals = new GameIntervals();
		gameIntervals.ParseGameInterruptionsFile(Settings.matchIntervalFilePath);
		Game game = new Game();

		Configuration configuration = new Configuration();
		// configuration.getEngineDefaults().getThreading().setInternalTimerEnabled(false);

		configuration.addEventType("Event", Event.class.getName());

		EPServiceProvider epServiceProvider = EPServiceProviderManager
				.getDefaultProvider(configuration);

		// new ValidBallPostionStatement(epService.getEPAdministrator(),
		// new ValidBallPostionListener());

		PositionStatement positionStatement = new PositionStatement(
				epServiceProvider.getEPAdministrator(), new PositionListener());
		PlayerLocationStatement playerLocationStatement = new PlayerLocationStatement(
				epServiceProvider.getEPAdministrator(),
				new PlayerLocationListener());
		RelevantPositionStatement relevantPositionStatement = new RelevantPositionStatement(
				epServiceProvider.getEPAdministrator(),
				new RelevantPositionListener());
		BallContactStatement ballContactStatement = new BallContactStatement(
				epServiceProvider.getEPAdministrator(),
				new BallContactListener());
		ContactStatement contactStatement = new ContactStatement(
				epServiceProvider.getEPAdministrator(), new ContactListener());
		PauseIntervalStatement pauseIntervalStatement = new PauseIntervalStatement(
				epServiceProvider.getEPAdministrator(),
				new PauseIntervalListener());
		NotInGameStatement notInGameStatement = new NotInGameStatement(
				epServiceProvider.getEPAdministrator(), new NotInGameListener());
		// BallPossessionStatement ballPossessionStatement = new
		// BallPossessionStatement(epServiceProvider.getEPAdministrator(), new
		// BallPossessionListener());
		GoalStatement goalStatement = new GoalStatement(
				epServiceProvider.getEPAdministrator(), new GoalListener());

		DataFileParser dataFileParser = new DataFileParser();
		Event currentEvent = dataFileParser.createNewEvent();
		while (currentEvent != null) {
			epServiceProvider.getEPRuntime().sendEvent(currentEvent);
			currentEvent = dataFileParser.createNewEvent();
		}

	}

	public static void main(String[] args) {
		// OutputStream output = null;
		// try
		// {
		// output = new FileOutputStream("output.txt");
		// }
		// catch (FileNotFoundException e)
		// {
		// e.printStackTrace();
		// }
		// PrintStream printOut = new PrintStream(output);
		// System.setOut(printOut);

		new InitializeQueries().initialize();
	}

}
>>>>>>> 3f0b74b9083d932c989cf6e3312d7b005192ac87
