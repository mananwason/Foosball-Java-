package GameComponents;
import AbtractClasses.Player;
import AbtractClasses.Team;



public class Attacker extends Player {

	public Attacker(boolean me,Team team) {
		super(me,team);
		
	}

	@Override
	public int[] getNext() {	
		return this.team.getGoalPoint();
		}
}
