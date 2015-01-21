package GameComponents;

import java.util.Random;

import javax.swing.Timer;

import AbtractClasses.Team;
import Exceptions.BallAlreadyExists;

import Interfaces.Observer;

public class GameSession {
	private Ball ball;
    int[] error={70,30,5}; 
    Team myTeam;
    Team comp;
    Coin coin ;
    Random rand = new Random();
    Formation formation=null;
	public GameSession(Formation formation2,int errorLevel) {
		try {
			ball = Ball.getInstance();
		} catch (BallAlreadyExists e1) {
			// TODO Auto-generated catch block
			System.out.println(e1);
		}
        
        Formation compFormation=setCompFormation(rand.nextInt(3));
        this.coin = new Coin();
		coin.toss();
		ScorePanel sp = new ScorePanel();
		sp.setLocation(50, 100);
		sp.add(sp.label);
		sp.setVisible(true);
		
		sp.label.setText("Score " + "0" + " - " + "0");
		System.out.println(coin.sideUp);
		
		if (StartGamePanel.CoinChoice.equals(coin.sideUp)) {
			ball.x = 197;
			ball.y = 318;
		
		} else {
			ball.x = 945;
			ball.y = 322;
		}
        int[] xCoordinatesOfMyPlayers = {122,222,488,780};
        int[] xCoordinatesOfOtherPlayers = {1013,910,640,356};
        formation=formation2;
        myTeam=formation.createTeam(xCoordinatesOfMyPlayers,ball,true,30);
        
        comp=compFormation.createTeam(xCoordinatesOfOtherPlayers,ball,false,error[errorLevel]);
        System.out.println("comp error:" + comp.error);
        myTeam.setOpponent(comp, 1044, 96);
        comp.setOpponent(myTeam, 106, 96);
        myTeam.setScorePanel(sp);
        comp.setScorePanel(sp);
        ball.addObserver((Observer)comp);
        //Board board=new Board(ball,myTeam,comp,coin);
        
	}
	
	public Board getBoard(){
		return new Board(this.ball,this.myTeam,this.comp,this.coin);
	}
	
	private Formation setCompFormation(int x) {
		if (x==0) {
			return (new Formation(2, 3, 5));
		} else if (x==1) {
			return (new Formation(3, 5, 2));
		} else {
			return (new Formation(3, 4, 3));
		}
	}
}
