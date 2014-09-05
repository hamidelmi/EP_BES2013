<<<<<<< HEAD
package eplab.anfragen;

/*
 * This class provides instance for eplab.bodenobjekte objects.
 * Also it holds all the utility functions needed in all queries 
 * 
 */
import eplab.bodenobjekte.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class Game {
	private static Game instance;
	protected static Team teamA, teamB;
	protected Referee referee;
	protected Ball ball;
	private HashMap<String, AccumulativeIntensity> playersAccumulativeIntensity;

	protected static int bottomField = -33960;
	protected static int leftField = 0;
	protected static int topField = 33960;
	protected static int rightField = 52483;
	protected static long tsStartFirstHalf = Long
			.parseLong("10753295594424116");
	protected static long tsEndFirstHalf = Long.parseLong("12557295594424116");
	protected static long tsStartSecondHalf = Long
			.parseLong("13086639146403495");
	protected static long tsEndSecondHalf = Long.parseLong("14879639146403495");
	protected static ArrayList<Double> beginTS = new ArrayList<Double>();
	protected static ArrayList<Double> endTS = new ArrayList<Double>();
	protected static int initTsArray = 0;

	public static Game Singleton() {
		if (instance == null)
			instance = new Game();
		return instance;
	}

	protected Game() {
		playersAccumulativeIntensity = new HashMap<String, AccumulativeIntensity>();
		List<Player> players = new ArrayList<Player>();
		players.add(new GoalKeeper(13, 14, 97, 98, "Nick Gertje"));
		players.add(new Player(47, 16, "Dennis Dotterweich"));
		players.add(new Player(49, 88, "Niklas Waelzlein"));
		players.add(new Player(19, 52, "Wili Sommer"));
		players.add(new Player(53, 54, "Philipp Harlass"));
		players.add(new Player(23, 24, "Roman Hartleb"));
		players.add(new Player(57, 58, "Erik Engelhardt"));
		players.add(new Player(59, 28, "Sandro Schneider"));

		fillAccumulativeIntensity(players);
		teamA = new Team(players);

		players = new ArrayList<Player>();

		players.add(new GoalKeeper(61, 62, 99, 100, "Leon Krapf"));
		players.add(new Player(63, 64, "Kevin Baer"));
		players.add(new Player(65, 66, "Luca Ziegler"));
		players.add(new Player(67, 68, "Ben Mueller"));
		players.add(new Player(69, 38, "Vale Reitstetter"));
		players.add(new Player(71, 40, "Christopher Lee"));
		players.add(new Player(73, 74, "Leon Heinze"));
		players.add(new Player(75, 44, "Leo Langhans"));

		fillAccumulativeIntensity(players);
		teamB = new Team(players);

		referee = new Referee(105, 106);

		ball = new Ball(new int[] { 4, 8, 10, 12 }, "ball");
	}

	private void fillAccumulativeIntensity(List<Player> players) {
		for (Player player : players) {
			playersAccumulativeIntensity.put(player.name,
					new AccumulativeIntensity());
		}
	}

	public AccumulativeIntensity getIntensity(int sensorId) {
		String playerName = getPlayer(sensorId).name;

		if (playersAccumulativeIntensity.containsKey(playerName))
			return playersAccumulativeIntensity.get(playerName);
		return null;
	}

	private int indexOfPlayer(String playerName, Team team) {
		for (int i = 0; i < team.players.size(); i++)
			if (team.players.get(i).name.equalsIgnoreCase(playerName))
				return i;
		return -1;
	}

	private int indexOfPlayer(int sensorId, Team team) {
		for (int i = 0; i < team.players.size(); i++)
			if (team.players.get(i).hasSensorId(sensorId))
				return i;
		return -1;
	}

	public String GetTeamName(String playerName) {
		if (indexOfPlayer(playerName, teamA) >= 0)
			return "teamA";
		else if (indexOfPlayer(playerName, teamB) >= 0)
			return "teamB";
		else
			return null;
	}

	public Player getPlayer(String playerName) {
		int i = indexOfPlayer(playerName, teamA);
		if (i >= 0)
			return teamA.players.get(i);
		else {
			i = indexOfPlayer(playerName, teamB);
			if (i >= 0)
				return teamB.players.get(i);
		}
		return null;
	}

	public Player getPlayer(int sensorId) {
		int i = indexOfPlayer(sensorId, teamA);
		if (i >= 0)
			return teamA.players.get(i);
		else {
			i = indexOfPlayer(sensorId, teamB);
			if (i >= 0)
				return teamB.players.get(i);
		}
		return null;
	}

	public static String getPlayerName(String sensorId) {
		int x = Integer.parseInt(sensorId);
		if(x == 105 || x == 106 )
			{return "Refree";}
		else if (x == 4 || x == 8 || x == 10 || x == 12)
		{ return "Ball"; }
		
		for (int i = 0; i < teamA.players.size(); i++){
			if (teamA.players.get(i).hasSensorId(x)) {
				return teamA.players.get(i).name;
			}else
				continue;
		
		}
		for (int i = 0; i < teamB.players.size(); i++){
			if (teamB.players.get(i).hasSensorId(x)) {
				return teamB.players.get(i).name;
				}else
					continue;
		}
		
		return null;
	}

	public static String getTeam(String playerName) {
	
		for (int i = 0; i < teamA.players.size(); i++){
			if (teamA.players.get(i).getName().equals(playerName)) {
				return "teamA";
				}else
					continue;
		}
		for (int i = 0; i < teamB.players.size(); i++){
			if (teamB.players.get(i).getName().equals(playerName)) {
				return "teamB";
				}else
					continue;
		}
		
		return null;
	}

	public static double calculateSpeed(double v) {
		return v * 3600D * Math.pow(10, -9);
	}

	public static Intensitiy getIntesity(double v) {
		double speed = calculateSpeed(v);

		if (0 <= speed && speed <= 1)
			return Intensitiy.Standing;
		else if (speed <= 11)
			return Intensitiy.Trot;
		else if (speed <= 14)
			return Intensitiy.LowSpeedRun;
		else if (speed <= 17)
			return Intensitiy.MediumSpeedRun;
		else if (speed <= 24)
			return Intensitiy.HighSpeedRun;
		else
			return Intensitiy.Sprint;
	}

	public static String StatusInField(long x, long y, long z) {
		if ((x < leftField) || (x > rightField) || (y > topField)
				|| (y < bottomField)) {
			return "-1";
		}
		if ((x > 22578.5D) && (x < 29898.5D) && (y >= 33941.0D)
				&& (z < 2440.0D)) {
			return "1";
		}
		if ((x > 22560.0D) && (x < 29880.0D) && (y <= -33968.0D)
				&& (z < 2440.0D)) {
			return "2";
		}
		return "0";
	}

	public static long GetEuclideanDistance(int x1, int y1, int z1, int x2,
			int y2, int z2) {
		double a = Math.pow(x2 - x1, 2.0D) + Math.pow(y2 - y1, 2.0D)
				+ Math.pow(z2 - z1, 2.0D);

		double d = Math.sqrt(a);

		return (long) d;
	}

	public static boolean isValidInterval(double ts) throws IOException {

		int y;
		long gameHalf = 0;
		;
		if (initTsArray == 0) {
			BufferedReader br = new BufferedReader(new FileReader(
					Settings.matchIntervalFilePath));
			String[] splitLine = null;
			String splitString = null;

			while ((splitString = br.readLine()) != null) {
				splitLine = splitString.toString().split(";");
				if (splitLine[0].startsWith("20")) {
					gameHalf = tsStartFirstHalf;
				} else
					gameHalf = tsStartSecondHalf;
				if (splitLine[1].split(" ")[2].equals("Begin")) {
					beginTS.add(convertStringtoMilliSec(splitLine[2], gameHalf));
				} else {
					endTS.add(convertStringtoMilliSec(splitLine[2], gameHalf));
				}
			}
			br.close();
			initTsArray = 1;
		}

		for (Double s : beginTS) {
			Double db = new Double(ts);
			db = db * Math.pow(10.0D, -12.0D);
			if (db.compareTo(s) <= 0) {
				y = beginTS.indexOf(s) - 1;
				if (db.compareTo(endTS.get(y)) <= 0) {
					return true;
				} else
					return false;
			}

		}
		return false;

	}

	public static double convertStringtoMilliSec(String currTime, long gameHalf) {
		String sTimeStampFormat = "hh:mm:ss.SSS";
		SimpleDateFormat intervalTimeFormat = new SimpleDateFormat(
				sTimeStampFormat);
		Calendar baseCal = new GregorianCalendar();

		try {
			baseCal.setTime(intervalTimeFormat.parse("00:00:00.000"));
			Date parsedDate = intervalTimeFormat.parse(currTime);
			Calendar tmpCal = new GregorianCalendar();
			tmpCal.setTime(parsedDate);

			// double x = tmpCal.getTimeInMillis();
			// double y = baseCal.getTimeInMillis();

			return (parsedDate.getTime() - baseCal.getTimeInMillis())
					* Math.pow(10D, -3.0D) + gameHalf * Math.pow(10D, -12D);

		} catch (Exception e) {
		//	System.out.println("error converting ts to pico seconds");
			return 0L;
		}

	}

	public static boolean isBall(String sensorId) {
		int[] ballSid = new int[] { 4, 8, 10, 12 };
		for( int x = 0; x < ballSid.length ; x++)
		{
			if(ballSid[x] == Integer.parseInt(sensorId))
				return true;
		}
//		if (Arrays.asList(ballSid).contains(Integer.parseInt(sensorId)))
//			return true;
//		else
//			return false;

		return false;
	}

=======
package eplab.anfragen;

/*
 * This class provides instance for eplab.bodenobjekte objects.
 * Also it holds all the utility functions needed in all queries 
 * 
 */
import eplab.bodenobjekte.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class Game {
	private static Game instance;
	protected Team teamA, teamB;
	protected Referee referee;
	protected Ball ball;
	private HashMap<String, AccumulativeIntensity> playersAccumulativeIntensity;

	protected static int bottomField = -33960;
	protected static int leftField = 0;
	protected static int topField = 33960;
	protected static int rightField = 52483;

	protected static ArrayList<Double> beginTS = new ArrayList<Double>();
	protected static ArrayList<Double> endTS = new ArrayList<Double>();
	protected static GameIntervals oGameIntervals = new GameIntervals();
	protected static int initTsArray = 0;

	public static Game Singleton() {
		if (instance == null)
			instance = new Game();
		return instance;
	}

	protected Game() {
		playersAccumulativeIntensity = new HashMap<String, AccumulativeIntensity>();
		List<Player> players = new ArrayList<Player>();
		players.add(new GoalKeeper(13, 14, 97, 98, "Nick Gertje"));
		players.add(new Player(47, 16, "Dennis Dotterweich"));
		players.add(new Player(49, 88, "Niklas Waelzlein"));
		players.add(new Player(19, 52, "Wili Sommer"));
		players.add(new Player(53, 54, "Philipp Harlass"));
		players.add(new Player(23, 24, "Roman Hartleb"));
		players.add(new Player(57, 58, "Erik Engelhardt"));
		players.add(new Player(59, 28, "Sandro Schneider"));

		fillAccumulativeIntensity(players);
		teamA = new Team(players);

		players = new ArrayList<Player>();

		players.add(new GoalKeeper(61, 62, 99, 100, "Leon Krapf"));
		players.add(new Player(63, 64, "Kevin Baer"));
		players.add(new Player(65, 66, "Luca Ziegler"));
		players.add(new Player(67, 68, "Ben Mueller"));
		players.add(new Player(69, 38, "Vale Reitstetter"));
		players.add(new Player(71, 40, "Christopher Lee"));
		players.add(new Player(73, 74, "Leon Heinze"));
		players.add(new Player(75, 44, "Leo Langhans"));

		fillAccumulativeIntensity(players);
		teamB = new Team(players);

		referee = new Referee(105, 106);

		ball = new Ball(new int[] { 4, 8, 10, 12 }, "ball");
	}

	private void fillAccumulativeIntensity(List<Player> players) {
		for (Player player : players) {
			playersAccumulativeIntensity.put(player.name,
					new AccumulativeIntensity());
		}
	}

	public AccumulativeIntensity getIntensity(int sensorId) {
		String playerName = getPlayer(sensorId).name;

		if (playersAccumulativeIntensity.containsKey(playerName))
			return playersAccumulativeIntensity.get(playerName);
		return null;
	}

	private int indexOfPlayer(String playerName, Team team) {
		for (int i = 0; i < team.players.size(); i++)
			if (team.players.get(i).name.equalsIgnoreCase(playerName))
				return i;
		return -1;
	}

	private int indexOfPlayer(int sensorId, Team team) {
		for (int i = 0; i < team.players.size(); i++)
			if (team.players.get(i).hasSensorId(sensorId))
				return i;
		return -1;
	}

	public String GetTeamName(String playerName) {
		if (indexOfPlayer(playerName, teamA) >= 0)
			return "teamA";
		else if (indexOfPlayer(playerName, teamB) >= 0)
			return "teamB";
		else
			return null;
	}

	public Player getPlayer(String playerName) {
		int i = indexOfPlayer(playerName, teamA);
		if (i >= 0)
			return teamA.players.get(i);
		else {
			i = indexOfPlayer(playerName, teamB);
			if (i >= 0)
				return teamB.players.get(i);
		}
		return null;
	}

	public Player getPlayer(int sensorId) {
		int i = indexOfPlayer(sensorId, teamA);
		if (i >= 0)
			return teamA.players.get(i);
		else {
			i = indexOfPlayer(sensorId, teamB);
			if (i >= 0)
				return teamB.players.get(i);
		}
		return null;
	}

	public static double calculateSpeed(double v) {
		return v * 3600D * Math.pow(10, -9);
	}

	public static Intensitiy getIntesity(double v) {
		double speed = calculateSpeed(v);

		if (0 <= speed && speed <= 1)
			return Intensitiy.Standing;
		else if (speed <= 11)
			return Intensitiy.Trot;
		else if (speed <= 14)
			return Intensitiy.LowSpeedRun;
		else if (speed <= 17)
			return Intensitiy.MediumSpeedRun;
		else if (speed <= 24)
			return Intensitiy.HighSpeedRun;
		else
			return Intensitiy.Sprint;
	}

	public static int StatusInField(long x, long y, long z) {
//		if ((x < leftField) || (x > rightField) || (y > topField)
//				|| (y < bottomField)) {
//			return -1;
//		}
		if ((x > 22578.5D) && (x < 29898.5D) && (y >= 33941.0D)
				&& (z < 2440.0D)) {
			//System.out.println("1");
			return 1;
		}
		if ((x > 22560.0D) && (x < 29880.0D) && (y <= -33968.0D)
				&& (z < 2440.0D)) {
			//System.out.println("2");
			return 2;
		}
		return 0;
	}

	public static long GetEuclideanDistance(int x1, int y1, int z1, int x2,
			int y2, int z2) {
		double a = Math.pow(x2 - x1, 2.0D) + Math.pow(y2 - y1, 2.0D)
				+ Math.pow(z2 - z1, 2.0D);

		double d = Math.sqrt(a);

		return (long) d;
	}

	public static boolean IsActiveGame(long ts) {
		return oGameIntervals.isActiveInterval(ElapsedSecondsFromStart(ts));
	}
	
	  public static double ElapsedSecondsFromStart(long ts)
	  {
	    int gameHalf = CurrentGameHalf(ts);
	    if (gameHalf == 1) {
	    	//System.out.println(ElapsedSeconds(tsGameStartFrst, ts).doubleValue());
	      return ElapsedSeconds(tsGameStartFrst, ts).doubleValue();
	    }
	    if (gameHalf == 2) {
	      return ElapsedSeconds(tsGameStartSnd, ts).doubleValue();
	    }
	    return 0.0D;
	  }
	  
	  public static Double ElapsedSeconds(long tsStart, long tsEnd)
	  {
	    double seconds = (tsEnd - tsStart) * Math.pow(10.0D, -12.0D);
	    
	    return new Double(seconds);
	  }
	  
	  protected static long tsGameStartFrst = Long.parseLong("10753295594424116");
	  protected static long tsGameEndFrst = Long.parseLong("12557295594424116");
	  protected static long tsGameStartSnd = Long.parseLong("13086639146403495");
	  protected static long tsGameEndSnd = Long.parseLong("14879639146403495");
	  
	  public static int CurrentGameHalf(long ts)
	  {
	    if ((ts >= tsGameStartFrst) && (ts <= tsGameEndFrst)) {
	      return 1;
	    }
	    if ((ts >= tsGameStartSnd) && (ts <= tsGameEndSnd)) {
	      return 2;
	    }
	    return -1;
	  }

	public static boolean isValidInterval(Double ts) throws IOException {

		int y;
		if (initTsArray == 0) {
			BufferedReader br = new BufferedReader(new FileReader(
					Settings.matchIntervalFilePath));
			String[] splitLine = null;
			String splitString = null;
			int gameHalf = 0;
			int event_id = 0;
			while ((splitString = br.readLine()) != null) {
				splitLine = splitString.toString().split(";");
				if (splitLine[0].startsWith("20")) {
					gameHalf = 1;
				} else
					gameHalf = 2;
				if (splitLine[2].split(" ")[2].equals("Begin")) {
					beginTS.add(convertStringtoMilliSec(splitLine[2]));
				} else {
					endTS.add(convertStringtoMilliSec(splitLine[2]));
				}
			}
			br.close();
			initTsArray = 1;
		}

		for (Double s : beginTS) {
			if (ts.compareTo(s) <= 0) {
				y = beginTS.indexOf(s - 1);
				if (ts.compareTo(endTS.get(y)) <= 0) {
					return true;
				}
			}

		}
		return false;
	}

	public static double convertStringtoMilliSec(String currTime) {
		String sTimeStampFormat = "hh:mm:ss.SSS";
		SimpleDateFormat intervalTimeFormat = new SimpleDateFormat(
				sTimeStampFormat);
		Calendar baseCal = new GregorianCalendar();
		try {
			baseCal.setTime(intervalTimeFormat.parse("00:00:00.000"));
			Date parsedDate = intervalTimeFormat.parse(currTime);
			Calendar tmpCal = new GregorianCalendar();
			tmpCal.setTime(parsedDate);

			return tmpCal.getTimeInMillis() - baseCal.getTimeInMillis();
		} catch (Exception e) {
			System.out.println("error converting ts to pico seconds");
			return 1800000L;
		}

	}

	public static int IsGoal(long ts, int x, int y, int z, int abs_v, int vx,
			int vy, int vz) {
		double interval = 1.5D;

//		Coordinate nextCoordinate = GetPositionInInterval(interval, x, y, z,
//				abs_v, vx, vy, vz);

//		int iStatusInfield = StatusInField(nextCoordinate.x, nextCoordinate.y,
//				nextCoordinate.z);
		int iStatusInfield = StatusInField(x, y,z);
		if ((iStatusInfield == 1) || (iStatusInfield == 2)) {
			return 1;
		}
		return 0;
	}

	protected static Coordinate GetPositionInInterval(double interval,
			int cur_x, int cur_y, int cur_z, int cur_abs_v, int cur_vx,
			int cur_vy, int cur_vz) {
		long future_x = 0L;
		long future_y = 0L;
		long future_z = 0L;

		future_x = (long) (cur_x + cur_abs_v * cur_vx * Math.pow(10.0D, -3.0D)
				* interval);

		future_y = (long) (cur_y + cur_abs_v * cur_vy * Math.pow(10.0D, -3.0D)
				* interval);

		future_z = (long) (9810.0D * Math.pow(interval, 2.0D) / 2.0D + cur_vz
				* interval + cur_z);

		return new Coordinate(future_x, future_y, future_z);
	}
	
	
	
>>>>>>> 3f0b74b9083d932c989cf6e3312d7b005192ac87
}