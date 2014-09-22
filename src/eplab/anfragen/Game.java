package eplab.anfragen;

/*
 * This class provides instance for eplab.bodenobjekte objects.
 * Also it holds all the utility functions needed in all queries 
 * 
 */

import eplab.anfragen.GameInterval;
import eplab.anfragen.Settings;
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
import java.util.Objects;


public class Game {
	private static Game instance;
	protected static Team teamA, teamB;
	protected Referee referee;
	protected Ball ball;
	private HashMap<String, AccumulativeIntensity> playersAccumulativeIntensity;
	protected static GameInterval oGameInterval = new GameInterval();
	protected static int bottomField = -33960;
	protected static int leftField = 0;
	protected static int topField = 33960;
	protected static int rightField = 52483;
	protected static long tsStartFirstHalf = Long.parseLong("10753295594424116");
	protected static long tsEndFirstHalf = Long.parseLong("12557295594424116");
	protected static long tsStartSecondHalf = Long.parseLong("13086639146403495");
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
	// int x = Integer.parseInt(sensorId);
	String x = sensorId;
	// if(x == 105 || x == 106 )
	// {return "Refree";}
	// else if (x == 4 || x == 8 || x == 10 || x == 12)
	// { return "Ball"; }
	//
	for (int i = 0; i < teamA.players.size(); i++) {
		if (teamA.players.get(i).hasSensorId(Integer.parseInt(x))) {
			return teamA.players.get(i).name;
		} else {
			continue;
		}

	}
	for (int i = 0; i < teamB.players.size(); i++) {
		if (teamB.players.get(i).hasSensorId(Integer.parseInt(x))) {
			return teamB.players.get(i).name;
		} else {
			continue;
		}
	}

	return null;

}

	public static String getTeam(String playerName) {

		for (int i = 0; i < teamB.players.size(); i++) {
			if (teamB.players.get(i).getName().equals(playerName)) {
				return "teamB";
			} else {
				continue;
			}
		}
		for (int i = 0; i < teamA.players.size(); i++) {
			if (teamA.players.get(i).getName().equals(playerName)) {
				return "teamA";
			} else {
				continue;
			}
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
	
	
	public static int StatusInField(long x, long y, long z)
	{
		if ((x > 22578.5D) && (x < 29898.5D) && (y >= 33941.0D) && (z < 2440.0D)) {
			//System.out.println("1");
			return 1;
		}
		if ((x > 22560.0D) && (x < 29880.0D) && (y <= -33968.0D) && (z < 2440.0D)) {
			//System.out.println("2");
			return 2;
		}
		if ((x < leftField) || (x > rightField) || (y > topField) || (y < bottomField)) {
			return -1;
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
	
	public static int getGameHalf(long ts) {
		if (ts < tsStartFirstHalf)
			return 0;
		else if (ts >= tsStartFirstHalf && ts <= tsEndFirstHalf)
			return 1;
		else if (ts >= tsStartSecondHalf && ts <= tsEndSecondHalf)
			return 2;
		else if (Objects.toString(ts, null).matches("12398.*"))
			return 0;

		return 0;
	}
	
	 public static int IsTowardGoal(long ts, int x, int y, int z, int abs_v, int vx, int vy, int vz)
	  {
	    double interval = 1.5D;
	    
	    Location LocationInfuture = GetPositionInInterval(interval, x, y, z, abs_v, vx, vy, vz);
	    
	    int iStatusInfield = StatusInField(LocationInfuture.x, LocationInfuture.y, LocationInfuture.z);
	    if ((iStatusInfield == 1) || (iStatusInfield == 2)) {
	      return 1;
	    }
	    return 0;
	  }
	 
	 protected static Location GetPositionInInterval(double interval, int cur_x, int cur_y, int cur_z, int cur_abs_v, int cur_vx, int cur_vy, int cur_vz)
	  {
	    long next_x = 0L;
	    long next_y = 0L;
	    long next_z = 0L;

	    //x > 22560.0 and x < 29880.0 OR x > 22578.5 and x < 29898.5
	    next_x = (long)(cur_x + (double)cur_abs_v * (double)cur_vx * Math.pow(10.0D, -7.0D) * interval);
	    //y = 33941.0 OR y = -33968.0
	    next_y = (long)(cur_y + (double)cur_abs_v * (double)cur_vy * Math.pow(10.0D, -7.0D) * interval);
	    //z < 2440.0
	    next_z =(long)(9810.0D * Math.pow(interval, -2.0D) / 2.0D + (double)cur_vz * interval + cur_z);

	    //return new Location(cur_x, cur_y, cur_z);
	    return new Location(next_x, next_y, next_z);
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
			// System.out.println("error converting ts to pico seconds");
			return 0L;
		}

	}

	public static boolean isBall(int sensorId) {
		int[] ballSid = new int[] { 4, 8, 10, 12 };
		for (int x = 0; x < ballSid.length; x++) {
			if (ballSid[x] == sensorId)
				return true;
		}
		// if (Arrays.asList(ballSid).contains(Integer.parseInt(sensorId)))
		// return true;
		// else
		// return false;

		return false;
	}
	
	public static double GetTimeInGame(long ts)
	{
		if (!oGameInterval.ParseGameInterruptionsFile(Settings.sPathToInterruptionPath))
	    {
	      System.out.println("Error in ParseGameInterruptionsFile(sPathToInterruptionPath)");
	      System.exit(0);
	    }
		//oGameInterval.printGameInterruption();
		double currts = ElapsedSecondsFromStart(ts);
		double x = oGameInterval.getCurrGameTime(currts);
		return x;
	}
	
	public static double ElapsedSecondsFromStart(long ts)
	{
		int gameHalf = CurrentGameHalf(ts);
	    if (gameHalf == 1) {
	      return ElapsedSeconds(tsStartFirstHalf, ts).doubleValue();
	    }
	    if (gameHalf == 2) {
	    return ElapsedSeconds(tsStartSecondHalf, ts).doubleValue();
	    }
	    	return 0.0D;
	}
	
	public static int CurrentGameHalf(long ts)
	{
	  if ((ts >= tsStartFirstHalf) && (ts <= tsEndFirstHalf)) {
	    return 1;
	  }
	  if ((ts >= tsStartSecondHalf) && (ts <= tsEndSecondHalf)) {
	    return 2;
	  }
	  return -1;
	}
	
	 protected static GameIntervals gameIntervals = new GameIntervals();
	 public static boolean IsActiveGame(long ts)
	  {
	    return gameIntervals.isActiveInterval(ElapsedSecondsFromStart(ts));
	  }
	
	public static Double ElapsedSeconds(long tsStart, long tsEnd)
	{
	  double seconds = (tsEnd - tsStart) * Math.pow(10.0D, -12.0D);
	  return new Double(seconds);
	}
	
	public static int GetFiledWidth()
	{
		return rightField - leftField;
	}

	public static int GetFiledHeight()
	{
		return topField - bottomField;
	}
}