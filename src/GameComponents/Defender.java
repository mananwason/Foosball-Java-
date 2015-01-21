package GameComponents;
import AbtractClasses.Player;
import AbtractClasses.Team;



public class Defender extends Player {

	public Defender(boolean me, Team team) {
		super(me,team);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int[] getNext() {
		Player midFielder=getMidFielder();
		int[] location=new int[2];
		if(random.nextInt(2)==0){
			return this.team.getGoalPoint();
		}
		else{
			location[0]=midFielder.x;location[1]=(int)midFielder.y + random.nextInt(this.team.error);
			return location;
		}
	}
	
	public Player getMidFielder(){ //changed from private to public
		int m=this.team.MFcount;
		return this.team.players[random.nextInt(m)+this.team.DFcount+1];
	}
	
}
