package eplab.anfragen;


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

//		PositionStatement positionStatement = new PositionStatement(
//				epServiceProvider.getEPAdministrator(), new PositionListener());
//		PlayerLocationStatement playerLocationStatement = new PlayerLocationStatement(
//				epServiceProvider.getEPAdministrator(),
//				new PlayerLocationListener());
////		RelevantPositionStatement relevantPositionStatement = new RelevantPositionStatement(
////				epServiceProvider.getEPAdministrator(),
////				new RelevantPositionListener());
//		BallContactStatement ballContactStatement = new BallContactStatement(
//				epServiceProvider.getEPAdministrator(),
//				new BallContactListener());
//		ContactStatement contactStatement = new ContactStatement(
//				epServiceProvider.getEPAdministrator(), new ContactListener());
//		PauseIntervalStatement pauseIntervalStatement = new PauseIntervalStatement(
//				epServiceProvider.getEPAdministrator(),
//				new PauseIntervalListener());
//		NotInGameStatement notInGameStatement = new NotInGameStatement(
//				epServiceProvider.getEPAdministrator(), new NotInGameListener());
//		// BallPossessionStatement ballPossessionStatement = new
//		// BallPossessionStatement(epServiceProvider.getEPAdministrator(), new
//		// BallPossessionListener());
//		GoalStatement goalStatement = new GoalStatement(
//				epServiceProvider.getEPAdministrator(), new GoalListener());
		
		NewPostionStatement np=new NewPostionStatement(epServiceProvider.getEPAdministrator(), new NewPostionListener());
		PlayerWindowStatement pbc=new PlayerWindowStatement(epServiceProvider.getEPAdministrator());
		ValidPostionStatement vp=new ValidPostionStatement(epServiceProvider.getEPAdministrator(), new ValidPostionListener());
		BallTouchStatement bt=new BallTouchStatement(epServiceProvider.getEPAdministrator(), new BallTouchListener());
		PlayerBallContactStatement bc=new PlayerBallContactStatement(epServiceProvider.getEPAdministrator(), new PlayerBallContactListener());
		OutOfPlayStatement op=new OutOfPlayStatement(epServiceProvider.getEPAdministrator(), new OutOfPlayListener());
		BallPossIntervalStatement bs=new BallPossIntervalStatement(epServiceProvider.getEPAdministrator(), new BallPossIntervalListener());
		RelevantPositionStatement relevantPositionStatement = new RelevantPositionStatement(epServiceProvider.getEPAdministrator(),new RelevantPositionListener());
		GoalStatement goalStatement = new GoalStatement(epServiceProvider.getEPAdministrator(), new GoalListener());
		

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
