package GameComponents;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.Timer;

import AbtractClasses.Player;
import AbtractClasses.Team;
import Exceptions.BallAlreadyExists;
import Interfaces.Observer;



public class Board extends JPanel implements ActionListener,MouseListener {

    private Image foosballTable;
    private Timer timer;
    public Coin coin;
    private Ball ball;
    int[] error={70,30,5}; 
    Team myTeam;
    Team comp;
    Random rand = new Random();
    Formation formation;
    
    public Board(Ball ball, Team my,Team other,Coin coin) {

        initBoard();
        this.coin=coin;
        this.ball=ball;
        this.myTeam=my;
        this.comp=other;
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        
        setDoubleBuffered(true);
		if (StartGamePanel.CoinChoice.equals(coin.sideUp)) {
			ball.x = 197;
			ball.y = 318;
		} else {
			ball.x = 945;
			ball.y = 322;
		}
        addMouseListener(this);
        timer = new Timer(0,this);
        
        timer.start();
        myTeam.start();
        comp.start();
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ball.start();
        
    }
    
    private void initBoard() {
        
        loadImage();
        
        int w = foosballTable.getWidth(this);
        int h =  foosballTable.getHeight(this);
        setPreferredSize(new Dimension(w+50, h+50));        
    }
    
    private void loadImage() {
        
        ImageIcon ii = new ImageIcon("foosballTable1.jpg");
        foosballTable = ii.getImage();        
    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(foosballTable, 25, 25, this);
    }
    
    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
        	
        	
        		myTeam.keyReleased(e);
        	
        		
        }

        public void keyPressed(KeyEvent e) {
        	
        		myTeam.keyPressed(e);
        
        }
    }
    

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(ball.color);
        g2d.fill(ball.ball);
        myTeam.draw(g2d,this);
        comp.draw(g2d,this);
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		myTeam.move();
		//comp.moveComp();
        repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX()+" , "+e.getY());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}