package AbtractClasses;



import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;

import GameComponents.Ball;



public abstract class Player extends Thread {
	public double dy;
	public int x;
	public double y;
	public double upperLimitY;
	public double lowerLimitY;
	public double range;
	private Image image;
	public Team team;
	protected Random random=new Random();
	// private Ball ball;
	boolean me;
	double speed;
	public double minkickSpeed;

	public Player(boolean me, Team team) {
		ImageIcon ii;
		this.team=team;
		// this.ball = ball;
		if (me)
			ii = new ImageIcon("player.png");
		else
			ii = new ImageIcon("player2.png");
		image = ii.getImage();
		this.me = me;
	}

	/*public void move() {
		if (y + dy <= lowerLimitY && y + dy >= upperLimitY)
			y += dy;
	}*/

	/*public void moveComp() {
		if (y + dy <= lowerLimitY && y + dy >= upperLimitY)
			y += dy;
		else {
			dy = -dy;
			y += dy;
		}
	}*/

	public int getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void set(int x, double y2, double d, double e,double range) {
		this.x = x;
		this.y = y2;
		this.upperLimitY = d;
		this.lowerLimitY = e;
		this.range = range;
	}

	/*public void keyPressed(KeyEvent e) {

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
	}*/
	
	public double getNewLocation(){
		return dy*team.direction*team.rodFactor;
	}

	public Image getImage() {
		return image;
	}

	public Rectangle getBounds() {
		return new Rectangle(x + 8, (int)y, 1, 36);
	}
	
	public boolean collision(Ball b) {
		return b.getBounds().intersects(this.getBounds());
	}
	
	public abstract int[] getNext();

	// @Override

	/*
	 * public void run() { /*while(true){
	 * if(Math.abs(ball.ball.getCenterX()-x)<=
	 * 10&&Math.abs(ball.ball.getCenterY()-y)<=20) ball.dx=-ball.dx; }
	 */
	/*
	 * while(true){ synchronized (ball) { while(ball.ball.getCenterX()!=x) try {
	 * ball.wait(); } catch (InterruptedException e) { e.printStackTrace(); } }
	 * 
	 * if(Math.abs(ball.ball.getCenterX()-x)<=10&&Math.abs(ball.ball.getCenterY()
	 * -y)<=20) ball.dx=-ball.dx; }
	 * 
	 * }
	 */

}
