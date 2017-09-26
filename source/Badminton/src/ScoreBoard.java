

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

import processing.core.PApplet;
import processing.core.PFont;

/**
 * This class represents a scoreboard, containing the score of the players during a match.
 * @author Andrew Liu
 * @version 5-23-2016
 * 
 * 
 */
public class ScoreBoard extends JComponent{
//	private Net n;
//	private Shuttlecock s;
	private int one;
	private int two;
	
	
	/**
	 * Constructs the scoreboard of the match, with score 0-0.
	 */
	public ScoreBoard() {
		one = 0;
		two = 0;
	

	}
	
	/**
	 * Increments the score for a player.
	 * @param n player number
	 */
	public void updateScore(int n)
	{
		if (n == 1)
		{
			one+=1;
		}
		else if (n==2)
		{
			two+=1;
		}
	}
	
	/**
	 * Returns player 1 score
	 * @return player 1 score
	 */
	public int getP1()
	{
		return one;
	}
	
	/**
	 * Returns player 2 score
	 * @return player 2 score
	 */
	public int getP2()
	{
		return two;
	}

	
	/**
	 * Prints the score on top of screen
	 * @param p PApplet used to draw the scoreboard
	 * @post PApplet's noFill set
	 */
	public void draw (PApplet p) {
		p.fill(0);
		p.stroke(0);
		PFont font = p.createFont("badminton", 60);
		p.textFont(font); 
		p.textAlign(p.CENTER);
		p.text(two + " - " + one, 600, 70);
		p.noFill();
	}

}
