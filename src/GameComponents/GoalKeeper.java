package GameComponents;
import AbtractClasses.Player;
import AbtractClasses.Team;


public class GoalKeeper extends Player {

	public GoalKeeper(boolean me,Team team) {
		super(me,team);
	}

	@Override
	public int[] getNext() {
		Player defender = getDefender();
		int[] location = new int[2];
		location[0] = defender.x;
		location[1] = (int)defender.y+random.nextInt(this.team.error);
		return location;
	}

	public Player getDefender() {
		int d = this.team.DFcount;
		return this.team.players[random.nextInt(d) + 1];
	}

}
