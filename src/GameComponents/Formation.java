package GameComponents;
import AbtractClasses.Team;


public class Formation {
	int defenders,midFielders,attackers;
	public Formation(int defenders,int midFielders,int attackers){
		this.defenders=defenders;
		this.midFielders=midFielders;
		this.attackers=attackers;
	}
	
	public Team createTeam(int[] xCoordinates,Ball ball,boolean isMe,int error){
		Team team;
		if(isMe)
			team=new UserTeam(xCoordinates, this.defenders,this.midFielders,this.attackers,ball,isMe,error);
		else
			team=new ComputerTeam(xCoordinates, this.defenders,this.midFielders,this.attackers,ball,isMe,error);
		return team;
	}
}