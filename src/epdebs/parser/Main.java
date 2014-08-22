package epdebs.parser;

//import org.omg.CORBA.Environment;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//dataFilePath = System.getenv("dataFilePath");
		//metadataFilePath = System.getenv("metadataFilePath");
		DataFileParser df = new DataFileParser();
		
		Event ev = null;
		do {
			ev = df.createNewEvent();
			
			//TODO: query
		   } while (ev != null);

	}

}
