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
		if ((x < leftField) || (x > rightField) || (y > topField)
				|| (y < bottomField)) {
			return -1;
		}
		if ((x > 22578.5D) && (x < 29898.5D) && (y >= 33941.0D)
				&& (z < 2440.0D)) {
			return 1;
		}
		if ((x > 22560.0D) && (x < 29880.0D) && (y <= -33968.0D)
				&& (z < 2440.0D)) {
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
}