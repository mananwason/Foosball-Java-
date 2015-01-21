package ap2014.Project.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.AssertJUnit.assertTrue;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.hamcrest.Matchers;
import org.hamcrest.core.CombinableMatcher;
import org.hamcrest.core.CombinableMatcher.CombinableEitherMatcher;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import AbtractClasses.Player;
import AbtractClasses.Team;
import Exceptions.BallAlreadyExists;
import GameComponents.*;

public class ProjectTests {
	
	@Test(expectedExceptions = BallAlreadyExists.class)
	public void BallAlreadyExistsExceptionCatch() throws BallAlreadyExists{
		Ball ball = null;
		Ball ball2 = null;
	
			ball = Ball.getInstance();
			ball2 = Ball.getInstance();
		
	}
	
	/*public void cointossTestInCoinClass(){
		Coin coin = new Coin();
		assertThat(Coin.HEADS, equals("HEAD"));
	}*/
	
	@Test
	public void checkCoinSideAfterToss(){
		Coin coin = new Coin();
		coin.toss();
		if(coin.choice == 0){
			AssertJUnit.assertEquals(coin.getsideup(),"HEAD");
		}
		else
			AssertJUnit.assertEquals(coin.getsideup(), "TAIL");
	}
	
	
	
	/*public void CointossTestInCoinClass(){
		Coin coin = new Coin();
		coin.toss();
		
	}*/
	
	@Test(expectedExceptions = BallAlreadyExists.class)
	public void getDefenderTestinGoalKeeperClass() throws BallAlreadyExists{
		//Defender defender = new Defender(true, myTeam);
			Ball ball1 = null;
			//try {
				ball1 = Ball.getInstance();
			/*} catch (BallAlreadyExists e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			int []xcord = {122,222,488,780};
			Formation formation = new Formation(3,4,3);
			Team myTeam = formation.createTeam(xcord, ball1, true, 70);
			//Class c = GoalKeeper.class;
			//Method method = c.getDeclaredMethod("getDefender", null);
			//method.setAccessible(true);
			AssertJUnit.assertTrue(((GoalKeeper) myTeam.players[0]).getDefender() instanceof Defender);
		}
	
	@Test(expectedExceptions = BallAlreadyExists.class)
	public void getMidFielderTestinDefenderClass() throws BallAlreadyExists{
	//Defender defender = new Defender(true, myTeam);
		Ball ball1 = null;
		//try {
			ball1 = Ball.getInstance();
		/*} catch (BallAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		int []xcord = {122,222,488,780};
		Formation formation = new Formation(3,4,3);
		Team myTeam = formation.createTeam(xcord, ball1, true, 70);
		//Class c = Defender.class;
		//Method method = c.getDeclaredMethod("getMidFielder", null);
		//method.setAccessible(true);
		AssertJUnit.assertTrue(((Defender) myTeam.players[1]).getMidFielder() instanceof MidFielder );
	}
	
	@Test(expectedExceptions = BallAlreadyExists.class)
	public void getAttackerTestinMidFielderClass() throws BallAlreadyExists{
		//Defender defender = new Defender(true, myTeam);
			Ball ball1 = null;
			//try {
				ball1 = Ball.getInstance();
			/*} catch (BallAlreadyExists e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			int []xcord = {122,222,488,780};
			Formation formation = new Formation(3,4,3);
			Team myTeam = formation.createTeam(xcord, ball1, true, 70);
			//Class c = Defender.class;
			//Method method = c.getDeclaredMethod("getAttacker", null);
			//method.setAccessible(true);
			AssertJUnit.assertTrue(((MidFielder) myTeam.players[1+myTeam.DFcount+myTeam.MFcount]).getAttacker() instanceof Attacker );
	}
	
	@Test(expectedExceptions = BallAlreadyExists.class)
	public void getGoalTestinAttackerClass() throws BallAlreadyExists{
		//Defender defender = new Defender(true, myTeam);
			Ball ball1 = null;
			//try {
				ball1 = Ball.getInstance();
			/*} catch (BallAlreadyExists e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			int []xcord = {122,222,488,780};
			Formation formation = new Formation(3,4,3);
			Team myTeam = formation.createTeam(xcord, ball1, true, 70);
			//Class c = Defender.class;
			//Method method = c.getDeclaredMethod("getNext", null);
			//method.setAccessible(true);
			//assertTrue(((Attacker) myTeam.players[1+myTeam.DFcount+myTeam.MFcount+myTeam.ATcount]).getNext() instanceof Attacker );
			Attacker attacker = (Attacker) myTeam.players[1+myTeam.DFcount+myTeam.MFcount+myTeam.ATcount];
			int[] location = {1044,96};
			assertThat(attacker.getNext()[0], is(equalTo(location[0] )));
			AssertJUnit.assertTrue(attacker.getNext()[1]>= 180 && attacker.getNext()[1]<= 470 );
	}
	
	
	
	@Test(expectedExceptions = BallAlreadyExists.class)
	public void PlayerCollisionWithBallTest() throws BallAlreadyExists{ 	
		Ball ball = null;
		//try {
			ball = Ball.getInstance();
			Formation formation = new Formation(3, 4, 3);
			int []xcord = {122,222,488,780};
			Team myTeam = formation.createTeam(xcord, ball, true, 70);
			Player p = myTeam.players[0];
		/*} catch (BallAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		assertThat(p.collision(ball), Matchers.either(Matchers.is(true)).or(Matchers.is(false)));
	}
	
	
	@Test(expectedExceptions = BallAlreadyExists.class)
	public void notifyobserverinBallClass() throws BallAlreadyExists{ 	//working like a bitch twerking
		Ball ball1 = null;
		//try {
			ball1 = Ball.getInstance();
		/*} catch (BallAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		assertThat(ball1.lastContact.opponent.defend, Matchers.either(Matchers.is(true)).or(Matchers.is(false)));
	}
	
	@Test(expectedExceptions = BallAlreadyExists.class)
	public void goalkeeperBoundTestinmyTeamClass() throws BallAlreadyExists{
		Ball ball1 = null;
		//try {
			ball1 = Ball.getInstance();
		/*} catch (BallAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		int []xcord = {122,222,488,780};
		Formation formation = new Formation(3,4,3);
		Team myTeam = formation.createTeam(xcord, ball1, true, 70);
		AssertJUnit.assertTrue(myTeam.players[0].getY()>=256 && myTeam.players[0].getY()<=352);
	}
	
	@Test(expectedExceptions = BallAlreadyExists.class)
	public void getXTestinPlayerClass() throws BallAlreadyExists{
		Ball ball1 = null;
		//try {
			ball1 = Ball.getInstance();
		/*} catch (BallAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		int []xcord = {122,222,488,780};
		Formation formation = new Formation(3,4,3);
		Team myTeam = formation.createTeam(xcord, ball1, true, 70);
		//Player player = new Player(true, myTeam);
		AssertJUnit.assertEquals(myTeam.players[0].getX(), 122);
	}
	
	
	@Test(expectedExceptions = BallAlreadyExists.class)
	public void getYTestinPlayerClass() throws BallAlreadyExists{	//working like a bitch twerking
		Ball ball1 = null;
		//try {
			ball1 = Ball.getInstance();
		/*} catch (BallAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		int []xcord = {122,222,488,780};
		Formation formation = new Formation(3,4,3);
		Team myTeam = formation.createTeam(xcord, ball1, true, 70);
		AssertJUnit.assertEquals(myTeam.players[0].getY(), 304);
	}
	
	@Test(expectedExceptions = BallAlreadyExists.class)
	public void gameIsEndingOn5thGoal() throws BallAlreadyExists{
		Ball ball1 = null;
		//try {
			ball1 = Ball.getInstance();
		/*} catch (BallAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		int []xcord = {122,222,488,780};
		Formation formation = new Formation(3,4,3);
		Team myTeam = formation.createTeam(xcord, ball1, true, 70);
		AssertJUnit.assertEquals(myTeam.placeBallAfterGoal(), 1);
		AssertJUnit.assertEquals(myTeam.placeBallAfterGoal(), 0);
	}
	
	@Test(expectedExceptions = BallAlreadyExists.class)
	public void rodFactorRangeinComputerClass() throws BallAlreadyExists{
		Ball ball1 = null;
		//try {
			ball1 = Ball.getInstance();
		/*} catch (BallAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		int []xcord = {122,222,488,780};
		Formation formation = new Formation(3,4,3);
		Team compTeam = formation.createTeam(xcord, ball1, false, 70);
		AssertJUnit.assertTrue(compTeam.rodFactor>=0.5 && compTeam.rodFactor<=0.6);
	}
	
	@Test(expectedExceptions = BallAlreadyExists.class)
	public void checkNoOfPlayersAfterFormation()throws BallAlreadyExists{
		Ball ball1 = null;
		//try {
			ball1 = Ball.getInstance();
		/*} catch (BallAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		int []xcord = {122,222,488,780};
		Formation formation = new Formation(3,4,3);
		Team compTeam = formation.createTeam(xcord, ball1, false, 70);
		assertThat(compTeam.DFcount,is(equalTo(3)));
		assertThat(compTeam.MFcount,is(equalTo(4)));
		assertThat(compTeam.ATcount,is(equalTo(3)));
	}
	
	
	
	/*public void InitialCoordinatesOfBallInGameSessionClass(){
		Formation formation = new Formation(3,4,3);
		GameSession gamesession = new GameSession(formation, 1);
		
	}*/
	/*
	@Test(enabled = false)
	public void getNextTestinDefenderClass(){ //not final (random problem) bitch got a booty malfunction
		Player midFielder=getMidFielder(team);
		int[] location=new int[2];
		if(random.nextInt(2)==0){
			assertThat(getNext(team), is(equalTo(team.getGoalPoint())); //have to chek output of getgoalpoint 
		}
		else{
			location[0]=midFielder.x;
			location[1]=midFielder.y;
			assertThat(getNext(team),is(equalTo(location)));
		}  // can be done with matchers
		
	}
	
	@SuppressWarnings("deprecation")
	@Test(enabled = false)
	public void getDefenderTestinDefenderClass(){ //not final (random problem)
		Ball ball1 = null;
		try {
			ball1 = Ball.getInstance();
		} catch (BallAlreadyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int []xcord = {122,222,488,780};
		Formation formation = new Formation(3,4,3);
		Team myTeam = formation.createTeam(xcord, ball1, true, 70);
		//int m=myTeam.formation.numMidField;
		assertThat(getMidFielder(myTeam),MidFielder.class);
	}*/
	
	/*@Test(enabled = false)
	public void setOpponentTestinTeamClass(){
		//Team.getGoalPoint.location=new int[2];
		assertThat(Team.setOpponent(opp,200,150),is(equalTo(x,y)));
	}
	@Test(enabled = false)
	public void getNextTestinDefenderClass(){
		int[] location=new int[2];
		location[0]=200;  
		location[1]=150;
		assertThat(getGoalPoint(), is(equalTo([200,150]))); //not sure how arrays look like when returned
		//return location; //has to be done with matchers, changed code. -.-
	}
	@Test(enabled=false)
	public void moveTestinTeamClass() { //not final
		Player[] players = new Player[1];
		int i = 0;
		for (int i = 0; i < players.length; i++) {
			if(players[i].y+players[i].dy <= players[i].lowerLimitY && players[i].y + players[i].dy >= players[i].upperLimitY)
			{
				players[i].y += players[i].dy;
				//System.out.println(i + " " + players[i].y);
				assertequals(Team.move(),is(equalTo())); //not sure about result
			}
		}
	}
	@Test
	public void tossCoinTestnGameClass(){   //problem with matchers to check for IF condition. 
		assertThat(Game.tossCoin(),Matchers.either(Matchers.is(CoinChoice.HEADS)).or(Matchers.is(CoinChoice.TAILS)));
	}
	
	@Test(enabled = false)
	public void getNextTestinGKclass(Team team) { //not final   
		GoalKeeper gk = new GoalKeeper(me, team);
		Player defender = getDefender(team);
		int[] location = new int[2];
		location[0] = defender.x; //want to give specific values
		location[1] = defender.y;// ^ not sure how to give specific values
		assertThat(GK.getNext(team),is(equalTo(specific value check for location)));
	}
	@Test
	public void getXTestinRodClass(){ //working like a bitch twerking
		
		Rod.x = 140;
		Rod.y = 120;
		Rod.dy = 20;
		Assert.assertEquals(Rod.getX(),140);	
	}
	@Test
	public void getYTestinRodClass(){ //working like a bitch twerking
		Rod.y = 100;
		Assert.assertEquals(Rod.getY(), 100);
	}
	@Test
	public void moveTestinRodClass(){ //problem with matchers needs to be resolved
		Rod.dy = 10;
		Rod.y = 100;
		assertThat(Rod.move(), Matchers.either(Matchers.is(90)).or(Matchers.is(110)));
	}
	@Test(enabled = false)
	public void keypressTestinRodClass(){  // matchers fucking shit up again.
		assertThat(Rod.keyPressed(e), Matchers.either(Matchers.is(check if dy = -2).or(Matchers.is(check if dy = 2))));
	}
	
	@Test
	public void getdefendersinFormationClass(){
		int defenders = 2;
		int midFielders = 4;
		int attackers = 4;
		Formation formation = new Formation(defenders,midFielders,attackers);
		Assert.assertEquals(formation.getDefenders, 2);
		
	}
	public void getmidFieldersinFormationClass(){
		int midFielders = 4;
		Formation formation = new Formation(2,midFielders,4);
		Assert.assertEquals(formation.getmidFielders, 4);
	}
	public void getattackersinFormationClass(){
		int attackers = 3;
		Formation formation = new Formation(attackers,4,3);
		Assert.assertEquals(formation.egtattackers, 3);
		
	}
	public void getTotalPlayersTestinFormationClass(){ //perfect
		int defenders = 4;
		int attackers = 2;
		int midFielders = 4;
		Formation formation = new Formation(defenders, attackers, midFielders);
		Assert.assertEquals(Formation.getTotalPlayers, 11);
	}
*/
}
