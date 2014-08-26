package esper;

import com.espertech.esper.client.*;

import epdebs.game_objects.Game;
import epdebs.parser.DataFileParser;
import epdebs.parser.Event;
import epdebs.parser.Main;
import epdebs.parser.Settings;
import epdebs.queries.IntensityListener;
import epdebs.queries.IntensityStatement;

import java.util.Random;
import java.util.Date;


public class ExampleMain {

	public static class CEPListener implements UpdateListener {

		public void update(EventBean[] newData, EventBean[] oldData) {
			System.out.println("Event received: " + newData[0].getUnderlying());
		}
	}

	public static void main(String[] args) {
		Settings.dataFilePath = System.getenv("dataFilePath");
		Settings.metadataFilePath = System.getenv("metadataFilePath");

		// The Configuration is meant only as an initialization-time object.
		Configuration cepConfig = new Configuration();
		cepConfig.addEventType("Event", Event.class.getName());
		EPServiceProvider cep = EPServiceProviderManager.getProvider(
				"myCEPEngine", cepConfig);
		EPRuntime cepRT = cep.getEPRuntime();
		EPAdministrator cepAdm = cep.getEPAdministrator();
		IntensityStatement inetsityEventStmt = new IntensityStatement(cepAdm,
				new IntensityListener());

		DataFileParser parser = new DataFileParser();
		Game game = Game.Singleton();

		Event curPosEvent = parser.createNewEvent();

		int c = 0;
		while (curPosEvent != null && c++ < 10) {
			cep.getEPRuntime().sendEvent(curPosEvent);

			curPosEvent = parser.createNewEvent();
		}
	}
}
