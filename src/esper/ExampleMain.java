package esper;

import com.espertech.esper.client.*;

import eplab.anfragen.*;
import eplab.bodenobjekte.*;
import eplab.anfragen.DataFileParser;
import eplab.anfragen.Game;
import eplab.anfragen.Settings;
import eplab.bodenobjekte.Event;

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
