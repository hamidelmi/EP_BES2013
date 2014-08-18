package epdebs.game_objects;

import java.util.ArrayList;
import java.util.List;

public class Game {
	protected List<Player> teamA, teamB;
	protected Referee referee;

	public Game() {
		teamA = new ArrayList<Player>();
		teamA.add(new GoalKeeper(13, 14, 97, 98, "Nick Gertje"));
		teamA.add(new Player(47, 16, "Dennis Dotterweich"));
		teamA.add(new Player(49, 88, "Niklas Waelzlein"));
		teamA.add(new Player(19, 52, "Wili Sommer"));
		teamA.add(new Player(53, 54, "Philipp Harlass"));
		teamA.add(new Player(23, 24, "Roman Hartleb"));
		teamA.add(new Player(57, 58, "Erik Engelhardt"));
		teamA.add(new Player(59, 28, "Sandro Schneider"));

		teamB = new ArrayList<Player>();
		teamB.add(new GoalKeeper(61, 62, 99, 100, "Leon Krapf"));
		teamB.add(new Player(63, 64, "Kevin Baer"));
		teamB.add(new Player(65, 66, "Luca Ziegler"));
		teamB.add(new Player(67, 68, "Ben Mueller"));
		teamB.add(new Player(69, 38, "Vale Reitstetter"));
		teamB.add(new Player(71, 40, "Christopher Lee"));
		teamB.add(new Player(73, 74, "Leon Heinze"));
		teamB.add(new Player(75, 44, "Leo Langhans"));

		referee = new Referee(105, 106);
	}
}
