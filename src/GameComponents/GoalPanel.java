package GameComponents;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GoalPanel extends JFrame {
	Ball scoreA;
	Ball scoreB;
	Image win;
	Image lost;
	

	public GoalPanel() {

		initUI();
		ImageIcon i2 = new ImageIcon("lose.png");
		ImageIcon i1 = new ImageIcon("win1.png");
		JLabel label1 = new JLabel(i1);
		JLabel label2 = new JLabel(i2);
		System.out.println(Ball.scoreA + "A   " + Ball.scoreB + " B    ");
		setSize(800,800);
		
		
		
		//this.setPreferredSize(new Dimension(800, 650));
		setTitle("GOAL !! Score " + Ball.scoreA + " - " + Ball.scoreB);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		

	}


	public void paintComponent(Graphics g) {
		if (Ball.scoreA < Ball.scoreB) {
			g.drawImage(win, 100, 100, this);
		} else if (Ball.scoreA > Ball.scoreB) {
			g.drawImage(lost, 100, 100, this);

		}
	}
	

	private void initUI() {
		

	}

}
