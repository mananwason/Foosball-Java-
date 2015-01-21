package GameComponents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import AbtractClasses.Event;
import AbtractClasses.Player;
import AbtractClasses.Team;
import Exceptions.BallAlreadyExists;
import Interfaces.Observable;
import Interfaces.Observer;

public class Ball extends Thread implements Observable {
	public Ellipse2D.Double ball;
	// private boolean isMoving;
	public int size, speed;
	public double x, y;
	public double dx, dy;
	public Color color;
	double ballSpeed;
	public Team lastContact;
	Observer observer;
	static int scoreA = 0;
	static int scoreB = 0;
	ScorePanel label;
	private static Ball gameBall;
	private Ball() {
		lastContact = null;
		size = 15;
		speed = 0;
		
		dx = 1;
		dy = 1;
		if (dx == 0 && dy == 0)
			dy = 1;
		ball = new Ellipse2D.Double(x, y, size, size);
		color = Color.BLACK;
		ballSpeed = 1;
	}
	
	public static Ball getInstance() throws BallAlreadyExists
	{
		if(gameBall == null)
			gameBall = new Ball();
		else
			throw new BallAlreadyExists();
		return gameBall;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return this;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, size, size);
	}

	

	public void run() {
		double oldSpeedx = 0, oldSpeedY = 0;
		while (true) {
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (x + size > 1044 || x < 106) {
				dx = -dx;
			}
			if (y + size > 580 || y < 68) {
				
				dy = -dy;
			}
			
			if (oldSpeedx != dx || oldSpeedY != dy) {
				notifyObserver();
				oldSpeedx = dx;
				oldSpeedY = dy;
			}
			if(this.lastContact!=null && this.lastContact.flag==true){
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.lastContact.flag=false;
			}
			
			x = x + dx;
			y = y + dy;
			
			ball.setFrame(x, y, size, size);
		}
	}

	@Override
	public void notifyObserver() {
		if (this.lastContact != null)
			this.lastContact.opponent.defend = true;
	}

	@Override
	public void addObserver(Observer team) {
		this.observer = team;
	}
}