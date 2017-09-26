

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;

import javax.swing.Timer;

import processing.core.PApplet;

/**
 * This class represents a match of badminton, including the players, net, shuttlecock, and scoreboard.
 * @author Andrew Liu
 * @version 5-23-2016
 */
public class Match extends PApplet{
	
	private Player p1,p2;
	private PowerBar p1Bar, p2Bar;
	private Net n;
	private Shuttlecock s;
	private ScoreBoard score;
	private Court c;
	private boolean inRally;
	private long p1SwingStart;
	private long p2SwingStart;
	private int server;
	private int winner;
	
	/**
	 * Constructs a match with 2 players and their powerbars, a net, a shuttlecock, a court, and scoreboard
	 */
	public Match()
	{
		p1 = new Player(850,300,40,1);
		p2 = new Player(350, 300, 40,2);
		p1Bar = new PowerBar(p1,530, 100, 20);
		p2Bar = new PowerBar(p2,530, 100, 20);
		n = new Net(595,400, 10, 100);
		s = new Shuttlecock(0,0);
		p1.grabShuttlecock(s);
		server=1;
		score = new ScoreBoard();
		c = new Court(50,500,1100,480);
		inRally=false;
		winner=0;

	}
	
	public void setup() {
		 noLoop();
	}
	
	/**
	 * Starts the badminton match. Match runs until someone wins.
	 */
	public void startMatch() {
		while(winner==0)
			{
			update();
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
	}
	
	/**
	 * Draws everything in a badminton match (2 players, a net, score, and a shuttlecock) over white background
	 */
	public void draw()
	{
		background(255);
		strokeWeight(3);
		p1.draw(this);
		p2.draw(this);
		n.draw(this);
		c.draw(this);
		s.draw(this);
		score.draw(this);
		p1Bar.draw(this);
		p2Bar.draw(this);
		if (winner!=0)
		{
			textSize(50);
			text("Player " + winner + " wins!",600,200);
		}
		
	}
	
	/**
	 * Updates the players, shuttlecock, and score, then redraws.
	 */
	public void update()
	{
			p1.update(n,c);
			p2.update(n,c);
			s.update(p1,p2,n,c);
			p1Bar.update();
			p2Bar.update();
			if (inRally&&!s.inRally())
			{	
				p1Bar.stop();
				p2Bar.stop();
				p1.setMovement(0,false);
				p1.setMovement(1,false);
				p2.setMovement(0,false);
				p2.setMovement(1,false);
				
				inRally=false;
				server = c.whoScored(s);
				
				if (server==1)
				{
					score.updateScore(1);
			
				}
				else if (server==2)
				{
					score.updateScore(2);
					
				}
				int one = score.getP1();
				int two = score.getP2();
				if (one>=21&&one-two>1)
				{
					winner=1;
				}
				else if (two>=21&&two-one>1)
				{
					winner=2;
				}
			}
			redraw();
		
			
				
				
			
		
	}
	
	/*
	 * LEFT: p1 move left
	 * RIGHT: p1 move right
	 * UP: p1 jump
	 * PERIOD: p1 overhand swing
	 * SLASH: p2 underhand swing
	 * SHIFT: p1 serves
	 * 
	 * A: p2 move left
	 * D: p2 move right
	 * W: p2 jump
	 * F: p2 overhand swing
	 * G: p2 underhand swing
	 * SPACE: p2 serve
	 * 
	 * R: reset (start new rally)
	 * 
	 */
	@Override
	public void keyPressed() {
		// TODO Auto-generated method stub
		if (inRally)
		{
			if (keyCode == KeyEvent.VK_LEFT) {
				p1.setMovement(0,true);
		  	} else if (keyCode == KeyEvent.VK_RIGHT) {
		  		p1.setMovement(1,true);
		  	} else if (keyCode == KeyEvent.VK_UP) {
		  		p1.jump();
		  	} else if (keyCode == KeyEvent.VK_PERIOD){
		  		if (p1.getSwingStatus()==0) {
			  		p1Bar.start();
			  		p1.setSwingStatus(-3);
		  		}
		  		
		  	} else if (keyCode == KeyEvent.VK_SLASH){
		  		if (p1.getSwingStatus()==0) {
			  		p1Bar.start();
			  		p1.setSwingStatus(-3);
		  		}
		  		
		  	} else if (keyCode == KeyEvent.VK_F){
		  		if (p2.getSwingStatus()==0) {
			  		p2Bar.start();
			  		p2.setSwingStatus(-3);
		  		}
		  		
		  	} else if (keyCode == KeyEvent.VK_G){
		  		if (p2.getSwingStatus()==0) {
			  		p2Bar.start();
			  		p2.setSwingStatus(-3);
		  		}
		  		
		  	} else if (keyCode == KeyEvent.VK_A) {
		  		p2.setMovement(0,true);
		  	} else if (keyCode == KeyEvent.VK_D) {
		  		p2.setMovement(1,true);
		  	} else if (keyCode == KeyEvent.VK_W) {
		  		p2.jump();
		  	} 
	  	} 
		else if (keyCode== KeyEvent.VK_SHIFT) {
	  		inRally = p1.serve(s);
	  	} else if (keyCode== KeyEvent.VK_SPACE) {
			inRally = p2.serve(s);
	  	}else if (keyCode== KeyEvent.VK_R) {
	  		p1.setX(850);
	  		p1.setY(300);
	  		p2.setX(350);
	  		p2.setY(300);
			if (server==1)
				p1.grabShuttlecock(s);
			else
				p2.grabShuttlecock(s);
	  	}	
	}
	public void keyReleased() {
		// TODO Auto-generated method stub
		if(inRally)
		{
			if (keyCode == KeyEvent.VK_LEFT) {
				p1.setMovement(0,false);
		  	} else if (keyCode == KeyEvent.VK_RIGHT) {
		  		p1.setMovement(1,false);
		  	} else if (keyCode == KeyEvent.VK_A) {
		  		p2.setMovement(0, false);
		  	} else if (keyCode == KeyEvent.VK_D) {
		  		p2.setMovement(1, false);
		  	} else if (keyCode == KeyEvent.VK_F) {
		  		p2.swing(p2Bar.getPower(),1);
		  		p2Bar.stop();
		  	} else if (keyCode == KeyEvent.VK_G) {
		  		p2.swing(p2Bar.getPower(),2);
		  		p2Bar.stop();
		  	} else if (keyCode == KeyEvent.VK_PERIOD) {
		  		p1.swing(p1Bar.getPower(),1);
		  		p1Bar.stop();
		  	} else if (keyCode == KeyEvent.VK_SLASH) {
		  		p1.swing(p1Bar.getPower(),2);
		  		p1Bar.stop();
		  	}
		}
		
	}
	}

	
	




