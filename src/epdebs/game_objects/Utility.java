package epdebs.game_objects;

public class Utility {
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
}
