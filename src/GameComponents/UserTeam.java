package GameComponents;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import AbtractClasses.Team;

public class UserTeam extends Team {

	public UserTeam(int[] xCoordinates, int numDF, int numMF, int numAT,
			Ball ball, boolean isMe, int error) {
		super(xCoordinates, numDF, numMF, numAT, ball, isMe, error);
		rodFactor = 0.3 ;
	}

	public void showImage() {
		GoalPanel gp = new GoalPanel();
		gp.add(new JLabel(new ImageIcon("win1.png")));

		
		gp.setVisible(true);
	}
	
	public int checkGoal(){
		//System.out.println("User");
		//System.out.println("Goal point "+this.goalPoint[0] + " "+ this.ball.x);
		if (this.ball.x + this.ball.size  >= this.goalPoint[0]
				&& (this.ball.y + this.ball.size > 257 && this.ball.y
						+ this.ball.size < 392)) {
			this.score++;
			this.sp.label.setText("Score "+this.score+" - "+this.opponent.score);
			return this.placeBallAfterGoal();
		}
		return 0;
	}
	
	
}
