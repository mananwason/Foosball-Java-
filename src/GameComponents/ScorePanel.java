package GameComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ScorePanel extends JFrame {
	int scoreA=0;
	int scoreB=0;

	Image ba;
	JLabel label = new JLabel(scoreA + "  -  " + scoreB, SwingConstants.CENTER);

	public ScorePanel() {

		//System.out.println( + "A   " + Ball.scoreB + " B    ");
		setSize(300, 300);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10, 10));

		label.setFont(new Font("Georgia", Font.BOLD, 25));
		label.setForeground(new Color(50, 50, 25));

		panel.add(label, BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		setTitle("Score " + "User" + " - " + " Computer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// getContentPane().setBackground( Color.green );

	}

	public void paintComponent(Graphics g) {

		g.drawImage(ba, 0, 0, this);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

			}
		});
	}

}
