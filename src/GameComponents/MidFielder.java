package GameComponents;
import AbtractClasses.Player;
import AbtractClasses.Team;



public class MidFielder extends Player {

	public MidFielder(boolean me,Team team) {
		super(me,team);
	}
	
	@Override
	public int[] getNext() {
		Player attacker=getAttacker();
		int[] location=new int[2];
		if(random.nextInt(2)==0){
			return this.team.getGoalPoint();
		}
		else{
			location[0]=attacker.x;location[1]=(int)attacker.y + random.nextInt(this.team.error);
			return location;
		}
	}
	
	public Player getAttacker(){
		int d=this.team.ATcount ;
		return this.team.players[random.nextInt(d)+this.team.DFcount+this.team.MFcount+1];
	}

}
