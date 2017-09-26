import processing.core.PApplet;
/**
 * This class represents a player's power bar that hovers under the player. How much the power bar
 * is filled up represents the power of the shot once the player swings.
 * @author Shangyu
 * @version 5-23-2016
 */
public class PowerBar {
	private int x;
	private int y;
	private int width;
	private int height;
	private long startTime;
	private long power;
	private boolean on;
	private final long MAX_POWER =2000000000;
	private Player p;
	
	
	/**
	 * Constructs a specified player's power bar at specified y location and size.
	 * @param p player 
	 * @param y y location
	 * @param width width
	 * @param height width
	 */
	public PowerBar(Player p, int y, int width, int height)
	{
		this.x=(int)(p.getX());
		this.y=y;
		this.width=width;
		this.height=height;
		on=false;
		power=0;
		this.p=p;
	}
	
	/**
	 * Draws the power bar.
	 * @param p PApplet used to draw
	 * @post fill color set to green set, stroke color set to black
	 */
	public void draw(PApplet p)
	{
		p.noFill();
		p.stroke(0);
		p.rect(x, y, width, height);
		p.fill(p.color(0, 100, 0));
		p.stroke(p.color(0, 100, 0));
		p.rect(x,y,(int)((double)power*width/MAX_POWER),height);
		p.stroke(0);
	}
	
	
	/**
	 * Updates the location and power of the power bar.
	 */
	public void update()
	{
		if (on)
			power = Math.min(System.nanoTime()-startTime, MAX_POWER);
		x = (int)(p.getX());
		
	}
	
	/**
	 * Returns the power bar's current power
	 * @return power
	 */
	public long getPower()
	{
		return power;
	}
	
	/**
	 * Turns the power bar off, power reset to 0.
	 */
	public void stop()
	{
		power=0;
		on=false;
	}
	
	/**
	 * Turns power bar on, and begins updating power.
	 */
	public void start()
	{
		on=true;
		startTime=System.nanoTime();
	}
}
