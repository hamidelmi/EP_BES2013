package epdebs.parser;

//import org.omg.CORBA.Environment;

public class Main {

	public static String dataFilePath = "E:\\TUD\\TUD_Study\\Event Processing\\LAB\\full-game.txt";
	public static String metadataFilePath = "E:\\TUD\\TUD_Study\\Event Processing\\LAB\\metadata.txt";
	static int count = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dataFilePath = System.getenv("dataFilePath");
		metadataFilePath = System.getenv("metadataFilePath");
		DataFileParser df = new DataFileParser();
		
		Event ev = null;
		do {
			ev = df.createNewEvent();
			count++;
			System.out.println("Count =" + count);
		} while (ev != null);

	}

}
