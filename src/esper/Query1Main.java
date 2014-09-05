<<<<<<< HEAD
//package esper;
//
//import com.espertech.esper.client.Configuration;
//import com.espertech.esper.client.EPAdministrator;
//import com.espertech.esper.client.EPRuntime;
//import com.espertech.esper.client.EPServiceProvider;
//import com.espertech.esper.client.EPServiceProviderManager;
//import com.espertech.esper.client.EventBean;
//import com.espertech.esper.client.UpdateListener;
//
//import epdebs.game_objects.Game;
//import epdebs.parser.DataFileParser;
//import epdebs.parser.Event;
//import epdebs.parser.Settings;
//import eplab.anfragen.AccumulativeIntensityStatement;
//import eplab.anfragen.EchoListener;
//import eplab.anfragen.IntensityStatement;
//
//public class Query1Main {
//
//	public static class CEPListener implements UpdateListener {
//
//		public void update(EventBean[] newData, EventBean[] oldData) {
//			System.out.println("Event received: " + newData[0].getUnderlying());
//		}
//	}
//
//	public static void main(String[] args) {
//		Settings.dataFilePath = System.getenv("dataFilePath");
//		Settings.metadataFilePath = System.getenv("metadataFilePath");
//
//		// The Configuration is meant only as an initialization-time object.
//		Configuration cepConfig = new Configuration();
//		cepConfig.addEventType("Event", Event.class.getName());
//		EPServiceProvider cep = EPServiceProviderManager.getProvider(
//				"myCEPEngine", cepConfig);
//		EPRuntime cepRT = cep.getEPRuntime();
//		EPAdministrator cepAdm = cep.getEPAdministrator();
//		IntensityStatement intensityStatement = new IntensityStatement(cepAdm,
//				null);
//
//		AccumulativeIntensityStatement accumulativeIntensityStatement = new AccumulativeIntensityStatement(
//				cepAdm, new EchoListener());
//
//		DataFileParser parser = new DataFileParser();
//		Game game = Game.Singleton();
//
//		Event curPosEvent = parser.createNewEvent();
//
//		int c = 0;
//		while (curPosEvent != null && c++ < 100) {
//			cep.getEPRuntime().sendEvent(curPosEvent);
//
//			curPosEvent = parser.createNewEvent();
//		}
//	}
//}
=======
package esper;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import eplab.anfragen.AccumulativeIntensityStatement;
import eplab.anfragen.DataFileParser;
import eplab.anfragen.EchoListener;
import eplab.anfragen.FileListener;
import eplab.anfragen.Game;
import eplab.anfragen.Settings;
import eplab.bodenobjekte.Event;

public class Query1Main {

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
		// IntensityStatement intensityStatement = new
		// IntensityStatement(cepAdm,
		// new EchoListener());

		AccumulativeIntensityStatement accumulativeIntensityStatement = new AccumulativeIntensityStatement(
				cepAdm, new FileListener("c:\1.txt"));

		DataFileParser parser = new DataFileParser();
		Game game = Game.Singleton();

		Event curPosEvent = parser.createNewEvent();

		int c = 0;
		while (curPosEvent != null && c++ < 10000000) {
			if (curPosEvent.getsid() == 59 || curPosEvent.getsid() == 28)
				cep.getEPRuntime().sendEvent(curPosEvent);

			curPosEvent = parser.createNewEvent();
		}
	}
}
>>>>>>> 3f0b74b9083d932c989cf6e3312d7b005192ac87
