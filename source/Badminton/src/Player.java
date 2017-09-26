

import processing.core.PApplet;

/**
 * This class represents a Player that holds a racket. This player can move left and right and swing.
 * @author Shangyu
 * @version 5-23-2016
 */
public class Player extends MovingImage{
	private Racket r;
	
	private int swingStatus;
	private int swingCount;
	private double swingPower;
	private double swingMotion;
	private boolean[] movement;
	private int playerNum;
	private int stepStatus;
	private double vY;
	private final double MAX_POWER=2;
	private int groundLevel;
	private boolean hasShuttlecock;
	
	private final double GRAVITY = 1;
	
	/**
	 * Constructs a player with x,y location, size, and player number
	 * @param x xcoord of the player
	 * @param y ycoord of the player
	 * @param size size of the player
	 * @param playerNum either player 1 or player 2
	 */
	public Player(double x, double y, int size,int playerNum)
	{
		super(x,y,size,size*5);
		groundLevel=(int)(y+getHeight());
		swingStatus = 0;
		swingCount=0;
		swingPower=0;
		stepStatus = 0;
		vY=0;
		hasShuttlecock=false;
		
		movement = new boolean[2];
		movement[0] = false;
		movement[1] = false;
		this.playerNum = playerNum;
		if (playerNum==1)
		{
			swingMotion=0.30;
			r = new Racket(getRacketX(),getRacketY(), 0.9);
		}
		else if (playerNum==2)
		{
			swingMotion=-0.30;
			r = new Racket(getRacketX(),getRacketY(), Math.PI-0.9);
		}
		else
		{
			System.err.println("Invalid player");
		}
				
		
	}
	
	/**
	 * Returns the x coordinate of rackethead's center.
	 * @return x coordinate of rackethead's center
	 */
	public double getRacketX()
	{
		if (playerNum==1)
			return getX()+getWidth();
		else
			return getX();
	}
	
	/**
	 * Returns the y coordinate of rackethead's center.
	 * @return y coordinate of rackethead's center
	 */
	public double getRacketY()
	{	
		return getY()+getHeight()*5/12;
	}
	
	/**
	 * Grabs the shuttlecock in hand not holding the racket after a rally has ended.
	 * @param s shuttlecock to be grabbed
	 * @post Shuttlecock's location set according to player's location
	 */
	public void grabShuttlecock(Shuttlecock s)
	{
		if (!s.inRally())
		{
			if(playerNum == 1)
			{
				s.reset(getX()-8,getRacketY()-4);
			}
			else
			{
				s.reset(getX()+getWidth(),getRacketY()-4);
			}
			hasShuttlecock=true;
			
		}
	}
	/**
	 * Player serves the shuttlecock, only if player has the shuttlecock (shuttlecock tossed up)
	 * @param s the shuttlecock to be served
	 * @return whether the serve was executed
	 * @post shuttlecock's velocity is changed
	 * 
	 */
	public boolean serve(Shuttlecock s)
	{
		if (hasShuttlecock)
		{
			s.setRallyStatus(true);
			if (playerNum==1)
				s.setVelocity(-0.5, -10);
			else
				s.setVelocity(0.5, -10);	
			hasShuttlecock=false;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the power of the current swing
	 * @return power of the current swing (0 if not swinging)
	 */
	public double getSwingPower()
	{
		return swingPower;
	}
	
	/*
	 * Determines whether the player is on the ground.
	 * @return whether the player is on the ground
	 */
	public boolean onGround()
	{
		return getY()+getHeight()>=groundLevel;
	}
	
	/**
	 * Set the X location of player, along with his racket.
	 * @param n x location
	 */
	public void setX(double n)
	{
		super.setX(n);
		r.setX(getRacketX());
	}
	
	/**
	 * Set the Y location of player, along with his racket.
	 * @param n x location
	 */
	public void setY(double n)
	{
		super.setY(n);
		r.setY(getRacketY());
	}
	

	
	/**
	 * Moves the player in x direction, along with his racket.
	 * @param n how much to move the player by in X direction
	 */
	
	public void moveX(double n)
	{
		setX(getX()+n);
		stepStatus++;
		if(stepStatus>3)
		{
			stepStatus=0;
		}
	}
	
	/**
	 * Moves the player in y direction, along with his racket.
	 * @param n amount to move player by
	 */
	public void moveY(double n)
	{
		setY(getY()+n);
	}
	
	/**
	 * Returns player's racket
	 * @return racket
	 */
	public Racket getRacket()
	{
		return r;
	}

	/**
	 * Player swings racket
	 * @pre n is equal to 1 or 2
	 * @param power power of the swing
	 * @param n 1: overhand swing 2:underhand swing
	 */
	public void swing(double power, int n)
	{
		if (swingStatus ==-3)
			swingStatus=n;
		swingPower=power;
		
	}
	
	/**
	 * Draws the player and his racket.
	 * @param p PApplet used to draw
	 * @post ellipseMode set to CORNER
	 */
	public void draw(PApplet p)
	{
		p.fill(0);
		p.ellipseMode(p.CORNER);
		p.ellipse((int)(getX()),((int)(getY())), (int)(getWidth()), (int)(getHeight()/3));
		p.line((int)(getX()+getWidth()/2), (int)(getY()+getHeight()/3), (int)(getX()+getWidth()/2), (int)(getY()+getHeight()*2/3));
		p.line((int)(getX()+getWidth()/2), (int)(getY()+getHeight()/2), (int)(getX()+getWidth()), (int)(getY()+5*getHeight()/12));
		p.line((int)(getX()+getWidth()/2), (int)(getY()+getHeight()/2), (int)(getX()), (int)(getY()+5*getHeight()/12));
		
		if (movement[0]||movement[1])
		{
			if (stepStatus==0)
			{
				p.line((int)(getX()+getWidth()/2), (int)(getY()+2*getHeight()/3), (int)(getX()), (int)(getY()+getHeight()));
				p.line((int)(getX()+getWidth()/2), (int)(getY()+2*getHeight()/3), (int)(getX()+getWidth()), (int)(getY()+getHeight()));
			}
			else if (stepStatus==1||stepStatus==3)
			{
				p.line((int)(getX()+getWidth()/2), (int)(getY()+2*getHeight()/3), (int)(getX()+getWidth()/4), (int)(getY()+getHeight()));
				p.line((int)(getX()+getWidth()/2), (int)(getY()+2*getHeight()/3), (int)(getX()+3*getWidth()/4), (int)(getY()+getHeight()));
			}
			else if (stepStatus==2)
			{
				p.line((int)(getX()+getWidth()/2), (int)(getY()+2*getHeight()/3), (int)(getX()+getWidth()/2), (int)(getY()+getHeight()));
			}
		}
		else
		{
			p.line((int)(getX()+getWidth()/2), (int)(getY()+2*getHeight()/3), (int)(getX()), (int)(getY()+getHeight()));
			p.line((int)(getX()+getWidth()/2), (int)(getY()+2*getHeight()/3), (int)(getX()+getWidth()), (int)(getY()+getHeight()));
		}
		r.draw(p);
		
	}
	
	/**
	 * Sets the player's motion in X direction.
	 * @param n Direction of movement to set. 0: left 1: right
	 * @param b whether the player moves in the given direction.
	 */
	public void setMovement(int n, boolean b)
	{
		movement[n] = b;
	}
	

	
	/**
	 * Player jumps up, if he is on the ground.
	 */
	public void jump()
	{
		if (Math.abs(vY)<0.00001)
			vY=-14;
	}
	
	
	
	/**
	 * Updates the player's and racket's movements.
	 * @param n net used during match
	 * @param c court used during match
	 */
	public void update(Net n, Court c)
	
	{
		
		moveY((int)(vY));
		if (!onGround())
			vY+=GRAVITY;
		else
		{
			vY=0;
			setY(groundLevel-getHeight());
		}
			
		if (movement[0])
		{
			moveX(-7);
			if (intersects(n)||getX()+getWidth()>c.getX()+c.getWidth()||getX()<c.getX())
			moveX(7);
		}
		else if (movement[1])
		{
			moveX(7);
			if (intersects(n)||getX()+getWidth()>c.getX()+c.getWidth()||getX()<c.getX())
				moveX(-7);
		}
		
		if(swingStatus == 1)
		{
			r.moveAngle(swingMotion);
			swingCount++;
			if(swingCount==7)
			{
				swingStatus =-1;
			}
		}
		else if (swingStatus == -1)
		{
			
			r.moveAngle(-swingMotion/2);
			swingCount++;
			if (swingCount==21)
			{
				swingCount=0;
				swingStatus=0;
			}
		}
		else if(swingStatus == 2)
		{
			r.moveAngle(-swingMotion*1.2);
			swingCount++;
			if(swingCount==11)
			{
				swingStatus =-2;
			}
		}
		else if (swingStatus == -2)
		{
			
			r.moveAngle(swingMotion*0.6);
			swingCount++;
			if (swingCount==33)
			{
				swingCount=0;
				swingStatus=0;
			}
		}
		}
		
	
	
	/**
	 * Determines the player's current swinging motion.
	 * @return -3: ready to be swung 
	 * 			-2: rewinding from underhand
	 * 			-1: rewinding from overhand
	 * 			0: not swinging, not ready to be swung
	 * 			1: underhand swing
	 * 			2: overhand swing
	 */
	public int getSwingStatus()
	{
		return swingStatus;
	}
	
	/**
	 * Sets the player's swinging motion
	 * @param n swing status to be set 
	 * 			-3: ready to be swung 
	 * 			-2: rewinding from underhand
	 * 			-1: rewinding from overhand
	 * 			0: not swinging, not ready to be swung
	 * 			1: underhand swing
	 * 			2: overhand swing
	 */
	public void setSwingStatus(int n)
	{
		swingStatus = n;
	}
	
	
}
