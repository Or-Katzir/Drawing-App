import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/*
 * <h2>Class to implements a circle object</h2> 
 * @author Or Katir
 * @version 2 
 */
public class Circle extends Shapes{
	
	private Point p1;
	private int width, height; //The width and height of the circle 
	
	/*
	 * <h1>Constructor to create a circle object</h1>
	 * @param p1 A point object for drawing the upper left corner of the rectangle that surround the circle
	 * @param p2 A point object for calculating the width and height of the circle
	 * @param color The color of the circle 
	 * @param fill boolean variable to set the filling option for the circle 
	 */
	public Circle(Point p1, Point p2, Color color, boolean fill) {
		this.p1 = p1;
		width = Math.abs((int)p1.getX() - (int)p2.getX());
		height = Math.abs((int)p1.getY() - (int)p2.getY());
		this.color = color;
		this.fill = fill;
	}
	
	/*
	 * Method to draw the circle object to the drawing panel 
	 * @param fill If true the circle will be filled with color
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		
		if(fill)
			g.fillOval((int)p1.getX(), (int)p1.getY(), width, height);
		
		g.drawOval((int)p1.getX(), (int)p1.getY(), width, height);
	}

}
