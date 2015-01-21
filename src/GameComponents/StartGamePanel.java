package GameComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class StartGamePanel extends JPanel implements ActionListener {
	int CompGoal = 0;
	int UserGoal = 1;
	JFrame frame;
	JComboBox level, coinSide, userFormation;
	JButton play;
	Image image = null;
	Formation formation = new Formation(3,3,4);
	int errorLevel = 0;
	int[] error = { 70, 30, 5 };
	public static String CoinChoice = "";

	public StartGamePanel(JFrame frame) {
		// super(new FlowLayout(FlowLayout.CENTER));
		this.frame = frame;
		this.add(new JLabel(new ImageIcon("bck.jpg")));

		Dimension g = new Dimension(800, 150);
		this.setPreferredSize(g);
		// this.setLocation(50, 10);
		// System.out.println("label 1");
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel(new GridLayout(2, 1));

		Dimension g1 = new Dimension(800, 30);
		Dimension g2 = new Dimension(800, 30);
		Dimension g3 = new Dimension(800, 60);

		p1.setPreferredSize(g1);
		p2.setPreferredSize(g2);
		p3.setPreferredSize(g3);

		String[] levels = { "Novice", "Intermediate", "Expert" };
		JTextField chooseLevel = new JTextField("CHOOSE LEVEL");
		chooseLevel.setEditable(false);
		level = new JComboBox(levels);
		level.addActionListener(this);
		p1.add(chooseLevel, BorderLayout.EAST);
		p1.add(level);
		add(p1);

		JTextField chooseCoin = new JTextField("CHOOSE HEAD OR TAIL");
		chooseCoin.setEditable(false);
		String[] coin = { "HEAD", "TAIL" };
		coinSide = new JComboBox(coin);
		coinSide.addActionListener(this);
		p2.add(chooseCoin, BorderLayout.EAST);
		p2.add(coinSide);

		add(p2);

		String[] UserFormation = { "3-3-4", "3-5-2", "3-4-3" };
		userFormation = new JComboBox(UserFormation);
		userFormation.addActionListener(this);
		JTextField chooseFormation = new JTextField("CHOOSE YOUR FORMATION");
		chooseFormation.setEditable(false);
		p3.add(chooseFormation);
		p3.add(userFormation);

		play = new JButton("START THE GAME");
		play.addActionListener(this);
		p3.add(play);
		// p3.set(this.CENTER_ALIGNMENT);
		add(p3);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// System.out.println("label 2");
		/*
		 * g.setColor(Color.DARK_GRAY); g.fillRect(25, 10, 1100,90 );
		 * g.setColor(Color.WHITE); g.drawRect(25, 10, 1100, 30); g.drawRect(25,
		 * 40, 550, 70);
		 */

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == play) {
			JFrame Image = new JFrame();
			image = new Image(formation, errorLevel);
			frame.dispose();
			String gongFile = "mu2.au";
		    InputStream in = null;
			try {
				in = new FileInputStream(gongFile);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 
		    // create an audiostream from the inputstream
		    AudioStream audioStream = null;
			try {
				audioStream = new AudioStream(in);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 
		    // play the audio clip with the audioplayer class
		    AudioPlayer.player.start(audioStream);
		}

		else if (e.getSource() == userFormation) {
			JComboBox form = (JComboBox) e.getSource();
			this.formation = setUserFormation((String) form.getSelectedItem());
		}

		else if (e.getSource() == coinSide) {
			CoinChoice = coinSide.getSelectedItem().toString();
			// System.out.println(CoinChoice);

		}

		else if (e.getSource() == level) {
			JComboBox level = (JComboBox) e.getSource();
			errorLevel = (level.getSelectedIndex());
		}

	}

	private Formation setUserFormation(String formation) {
		if (formation.equals("2-3-5")) {
			return (new Formation(2, 3, 5));
		} else if (formation.equals("3-5-2")) {
			return (new Formation(3, 5, 2));
		} else {
			return (new Formation(3, 4, 3));
		}
	}

	/*private int[] chooseLevel(int level) {
		int[] errors = new int[2];
		if (level == 0) {
			errors[0] = error[0];
			errors[1] = error[1];
			return errors;
		} else if (level == 1) {
			errors[0] = error[1];
			errors[1] = error[1];
			return errors;
		} else {
			errors[0] = error[1];
			errors[1] = error[0];
			return errors;
		}
	}*/

}
