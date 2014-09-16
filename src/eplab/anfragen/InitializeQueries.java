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
import eplab.bodenobjekte.GameIntervals;
import eplab.anfragen.DataFileParser;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

public class InitializeQueries {

	public void initialize() {
//		GameIntervals gameIntervals = new GameIntervals();
//		gameIntervals.ParseGameInterruptionsFile(Settings.matchIntervalFilePath);
//		GameIntervals gameIntervals = new GameIntervals();
//		String path = System.getenv("dataFilePath");
//		if (path == null || path.compareTo("") == 0)
//			path = Settings.dataFilePath;
//		else
//			Settings.dataFilePath = path;
//		gameIntervals.ParseGameInterruptionsFile(path);
		new Game();

		Configuration configuration = new Configuration();
//		 configuration.getEngineDefaults().getThreading().setInternalTimerEnabled(false);

		configuration.addEventType("Event", Event.class.getName());

		EPServiceProvider epService = EPServiceProviderManager
				.getDefaultProvider(configuration);

		 new NewPostionStatement(epService.getEPAdministrator(),
		 new NewPostionListener());
		
		 new ValidPostionStatement(epService.getEPAdministrator(),
		 new ValidPostionListener());
		
		 new OutOfPlayStatement(epService.getEPAdministrator(),
		 new OutOfPlayListener());
		
		 new PlayerWindowStatement(epService.getEPAdministrator());
		
		 new BallTouchStatement(epService.getEPAdministrator(),
		 new BallTouchListener());
		
		 new PlayerBallContactStatement(epService.getEPAdministrator(),
		 new PlayerBallContactListener());
		
		 new BallPossIntervalStatement(epService.getEPAdministrator(),
		 new BallPossIntervalListener());
		
		 new PlayerBallPossStatement(epService.getEPAdministrator(),
		 new PlayerBallPossListener());
		
		 new TeamBallPossessionStatement(epService.getEPAdministrator(),
		 new TeamBallPossessionListener());

		// PositionStatement positionStatement = new PositionStatement(
		// epService.getEPAdministrator(), new PositionListener());
		// PlayerLocationStatement playerLocationStatement = new
		// PlayerLocationStatement(
		// epService.getEPAdministrator(), new PlayerLocationListener());
		// RelevantPositionStatement relevantPositionStatement = new
		// RelevantPositionStatement(
		// epService.getEPAdministrator(), new RelevantPositionListener());
		// BallContactStatement ballContactStatement = new BallContactStatement(
		// epService.getEPAdministrator(), new BallContactListener());
		// ContactStatement contactStatement = new ContactStatement(
		// epService.getEPAdministrator(), new ContactListener());
		// PauseIntervalStatement pauseIntervalStatement = new
		// PauseIntervalStatement(
		// epService.getEPAdministrator(), new PauseIntervalListener());
		// NotInGameStatement notInGameStatement = new NotInGameStatement(
		// epService.getEPAdministrator(), new NotInGameListener());

//		AccumulativeIntensityStatement accumulativeIntensityStatement = new AccumulativeIntensityStatement(
//				epService.getEPAdministrator(), new FileListener(
//						"output_Q1.txt"));

		
	new HeatPositionStatement(epService.getEPAdministrator(), new HeatPositionListener());
		
		new HeatMapDeltaPositionStatement(epService.getEPAdministrator(), new HeatMapDeltaPositionListener());
		
		new AccumulativeHeatMapStatement(epService.getEPAdministrator(), new AccumulativeHeatMapListener(), 10000);
		new AccumulativeHeatMapStatement(epService.getEPAdministrator(), new AccumulativeHeatMapListener(), 60000);
		new AccumulativeHeatMapStatement(epService.getEPAdministrator(), new AccumulativeHeatMapListener(), 300000);
		new AccumulativeHeatMapStatement(epService.getEPAdministrator(), new AccumulativeHeatMapListener(), 600000);
	
		DataFileParser df = new DataFileParser();
		Event currentEvent = df.createNewEvent();
		while (currentEvent != null) {
			epService.getEPRuntime().sendEvent(currentEvent);
			currentEvent = df.createNewEvent();
		}

	}

	public static void main(String[] args) {
		OutputStream output = null;
		try {
			output = new FileOutputStream("output.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PrintStream printOut = new PrintStream(output);
		 System.setOut(printOut);

		new InitializeQueries().initialize();
	}

}
