

import processing.core.PApplet;

/**
 * This class represents a shuttlecock that interacts with rackets, the ground, and the net.
 * @author Shangyu
 * @version 5-23-2016

 */
public class Shuttlecock extends MovingImage {
	private double vX;
	private double vY;
	private final double GRAVITY = 0.55/2;
	private boolean inRally;
	private int lastHit;

	
	/**
	 * Constructs a shuttlecock at (x,y) location. 
	 * @param x xcoord of the shuttlecock
	 * @param y ycoord of the shuttlecock
	 */
	public Shuttlecock(double x, double y)
	{
		super(x,y,8,8);
		vX=0;
		vY=0;
		inRally = false;
		lastHit=0;

	}
	
	/**
	 * Set the status of the rally (whether the shuttlecock is in play)
	 * @param b whether shuttlecock is in rally
	 */
	public void setRallyStatus(boolean b)
	{
		inRally=b;
	}
	
	/**
	 * Draws the shuttlecock
	 * @param p PApplet used to draw
	 * @post ellipseMode set to CENTER
	 */
	public void draw(PApplet p)
	{
		p.ellipseMode(p.CORNER);
		p.ellipse((int)(getX()),(int)(getY()),(int)((getWidth())),(int)(getHeight()));
	}
	
	/**
	 * Set the velocity of the shuttlecock
	 * @param x velocity in X direction
	 * @param y velocity in Y direction
	 */
	public void setVelocity(double x, double y)
	{
		vX = x;
		vY= y;
	}
	
	/**
	 * Updates shuttlecock movement according to collisions.
	 * @param p1 player 1
	 * @param p2 player 2
	 * @param n the net
	 * @param c the court
	 */
	public void update(Player p1, Player p2, Net n, Court c)
	{
		if (inRally)
			{
		
				move();
				vY+=GRAVITY;
				vX*=0.99;
				if(intersects(n))
				{
					vX=-vX;
					move();
					
				}
				Racket r1 = p1.getRacket();
				Racket r2 = p2.getRacket();
				if (intersects(r1)&&lastHit!=1)
				{
					int s = p1.getSwingStatus();
					double a = r1.getAngle()+Math.PI/2;
					if (s==1)
					{
						
						double power = p1.getSwingPower()/(Math.pow(10, 8));
						vX = 2*((10+power*0.5)*Math.cos(a))/3;
						vY = 2*((-15-power*0.75)*Math.sin(a)-7)/3;
						move();
						move();
						
					}
					else if (s==2)
					{
						
						double power = p1.getSwingPower()/(Math.pow(10, 8));
						vX = -2*((7+power*0.6)*Math.cos(a)+5)/3;
						vY = 2*(-(-6-power/4)*Math.sin(a)-11)/3;
						move();
						move();
						
					}
					else if (s==0||s==-3)
					{
						vX = 2*Math.cos(a+Math.PI/2);
						vY = -2*Math.sin(a+Math.PI/2);
				
					}
					/*else if (s==2)
					{
						vX = -10*Math.cos(a-Math.PI/2);
						vY = -15*Math.sin(a-Math.PI/2)+5;
						move();
						move();
					}*/
					lastHit=1;
					
				}
				else if (intersects(r2)&&lastHit!=2)
				{
					int s = p2.getSwingStatus();
					double a = r2.getAngle()-Math.PI/2;
					if (s==1)
					{
						double power = p2.getSwingPower()/(Math.pow(10, 8));
						vX = (2*(10+power*0.5)*Math.cos(a))/3;
						vY = 2*((-15-power*0.75)*Math.sin(a)-7)/3;
						move();
						move();
						
						
					}
					else if (s==2)
					{
						
						double power = p2.getSwingPower()/(Math.pow(10, 8));
						vX = -2*((7+power*0.6)*Math.cos(a)-5)/3;
						vY = 2*(-(-6-power/4)*Math.sin(a)-11)/3;
						move();
						move();
						
					}
					else if (s==0||s==-3)
					{
						vX = 2*Math.cos(a-Math.PI/2);
						vY = 2*Math.sin(a-Math.PI/2);
				
					}
					/*else if (s==2)
					{
						vX = 10*Math.cos(a-Math.PI/2);
						vY = 15*Math.sin(a-Math.PI/2)+5;
						move();
						move();
					}*/
					lastHit=2;
					
				}
				if (intersects(c)||(getY()>c.getY()+c.getHeight()))
				{
					setY(c.getY()-7);
					inRally=false;
					vY=0;
					vX=0;	
				}
				
			}
	}
		
		
	

	/**
	 * Moves the shuttlecock based on velocity in X and Y directions.
	 */
	public void move()
	{
		setX(getX()+vX);
		setY(getY()+vY);
	}
	
	/*
	 * Returns whether the shuttlecock is in rally
	 * @return whether shuttlecock is in rally
	 */
	public boolean inRally()
	{
		return inRally;
	}
	
	/**
	 * Sets velocity to 0 and moves shuttlecock to specified location
	 * @param x x location to move to
	 * @param y y location to move to
	 */
	public void reset(double x, double y)
	{
		vX=0;
		vY=0;
		inRally=false;
		setX(x);
		setY(y);
		lastHit=0;
	}
	
	
}
