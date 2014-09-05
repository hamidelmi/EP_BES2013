package eplab.anfragen;

import eplab.bodenobjekte.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class DataFileParser {

	BufferedReader br = null;
	String eventLine = null;
	StringTokenizer tokenizer = null;
	Event currentEvent = null;

	public DataFileParser() {
		try {
			br = new BufferedReader(new FileReader(Settings.dataFilePath));
<<<<<<< HEAD
			System.out.println("File read successfully !!!!" + Settings.dataFilePath);
		} 
		catch (IOException e) 
		{
=======
			//System.out.println("File read successfully !!!!");
		} catch (IOException e) {
>>>>>>> 3f0b74b9083d932c989cf6e3312d7b005192ac87
			e.printStackTrace();
		}
	}

	public Event createNewEvent() {
		try {
			eventLine = br.readLine();
<<<<<<< HEAD
//			 System.out.println("Readline successfull ");
//			 System.out.println(eventLine);
		}
		catch (IOException e) 
		{
=======
			//System.out.println("Readline successfull ");
			//System.out.println(eventLine);
		} catch (IOException e) {
>>>>>>> 3f0b74b9083d932c989cf6e3312d7b005192ac87
			e.printStackTrace();
		}
		if (eventLine == null)
			return null;
		tokenizer = new StringTokenizer(eventLine, ",");
		int sid = Integer.parseInt(tokenizer.nextToken());
		long ts = Long.parseLong(tokenizer.nextToken());
		int x = Integer.parseInt(tokenizer.nextToken());
		int y = Integer.parseInt(tokenizer.nextToken());
		int z = Integer.parseInt(tokenizer.nextToken());
		int abs_v = Integer.parseInt(tokenizer.nextToken());
		int abs_a = Integer.parseInt(tokenizer.nextToken());
		int vx = Integer.parseInt(tokenizer.nextToken());
		int vy = Integer.parseInt(tokenizer.nextToken());
		int vz = Integer.parseInt(tokenizer.nextToken());
		int ax = Integer.parseInt(tokenizer.nextToken());
		int ay = Integer.parseInt(tokenizer.nextToken());
		int az = Integer.parseInt(tokenizer.nextToken());

		currentEvent = new Event(sid, ts, x, y, z, abs_v, vx, vy, vz, abs_a,
				ax, ay, az);
		return currentEvent;

	}

}
