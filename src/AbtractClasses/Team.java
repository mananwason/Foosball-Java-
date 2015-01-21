package AbtractClasses;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Random;

import GameComponents.Attacker;
import GameComponents.Ball;
import GameComponents.BallDirectionChange;
import GameComponents.Board;
import GameComponents.ComputerTeam;
import GameComponents.Defender;
import GameComponents.GoalKeeper;
import GameComponents.GoalPanel;
import GameComponents.MidFielder;
import GameComponents.ScorePanel;

import com.sun.xml.internal.org.jvnet.fastinfoset.stax.LowLevelFastInfosetStreamWriter;

public abstract class Team extends Thread {
	String name;
	public Player[] players = new Player[11];
	public int DFcount;
	public int MFcount;
	public int ATcount;
	public int direction;
	public Ball ball;
	public int error;
	public Team opponent;
	double minRange = 10000;
	public int[] goalPoint = new int[2];
	public int[] rowCoordinates;
	public Random rand = new Random();
	//double playerKickSpeed;
	public boolean defend = false;
	public int score;
	public GoalPanel gp;
	public ScorePanel sp;
	public boolean flag;
	public double rodFactor;

	public Team(int[] xCoordinates, int numDF, int numMF, int numAT, Ball ball,
			boolean isMe, int error) { // put xCoordinates of each player
		this.ball = ball;
		this.error = error;
		this.score=0;
		this.rowCoordinates = xCoordinates;
		this.DFcount = numDF;
		this.MFcount = numMF;
		this.ATcount = numAT;
		int playerCount = 0;
		double range, y;
		gp = new GoalPanel();
		gp.setLocation(400, 10);
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0: // range = (512-40)/2;
					// minRange = range;
				y = 68 + 236;
				players[playerCount] = new GoalKeeper(isMe, this);
				players[playerCount].set(xCoordinates[i], (int) y, 256, 352,
						352 - 256);
				playerCount++;
				break;

			case 1:
				range = (512 - 40 * this.DFcount) / (this.DFcount * 2);
				if (range < minRange)
					minRange = range;
				for (int j = 0; j < this.DFcount; j++) {
					players[playerCount] = new Defender(isMe, this);
					y = 40 * j + range * (2 * j + 1) + 68;
					players[playerCount].set(xCoordinates[i], y, y - range, y
							+ range, range);
					playerCount++;
				}
				break;

			case 2:
				range = (512 - 40 * this.MFcount) / (this.MFcount * 2);
				if (range < minRange)
					minRange = range;
				for (int j = 0; j < this.MFcount; j++) {
					players[playerCount] = new MidFielder(isMe, this);
					y = 40 * j + range * (2 * j + 1) + 68;
					players[playerCount].set(xCoordinates[i], y, y - range, y
							+ range, range);
					playerCount++;
				}
				break;

			case 3:
				range = (512 - 40 * this.ATcount) / (this.ATcount * 2);
				if (range < minRange)
					minRange = range;
				for (int j = 0; j < this.ATcount; j++) {
					players[playerCount] = new Attacker(isMe, this);
					y = 40 * j + range * (2 * j + 1) + 68;
					players[playerCount].set(xCoordinates[i], y, y - range, y
							+ range, range);
					playerCount++;
				}
				break;
			}
		}
		for (int i = 0; i < players.length; i++){
			players[i].dy = players[i].range / minRange;
			switch(error){
			case 70: players[i].minkickSpeed = 0.1;break;
			case 30 : players[i].minkickSpeed = 0.3;break;
			case 5 : players[i].minkickSpeed = 0.5;break;
			}
		}
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP) {
			direction = -1;
		}

		if (key == KeyEvent.VK_DOWN) {
			direction = 1;
		}
		
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {
			direction = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			direction = 0;
		}
	}
	
	public void move() {
		for (int i = 0; i < players.length; i++) {
			if (players[i].y + players[i].getNewLocation() <= players[i].lowerLimitY
					&& players[i].y + players[i].getNewLocation() >= players[i].upperLimitY)
				players[i].y += players[i].getNewLocation();
		}
	}

	public void moveComp() {
		for (int j = 0; j < players.length; j++) {
			if (players[j].y + players[j].dy <= players[j].lowerLimitY
					&& players[j].y + players[j].dy >= players[j].upperLimitY)
				players[j].y += players[j].dy;
			else {
				players[j].dy = -players[j].dy;
				players[j].y += players[j].dy;
			}
		}

	}

	public void draw(Graphics2D g2d, Board panel) {
		for (int i = 0; i < 11; i++)
			g2d.drawImage(players[i].getImage(), players[i].getX(),
					(int) players[i].getY(), panel);
	}

	@Override
	public void run() {
		int[] coordinates = null;
		double angle;
		while (true) {
			int x=checkGoal();
			if(x==1)
				break;
			for (int i = 0; i < 11; i++) {
				if (this.players[i].collision(ball)) {
					if (this.ball.lastContact == null)
						this.ball.lastContact = this;
					if (this.ball.lastContact.equals(this)) {
						// synchronized(this.ball){
						coordinates = players[i].getNext();
						angle = Math.atan2(coordinates[1] - players[i].y,
								coordinates[0] - players[i].x);
						double randomness = 0.5+rand.nextDouble();
						this.ball.dx=Math.cos(angle)*(players[i].minkickSpeed+ randomness);
						this.ball.dy=Math.sin(angle)*(players[i].minkickSpeed+ randomness);
						// }
					} else {
						this.ball.dx = -this.ball.dx;
						this.ball.lastContact = this;
					}
				}
			}
			if (defend && this instanceof ComputerTeam) {
				ComputerTeam temp = (ComputerTeam) this;
				temp.handleEvent(new BallDirectionChange(this.ball));
			}
		}
	}

	public void setOpponent(Team opp, int x, int y) {
		this.opponent = opp;
		goalPoint[0] = x;
		goalPoint[1] = y;
	}

	public int[] getGoalPoint() {
		int[] location = new int[2];
		location[0] = goalPoint[0];
		location[1] = rand.nextInt(96+rand.nextInt(this.error)) + 256 - (rand.nextInt(this.error));
		return location;
	}
	
	public abstract void showImage();
	public abstract int checkGoal();
	
	
	public int placeBallAfterGoal(){
		//this.score++;
		this.flag=true;
		
		System.out.println("Computer goal");
		if(this.score>=5){
			this.showImage();
			this.ball.dx=0;
			this.ball.dy=0;
			return 1;
		}
		int newX = this.opponent.players[0].x;
		int newY = (int) this.opponent.players[0].y;
		
		this.ball.y = newY + 20;
		
		System.out.println("A" + this.score);
		this.ball.dy = 0;
		if(this.opponent instanceof ComputerTeam){
			this.ball.x = newX -10;
			this.ball.dx = -1;
			
		}
		else
		{
			this.ball.dx= 1;
			this.ball.x = newX +10;
		}
		return 0;
	}
	
	public void setScorePanel(ScorePanel s){
		this.sp=s;
	}
	
	 
}
