

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import processing.core.PApplet;

/**
 * This class represents racket that a player holds. Racket can be swung, changing its angle.
 * @author Shangyu
 * @version 5-23-2016
 * 
 * 
 */
public class Racket extends MovingImage{
	
	private boolean inSwing;
	private int swingCount;
	private double angle;
	private final int LENGTH = 100;
	private final int HEAD_SIZE = 70;
	
	/**
	 * Constructs racket with x,y location and starting angle
	 * @param x xcoord of the racket
	 * @param y ycoord of the racket
	 * @param angle the angle of the racket
	 */
	public Racket(double x, double y, double angle)
	{
		super(x,y, 10, 10);
		this.angle=angle;
		inSwing = false;
		
	}
	
	/**
	 * Increments the angle of racket by specified amount.
	 * @param d amount to increment angle by
	 */
	public void moveAngle(double d)
	{
		angle+=d;
		
	}
	
	/**
	 * Sets the angle of the racket.
	 * @param d sets the angle 
	 */
	public void setAngle(double d)
	{
		angle =d;
	}
	
	/**
	 * Returns the x coordinate of the racket head.
	 * @return x coordinate of the racket head
	 */
	public double getX2()
	{
		return getX()+LENGTH*Math.cos(angle);
	}
	
	/**
	 * Returns the y coordinate of the racket head.
	 * @return y coordinate of the racket head
	 */
	public double getY2()
	{
		return getY()-LENGTH*Math.sin(angle);
	}
	
	/**
	 * Draws the racket.
	 * @param p PApplet used to draw
	 * @post ellipseMode set to CENTER
	 */
	public void draw(PApplet p)
	{
		p.line(0, -HEAD_SIZE/2,0, HEAD_SIZE/2);
		p.ellipseMode(p.CENTER);
		double x2= getX2();
		double y2 = getY2();
		p.line((int)(getX()), (int)(getY()), (int)(x2), (int)(y2));
		p.pushMatrix();
		p.translate((int)(x2), (int)(y2)); 
		p.rotate((float)(-angle+p.PI/2));
		p.fill(255);
		p.ellipse(0, 0, (int)(HEAD_SIZE/2.5), HEAD_SIZE);
		
		p.popMatrix();
		
		
		
	}
	
	/**
	 * Updates the collision rectangle of racket (contains only the racket head)
	 */
	public Area updateCollisionRect()
	{
		
		AffineTransform t = new AffineTransform();
		t.rotate(Math.PI/2-angle, getX2(), getY2());
		Area a = new Area(new Rectangle((int)((int)(getX2()-HEAD_SIZE/5)),(int)(getY2()-HEAD_SIZE/2),(int)(HEAD_SIZE/2.5),HEAD_SIZE));
		a.transform(t);
		
		setCollisionRect(a);
		return a;
		  
	}
	
	
	/**
	 * Returns angle of racket.
	 * @return angle of racket
	 */
	public double getAngle()
	{
		return angle;
	}
	
	
}
