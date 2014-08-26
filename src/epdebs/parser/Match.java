package epdebs.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

public class Match

{
	static int counter = 0;
	protected Player[][] m_currentTeams = (Player[][]) null;
	protected SensoredEntity m_referee = null;
	protected Ball[] m_balls = null;
	protected static HashMap<String, SensoredEntity> m_sensorIdsToEntities = null;
	protected static HashMap<String, SensoredEntity> m_PlayersMap = null;
	protected static HashMap<String, Integer> m_PlayerTeamMap = null;
	protected static Integer Team_A = Integer.valueOf(0);
	protected static Integer Team_B = Integer.valueOf(1);
	protected static long tsGameStartFrst = Long.parseLong("10753295594424116");
	protected static long tsGameEndFrst = Long.parseLong("12557295594424116");
	protected static long tsGameStartSnd = Long.parseLong("13086639146403495");
	protected static long tsGameEndSnd = Long.parseLong("14879639146403495");
	protected static MatchIntervals matchIntervals = new MatchIntervals();
	protected static double elapsedGameSecondsFromStart = 0.0D;
	protected static int bottomField = -33960;
	protected static int leftField = 0;
	protected static int topField = 33960;
	protected static int rightField = 52483;

	public static boolean Initialize(){
//	String sConfigFileName,
//			String sPathToInterruptionPath) {
		m_sensorIdsToEntities = new HashMap();
		m_PlayersMap = new HashMap();
		m_PlayerTeamMap = new HashMap();
		ParseConfigfile();
//		if (!ParseConfigfile(sConfigFileName)) {
//			System.out.println("Error in ParseConfigfile(sConfigFileName)");
//			System.exit(0);
//		}
//		if (!matchIntervals
//				.ParseMatchInterruptionsFile(sPathToInterruptionPath)) {
//			System.out.println("Error in ParseConfigfile(sConfigFileName)");
//			System.exit(0);
//		}
		return true;
	}

//	protected static BufferedReader InitializeReader(String sFileName) {
//		BufferedReader reader = null;
//		try {
//			reader = new BufferedReader(new FileReader(sFileName));
//		} catch (IOException e) {
//			System.out.println("Error in initializeReader:" + e.getMessage()
//					+ " Existing");
//
//			System.exit(1);
//		}
//		return reader;
//	}

	protected static boolean ParseConfigfile() {
		int[] ballSensId = { 4, 8, 10, 12 };
		Ball ball = new Ball(ballSensId, "Ball");
		m_sensorIdsToEntities.put("4", ball);
		m_sensorIdsToEntities.put("8", ball);
		m_sensorIdsToEntities.put("10", ball);
		m_sensorIdsToEntities.put("12", ball);

		int iIndex = 0;

		int[] sensIds_1 = { 13, 14, 97, 98 };
		Player playerNick = new Player(sensIds_1, "Nick Gertje",
				SensoredEntity.iTypeGoalKeeper);

		m_sensorIdsToEntities.put("13", playerNick);
		m_sensorIdsToEntities.put("14", playerNick);
		m_sensorIdsToEntities.put("97", playerNick);
		m_sensorIdsToEntities.put("98", playerNick);

		m_PlayersMap.put("Nick Gertje", playerNick);
		m_PlayerTeamMap.put("Nick Gertje", Team_A);
		iIndex++;

		int[] sensIds_2 = { 47, 16 };
		Player playerDennis = new Player(sensIds_2, "Dennis Dotterweich",
				SensoredEntity.iTypePlayer);

		m_sensorIdsToEntities.put("47", playerDennis);
		m_sensorIdsToEntities.put("16", playerDennis);

		m_PlayersMap.put("Dennis Dotterweich", playerDennis);
		m_PlayerTeamMap.put("Dennis Dotterweich", Team_A);
		iIndex++;

		int[] sensIds_3 = { 49, 88 };
		Player playerNiklas = new Player(sensIds_3, "Niklas Waelzlein",
				SensoredEntity.iTypePlayer);

		m_sensorIdsToEntities.put("49", playerNiklas);
		m_sensorIdsToEntities.put("88", playerNiklas);

		m_PlayersMap.put("Niklas Waelzlein", playerNiklas);
		m_PlayerTeamMap.put("Niklas Waelzlein", Team_A);
		iIndex++;

		int[] sensIds_4 = { 19, 52 };
		Player playerWili = new Player(sensIds_4, "Wili Sommer",
				SensoredEntity.iTypePlayer);

		m_sensorIdsToEntities.put("19", playerWili);
		m_sensorIdsToEntities.put("52", playerWili);

		m_PlayersMap.put("Wili Sommer", playerWili);
		m_PlayerTeamMap.put("Wili Sommer", Team_A);
		iIndex++;

		int[] sensIds_5 = { 53, 54 };
		Player playerPhillipp = new Player(sensIds_5, "Philipp Harlass",
				SensoredEntity.iTypePlayer);

		m_sensorIdsToEntities.put("53", playerPhillipp);
		m_sensorIdsToEntities.put("54", playerPhillipp);

		m_PlayersMap.put("Philipp Harlass", playerPhillipp);
		m_PlayerTeamMap.put("Philipp Harlass", Team_A);
		iIndex++;

		int[] sensIds_6 = { 23, 24 };
		Player playerRoman = new Player(sensIds_6, "Roman Hartleb",
				SensoredEntity.iTypePlayer);

		m_sensorIdsToEntities.put("23", playerRoman);
		m_sensorIdsToEntities.put("24", playerRoman);

		m_PlayersMap.put("Roman Hartleb", playerRoman);
		m_PlayerTeamMap.put("Roman Hartleb", Team_A);
		iIndex++;

		int[] sensIds_7 = { 57, 58 };
		Player playerErik = new Player(sensIds_7, "Erik Engelhardt",
				SensoredEntity.iTypePlayer);

		m_sensorIdsToEntities.put("57", playerErik);
		m_sensorIdsToEntities.put("58", playerErik);

		m_PlayersMap.put("Erik Engelhardt", playerErik);
		m_PlayerTeamMap.put("Erik Engelhardt", Team_A);
		iIndex++;

		int[] sensIds_8 = { 59, 28 };
		Player playerSandro = new Player(sensIds_8, "Sandro Schneider",
				SensoredEntity.iTypePlayer);

		m_sensorIdsToEntities.put("59", playerSandro);
		m_sensorIdsToEntities.put("28", playerSandro);

		m_PlayersMap.put("Sandro Schneider", playerSandro);
		m_PlayerTeamMap.put("Sandro Schneider", Team_A);
		iIndex++;

		int[] sensIds_9 = { 61, 62, 99, 100 };
		Player PlayerLeon = new Player(sensIds_9, "Leon Krapf",
				SensoredEntity.iTypeGoalKeeper);

		m_sensorIdsToEntities.put("61", PlayerLeon);
		m_sensorIdsToEntities.put("62", PlayerLeon);
		m_sensorIdsToEntities.put("99", PlayerLeon);
		m_sensorIdsToEntities.put("100", PlayerLeon);

		m_PlayersMap.put("Leon Krapf", PlayerLeon);
		m_PlayerTeamMap.put("Leon Krapf", Team_B);
		iIndex++;

		int[] sensIds_10 = { 63, 64 };
		Player PlayerKevin = new Player(sensIds_10, "Kevin Baer",
				SensoredEntity.iTypePlayer);

		m_sensorIdsToEntities.put("63", PlayerKevin);
		m_sensorIdsToEntities.put("64", PlayerKevin);

		m_PlayersMap.put("Kevin Baer", PlayerKevin);
		m_PlayerTeamMap.put("Kevin Baer", Team_B);
		iIndex++;

		int[] sensIds_11 = { 65, 66 };
		Player PlayerLuca = new Player(sensIds_11, "Luca Ziegler",
				SensoredEntity.iTypePlayer);

		m_sensorIdsToEntities.put("65", PlayerLuca);
		m_sensorIdsToEntities.put("66", PlayerLuca);

		m_PlayersMap.put("Luca Ziegler", PlayerLuca);
		m_PlayerTeamMap.put("Luca Ziegler", Team_B);
		iIndex++;

		int[] sensIds_12 = { 67, 68 };
		Player PlayerBen = new Player(sensIds_12, "Ben Mueller",
				SensoredEntity.iTypePlayer);

		m_sensorIdsToEntities.put("67", PlayerBen);
		m_sensorIdsToEntities.put("68", PlayerBen);

		m_PlayersMap.put("Ben Mueller", PlayerBen);
		m_PlayerTeamMap.put("Ben Mueller", Team_B);
		iIndex++;

		int[] sensIds_13 = { 69, 38 };
		Player PlayerVale = new Player(sensIds_13, "Vale Reitstetter",
				SensoredEntity.iTypePlayer);

		m_sensorIdsToEntities.put("69", PlayerVale);
		m_sensorIdsToEntities.put("38", PlayerVale);

		m_PlayersMap.put("Vale Reitstetter", PlayerVale);
		m_PlayerTeamMap.put("Vale Reitstetter", Team_B);
		iIndex++;

		int[] sensIds_14 = { 71, 40 };
		Player PlayerChristopher = new Player(sensIds_14, "Christopher Lee",
				SensoredEntity.iTypePlayer);

		m_sensorIdsToEntities.put("71", PlayerChristopher);
		m_sensorIdsToEntities.put("40", PlayerChristopher);

		m_PlayersMap.put("Christopher Lee", PlayerChristopher);
		m_PlayerTeamMap.put("Christopher Lee", Team_B);
		iIndex++;

		int[] sensIds_15 = { 73, 74 };
		Player PlayerLeonH = new Player(sensIds_15, "Leon Heinze",
				SensoredEntity.iTypePlayer);

		m_sensorIdsToEntities.put("73", PlayerLeonH);
		m_sensorIdsToEntities.put("74", PlayerLeonH);

		m_PlayersMap.put("Leon Heinze", PlayerLeonH);
		m_PlayerTeamMap.put("Leon Heinze", Team_B);
		iIndex++;

		int[] sensIds_16 = { 75, 44 };
		Player PlayerLeo = new Player(sensIds_16, "Leo Langhans",
				SensoredEntity.iTypePlayer);

		m_sensorIdsToEntities.put("75", PlayerLeo);
		m_sensorIdsToEntities.put("44", PlayerLeo);

		m_PlayersMap.put("Leo Langhans", PlayerLeo);
		m_PlayerTeamMap.put("Leo Langhans", Team_B);
		iIndex++;

		return true;
	}

	public static SensoredEntity GetEntityFromSensorIds(String sid) {
		SensoredEntity curEntity = (SensoredEntity) m_sensorIdsToEntities.get(sid);
		return curEntity;
	}

	public static int GetTeamFromPlayerName(String playerName) {
		Integer teamId = (Integer) m_PlayerTeamMap.get(playerName);
		return teamId.intValue();
	}

	public static CurrentIntensityMessage GetCurrentIntensityMessage(
			String name, long ts, int iIntensity, long abs_v) {
		CurrentIntensityMessage curIntMessage = new CurrentIntensityMessage(
				-10000L, -10000L, "ignore", -10000, -1000L, -1000);

		Player player = (Player) m_PlayersMap.get(name);
		CurrentIntensity curInt = player.currentIntesity;
		if (curInt != null) {
			if (curInt.active == true) {
				long speed = curInt.v;
				long delta = ts - curInt.ts;
				long distance = GetDistance(speed, delta);

				curIntMessage.ts_start = curInt.ts;
				curIntMessage.ts_stop = ts;
				curIntMessage.player_id = name;
				curIntMessage.intensity = curInt.intensity;
				curIntMessage.distance = distance;
				curIntMessage.speed = speed;

				curInt.v = abs_v;
				if (curInt.intensity == iIntensity) {
					curInt.active = true;
				} else {
					curInt.active = false;
				}
				curInt.ts = ts;
				curInt.intensity = iIntensity;
				curInt.v = abs_v;
			} else if (IsMoreThanASecond(curInt.lastIntTs, ts)) {
				long speed = curInt.v;
				long delta = ts - curInt.ts;
				long distance = GetDistance(speed, delta);

				curIntMessage.ts_start = curInt.ts;
				curIntMessage.ts_stop = ts;
				curIntMessage.player_id = name;
				curIntMessage.intensity = curInt.intensity;
				curIntMessage.distance = distance;
				curIntMessage.speed = speed;

				curInt.v = abs_v;
				if (curInt.intensity == iIntensity) {
					curInt.active = true;
				} else {
					curInt.active = false;
				}
				curInt.ts = ts;
				curInt.intensity = iIntensity;
				curInt.v = abs_v;
			} else if (curInt.intensity != iIntensity) {
				curInt.lastIntTs = ts;
			}
		}
		return curIntMessage;
	}

	public static CurrentIntensityMessage GetCurrentIntensityEvent_OLD(
			String name, long ts, int iIntensity, int abs_v) {
		CurrentIntensityMessage curIntEvent = new CurrentIntensityMessage(
				-10000L, -10000L, "ignore", -10000, -1000L, -1000);

		Player player = (Player) m_PlayersMap.get(name);
		if (player == null) {
			return curIntEvent;
		}
		CurrentIntensity curInt = player.currentIntesity;
		if (curInt == null) {
			player.setCurrentIntesity(new CurrentIntensity(ts, iIntensity,
					abs_v, false));
		} else if ((IsMoreThanASecond(curInt.ts, ts))
				|| ((curInt.active == true) && (curInt.intensity == iIntensity))) {
			long speed = curInt.v;
			long delta = ts - curInt.ts;
			long distance = GetDistance(speed, delta);

			curIntEvent = new CurrentIntensityMessage(curInt.ts, ts, name,
					curInt.intensity, distance, speed);
			if (curInt.intensity == iIntensity) {
				curInt.active = true;
			} else {
				curInt.active = false;
			}
			curInt.ts = ts;
			curInt.intensity = iIntensity;
			curInt.v = abs_v;
		} else {
			curInt.intensity = iIntensity;

			curInt.v = abs_v;
		}
		return curIntEvent;
	}

	public static double GetSpeed(double absV) {
		double speed_microPerHour = absV * 3600.0D;
		double speed_kmh = speed_microPerHour * Math.pow(10.0D, -9.0D);

		return speed_kmh;
	}

	public static int GetIntesity(long absV) {
		double speed = GetSpeed(absV);
		if (speed <= 1.0D) {
			return 0;
		}
		if ((speed > 1.0D) && (speed <= 11.0D)) {
			return 1;
		}
		if ((speed > 11.0D) && (speed <= 14.0D)) {
			return 2;
		}
		if ((speed > 14.0D) && (speed <= 17.0D)) {
			return 3;
		}
		if ((speed > 17.0D) && (speed <= 24.0D)) {
			return 4;
		}
		return 5;
	}

	public static double ElapsedSecondsFromStart(long ts) {
		int gameHalf = CurrentGameHalf(ts);
		if (gameHalf == 1) {
			return ElapsedSeconds(tsGameStartFrst, ts).doubleValue();
		}
		if (gameHalf == 2) {
			return ElapsedSeconds(tsGameStartSnd, ts).doubleValue();
		}
		return 0.0D;
	}

	public static int CurrentGameHalf(long ts) {
		if ((ts >= tsGameStartFrst) && (ts <= tsGameEndFrst)) {
			return 1;
		}
		if ((ts >= tsGameStartSnd) && (ts <= tsGameEndSnd)) {
			return 2;
		}
		return -1;
	}

	public static double GetTimeInGame(long ts) {
		return matchIntervals.getCurrMatchTime(ElapsedSecondsFromStart(ts));
	}

	public static double NetoTimeFromStart(long ts) {
		if ((ts >= tsGameStartFrst) && (ts <= tsGameEndFrst)) {
			return ElapsedSeconds(tsGameStartFrst, ts).doubleValue();
		}
		if ((ts >= tsGameEndFrst) && (ts <= tsGameStartSnd)) {
			return ElapsedSeconds(tsGameStartFrst, tsGameEndFrst).doubleValue();
		}
		if ((ts >= tsGameStartSnd) && (ts <= tsGameEndSnd)) {
			return ElapsedSeconds(tsGameStartFrst, tsGameEndFrst).doubleValue()
					+ ElapsedSeconds(tsGameStartSnd, ts).doubleValue();
		}
		if (ts > tsGameEndSnd) {
			return ElapsedSeconds(tsGameStartFrst, tsGameEndFrst).doubleValue()
					+ ElapsedSeconds(tsGameStartSnd, tsGameEndSnd)
							.doubleValue();
		}
		return 0.0D;
	}

	public static Double ElapsedSeconds(long tsStart, long tsEnd) {
		double seconds = (tsEnd - tsStart) * Math.pow(10.0D, -12.0D);

		return new Double(seconds);
	}

	public static boolean IsMoreThanASecond(long lStart, long lCur) {
		if (ElapsedSeconds(lStart, lCur).doubleValue() > 1.0D) {
			return true;
		}
		return false;
	}

	public static int GetFiledWidth() {
		return rightField - leftField;
	}

	public static int GetFiledHeight() {
		return topField - bottomField;
	}

	public static long GetDistance(long velocity, long lTimeDelta) {
		return velocity * (lTimeDelta / 1000000000L);
	}

	public static long GetEuclideanDistance(int x1, int y1, int z1, int x2,
			int y2, int z2) {
		double a = Math.pow(x2 - x1, 2.0D) + Math.pow(y2 - y1, 2.0D)
				+ Math.pow(z2 - z1, 2.0D);

		double d = Math.sqrt(a);

		return (long) d;
	}

	public static int IsGoal(long ts, int x, int y, int z, int abs_v, int vx,
			int vy, int vz) {
		double interval = 1.5D;

		Position positionInfuture = GetPositionInInterval(interval, x, y, z,
				abs_v, vx, vy, vz);

		int iStatusInfield = StatusInField(positionInfuture.x,
				positionInfuture.y, positionInfuture.z);

		if ((iStatusInfield == 1) || (iStatusInfield == 2)) {
			return 1;
		}
		return 0;
	}

	protected static Position GetPositionInInterval(double interval, int cur_x,
			int cur_y, int cur_z, int cur_abs_v, int cur_vx, int cur_vy,
			int cur_vz) {
		long future_x = 0L;
		long future_y = 0L;
		long future_z = 0L;

		future_x = (long) (cur_x + cur_abs_v * cur_vx * Math.pow(10.0D, -3.0D)
				* interval);

		future_y = (long) (cur_y + cur_abs_v * cur_vy * Math.pow(10.0D, -3.0D)
				* interval);

		future_z = (long) (9810.0D * Math.pow(interval, 2.0D) / 2.0D + cur_vz
				* interval + cur_z);

		return new Position(future_x, future_y, future_z);
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

	public static boolean IsActiveGame(long ts) {
		return matchIntervals.isActiveInterval(ElapsedSecondsFromStart(ts));
	}

	public static void main(String[] args) {
		long tsStart = 0L;
		long tsEnd = 0L;

		double elapsedSeconds = ElapsedSeconds(tsStart, tsEnd).doubleValue();

		System.out.println("Elapsed Seconds: " + elapsedSeconds);

		double absV = 0.0D;
		double speed = GetSpeed(absV);

		System.out.println("Speed is : " + speed);

		int iabsV = 993684;
		int intensity = GetIntesity(iabsV);
		System.out.println("Intensity is : " + intensity);

		int x = 9886;
		int y = 219;
		int z = 1486;
		int stat = StatusInField(x, y, z);

		System.out.println("Status is : " + stat);
	}
}