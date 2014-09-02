package eplab.anfragen;

/*
 * This class provides instance for eplab.bodenobjekte objects.
 * Also it holds all the utility functions needed in all queries 
 * 
 */

import eplab.anfragen.GameInterval;
import eplab.bodenobjekte.Intensitiy;
import eplab.bodenobjekte.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {
	private static Game instance;
	protected Team teamA, teamB;
	protected Referee referee;
	protected Ball ball;
	private HashMap<String, AccumulativeIntensity> playersAccumulativeIntensity;
	protected static GameInterval oGameInterval = new GameInterval();
	protected static int bottomField = -33960;
	protected static int leftField = 0;
	protected static int topField = 33960;
	protected static int rightField = 52483;
	protected static long tsGameStartFrst = Long.parseLong("10753295594424116");
	protected static long tsGameEndFrst = Long.parseLong("12557295594424116");
	protected static long tsGameStartSnd = Long.parseLong("13086639146403495");
	protected static long tsGameEndSnd = Long.parseLong("14879639146403495");

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
	
	public String getPlayerName(int sensorId) {
		return getPlayer(sensorId).name;
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
	
	public static int GetFiledWidth()
	{
		return rightField - leftField;
	}

	public static int GetFiledHeight()
	{
		return topField - bottomField;
	}
	
	public static int StatusInField(long x, long y, long z)
	{
		if ((x < leftField) || (x > rightField) || (y > topField) || (y < bottomField)) {
			return -1;
		}
		if ((x > 22578.5D) && (x < 29898.5D) && (y >= 33941.0D) && (z < 2440.0D)) {
			return 1;
		}
		if ((x > 22560.0D) && (x < 29880.0D) && (y <= -33968.0D) && (z < 2440.0D)) {
			return 2;
		}
		return 0;
	}
	public static double GetTimeInGame(long ts)
	{
		return oGameInterval.getCurrGameTime(ElapsedSecondsFromStart(ts));
	}
	public static double ElapsedSecondsFromStart(long ts)
	{
		int gameHalf = CurrentGameHalf(ts);
	    if (gameHalf == 1) {
	      return ElapsedSeconds(tsGameStartFrst, ts).doubleValue();
	    }
	    if (gameHalf == 2) {
	    return ElapsedSeconds(tsGameStartSnd, ts).doubleValue();
	    }
	    	return 0.0D;
	}
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
	public static Double ElapsedSeconds(long tsStart, long tsEnd)
	{
	  double seconds = (tsEnd - tsStart) * Math.pow(10.0D, -12.0D);
	  return new Double(seconds);
	}
}