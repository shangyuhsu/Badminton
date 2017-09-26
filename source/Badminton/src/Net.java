import processing.core.PApplet;

/**
 * This class represents the et dividing the court.
 * @author Shangyu
 * @version 5-23-2016
 *
 */
public class Net extends MovingImage{
	
	/**
	 * Constructs net with x,y location.
	 * @param x x coord of the net
	 * @param y y coord of the net
	 * @param width width
	 * @param height height
	 */
	public Net(double x, double y, int width, int height)
	{
		super(x, y, width, height);
	}
	/**
	 * Draws the net of the badminton match between the two players
	 * @param p PApplet used to draw
	 * @post PApplet's noFill called
	 */
	public void draw(PApplet p)
	{
		p.fill(0);
		super.draw(p);
		p.noFill();
	}
}
