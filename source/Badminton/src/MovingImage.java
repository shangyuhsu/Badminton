

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Area;

import processing.core.PApplet;

/**
 * This class represents an entity that can interact with MovingImages using rectangle collisions.
 * @author Shangyu
 * @version 5-23-2016
 * 
 */
public class MovingImage{
	
	private double x,y;
	private int width,height;
	private Rectangle rect;
	private Area collisionRect;
	
	/**
	 * Constructs a MovingImage with x,y, width, and height.
	 * @param x xcoord
	 * @param y ycoord
	 * @param width width
	 * @param height height 
	 */
	public MovingImage(double x, double y, int width, int height)
	{
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		collisionRect = updateCollisionRect();
		
	}
	
	/**
	 * Draws the collision rectangle
	 * @param p PApplet used to draw
	 */
	public void draw(PApplet p)
	{
		p.rect((int)(x),(int)(y), width, height);
	}
	
	/**
	 * Checks intersection with another MovingImage
	 * @param m MovingImage to check intersection with
	 * @return whether two MovingImages intersect
	 */
	public boolean intersects(MovingImage m)
	{
		updateCollisionRect().intersect(m.updateCollisionRect());
		if (collisionRect.isEmpty())
			return false;
		return true;
	}
	
	/**
	 * Updates the collision rectangle according to x,y, width, and height.
	 * @return the updated rectangle
	 */
	public Area updateCollisionRect()
	{
		setCollisionRect(new Area(new Rectangle((int)(x),(int)(y),width,height)));
		return collisionRect;
	}
	
	/**
	 * Sets the collision rectangle.
	 * @param a the collision rectangle
	 */
	public void setCollisionRect(Area a)
	{
		collisionRect = a;
	}
	
	/**
	 * Returns x location
	 * @return x location
	 */
	public double getX()
	{
		return x;
	}
	
	/**
	 * Returns y location.
	 * @return y location
	 */
	public double getY()
	{
		return y;
	}
	
	/**
	 * Returns width 
	 * @return width
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Returns height
	 * @return height
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * Sets new x coordinate
	 * @param n new x coordinate
	 */
	public void setX(double n)
	{
		x=n;
	}
	
	/**
	 * Sets new y coordinate
	 * @param n new y coordinate
	 */
	public void setY(double n)
	{
		y=n;
	}
}
