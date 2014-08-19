package epdebs.game_objects;

import java.util.ArrayList;
import java.util.List;

public class Game {
	protected Team teamA, teamB;
	protected Referee referee;
	protected Ball ball;

	public Game() {
		List<Player> players = new ArrayList<Player>();
		players.add(new GoalKeeper(13, 14, 97, 98, "Nick Gertje"));
		players.add(new Player(47, 16, "Dennis Dotterweich"));
		players.add(new Player(49, 88, "Niklas Waelzlein"));
		players.add(new Player(19, 52, "Wili Sommer"));
		players.add(new Player(53, 54, "Philipp Harlass"));
		players.add(new Player(23, 24, "Roman Hartleb"));
		players.add(new Player(57, 58, "Erik Engelhardt"));
		players.add(new Player(59, 28, "Sandro Schneider"));

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

		teamB = new Team(players);

		referee = new Referee(105, 106);
		
		ball = new Ball(new int [] {4, 8, 10, 12}, "ball");
		
		
	}
	
	public String GetTeamName(String playerName)
	{
		if(teamA.players.contains(playerName))
			return "teamA";
		else
			return "teamB";
	}
}
