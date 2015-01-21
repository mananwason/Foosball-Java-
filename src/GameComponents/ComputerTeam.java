package GameComponents;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import AbtractClasses.Event;
import AbtractClasses.Team;
import Interfaces.Observable;
import Interfaces.Observer;

public class ComputerTeam extends Team implements Observer {

	public ComputerTeam(int[] xCoordinates, int numDF, int numMF, int numAT,
			Ball ball, boolean isMe, int error) {
		super(xCoordinates, numDF, numMF, numAT, ball, isMe, error);
		this.startObserving(ball);
		switch(error){
		case 70 : rodFactor = 0.1 + rand.nextDouble()/10;break;
		case 30 : rodFactor = 0.3+ rand.nextDouble()/10;break;
		case 5 : rodFactor = 0.5+ rand.nextDouble()/10;break;
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public void startObserving(Observable observable) {
		this.ball.addObserver(this);
	}

	@Override
	public void handleEvent(Event event) {
		if (this.ball.lastContact instanceof UserTeam) {
			for (int i = 3; i >= 0; i--) {
				if (this.ball.x < this.rowCoordinates[i]) {
					int tentativeY = -1;
					tentativeY = getTentativePosition(this.rowCoordinates[i]);
					if (tentativeY > 68 && tentativeY < 580) {
						
						switch (i) {
						case 3:
							for (int j = 1 + DFcount + MFcount; j < 11; j++) {
								if (tentativeY <= players[j].lowerLimitY) {
									if (tentativeY < players[j].y) {
										direction = -1;
									} else if (tentativeY > players[j].y + 38) {
										direction = 1;
									}
									break;
								}
						
							}
							break;

						case 2:
							for (int j = 1 + DFcount; j < 1 + DFcount + MFcount; j++) {
								if (tentativeY <= players[j].lowerLimitY) {
									if (tentativeY < players[j].y) {
										direction = -1;
									} else if (tentativeY > players[j].y + 38) {
										direction = 1;
									}
									break;
								}
								// allignPlayers(this.rowCoordinates[3 - i],
								// tentativeY);
							}
							break;

						case 1:
							for (int j = 1; j < 1 + DFcount; j++) {
								if (tentativeY <= players[j].lowerLimitY) {
									if (tentativeY < players[j].y) {
										direction = -1;
									} else if (tentativeY > players[j].y + 38) {
										direction = 1;
									}
									break;
								}
						
							}
							break;
						case 0:
							if (tentativeY < players[0].y) {
								direction = -1;
							} else if (tentativeY > players[0].y + 38) {
								direction = 1;
							}
							break;

						}
						allignPlayers(this.rowCoordinates[i], tentativeY);

					}
				
				}

			}
			
		}
	}
	
	public int getTentativePosition(int x) {
		double tan = this.ball.dy / this.ball.dx;
		double yDiff = tan * (x - this.ball.x);
		return (int) (this.ball.y + yDiff);
	}
	
	public void moveComp() {
		
		
		for (int i = 0; i < players.length; i++) {
			if(players[i].y+players[i].getNewLocation() <= players[i].lowerLimitY && players[i].y + players[i].getNewLocation() >= players[i].upperLimitY)
				players[i].y += players[i].getNewLocation();	
		}
	}
	
	public void allignPlayers(int x, int y) {
		while (true) {
		
			for (int i = 0; i < 11; i++) {
		
				if (this.players[i].x == x && (this.players[i].y - y<=10) && (this.players[i].y-y>=0)){
		
					direction = 0;
					return ;
					}
			}
		
			moveComp();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
	

	public void showImage() {
		GoalPanel gp = new GoalPanel();

		gp.add(new JLabel(new ImageIcon("lose.png")));

		gp.setVisible(true);
	}

	public int checkGoal() {
		// System.out.println("Computer");
		if (this.ball.x <= this.goalPoint[0]
				&& (this.ball.y + this.ball.size > 257 && this.ball.y
						+ this.ball.size < 392)) {
			this.score++;
			this.sp.label.setText("Score " + this.opponent.score + " - "
					+ this.score);
			return this.placeBallAfterGoal();
		}
		return 0;
	}
}
