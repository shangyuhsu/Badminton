import processing.core.PApplet;

/**
 * This class represents the badminton court.
 * @author Andrew Liu
 * @version 5-23-2016
 *
 */
public class Court extends MovingImage{

	/**
	 * Constructs the court.
	 * @param x x location
	 * @param y y location
	 * @param width width
	 * @param height height
	 */
	public Court(double x, double y, int width, int height) {
		
		super(x,y, width, height);
	}
	
	/**
	 * Draws the court
	 * @param p PApplet used to draw
	 * @post PApplet's noFill setting called
	 */
	public void draw(PApplet p)
	{
		p.noFill();
		super.draw(p);
	}
	
	/**
	 * Determines the winner of the rally.
	 * @pre Rally ended
	 * @param s the shuttlecock used during rally
	 * @return 1:player1 2:player2
	 */
	public int whoScored (Shuttlecock s) {
			if (s.getX() < (getX() + getWidth()/2) && s.getX() >= getX() || s.getX() > (getX() + getWidth())) {
				return 1;
			} else 
				return 2;
	}	
}
