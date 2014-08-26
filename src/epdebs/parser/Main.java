package epdebs.parser;

import java.awt.*;
import java.awt.geom.Point2D;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

import epdebs.game_objects.Game;
import epdebs.queries.BallContactListener;
import epdebs.queries.BallContactStatement;
import epdebs.queries.BallPossessionListener;
import epdebs.queries.BallPossessionStatement;
import epdebs.queries.ContactListener;
import epdebs.queries.ContactStatement;
import epdebs.queries.GoalListener;
import epdebs.queries.GoalStatement;
import epdebs.queries.IntensityListener;
import epdebs.queries.IntensityStatement;
import epdebs.queries.PauseIntervalListener;
import epdebs.queries.PauseIntervalStatement;
import epdebs.queries.PlayerLocationListener;
import epdebs.queries.PlayerLocationStatement;
import epdebs.queries.PositionListener;
import epdebs.queries.PositionStatement;
import epdebs.queries.RelevantPositionListener;
import epdebs.queries.RelevantPositionStatement;

//import org.omg.CORBA.Environment;

public class Main {
	
	public static void main(String[] args) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		//dataFilePath = System.getenv("dataFilePath");
		//metadataFilePath = System.getenv("metadataFilePath");
//		DataFileParser dataFileParser = new DataFileParser();
//		
//		Event event = null;
//		do {
//			event = dataFileParser.createNewEvent();
//			
//			//TODO: query
//		   } while (event != null);
=======
		Match.Initialize();
		
		Configuration configuration = new Configuration();
		configuration.getEngineDefaults().getThreading().setInternalTimerEnabled(false);
		configuration.addEventType("Event", Event.class.getName());
		
		EPServiceProvider epServiceProvider = EPServiceProviderManager.getProvider("myCEPEngine", configuration);
		
		PositionStatement positionStatement = new PositionStatement(epServiceProvider.getEPAdministrator(), new PositionListener());
		PlayerLocationStatement playerLocationStatement = new PlayerLocationStatement(epServiceProvider.getEPAdministrator(), new PlayerLocationListener());
		RelevantPositionStatement relevantPositionStatement = new RelevantPositionStatement(epServiceProvider.getEPAdministrator(), new RelevantPositionListener());
		BallContactStatement ballContactStatement = new BallContactStatement(epServiceProvider.getEPAdministrator(), new BallContactListener());
		ContactStatement contactStatement = new ContactStatement(epServiceProvider.getEPAdministrator(), new ContactListener());
		PauseIntervalStatement pauseIntervalStatement = new PauseIntervalStatement(epServiceProvider.getEPAdministrator(), new PauseIntervalListener());
		//BallPossessionStatement ballPossessionStatement = new BallPossessionStatement(epServiceProvider.getEPAdministrator(), new BallPossessionListener());
		GoalStatement goalStatement = new GoalStatement(epServiceProvider.getEPAdministrator(), new GoalListener());
		
		DataFileParser parser = new DataFileParser();
>>>>>>> origin/master

		Event curPosEvent = parser.createNewEvent();

		while (curPosEvent != null) {
			epServiceProvider.getEPRuntime().sendEvent(curPosEvent);

			curPosEvent = parser.createNewEvent();
		}
	}
}
