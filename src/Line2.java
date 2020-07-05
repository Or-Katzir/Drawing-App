
import java.awt.Graphics;
import java.awt.Point;

/*
 * <h2>Class for creating a line object to draw to the screen</h2>
 * 
 * @author Or Katzir
 * 
 * @version 2
 */
public class Line2 extends Shapes{

	private Point p1;
	

	/*
	 * <h1>Constructor to create a line object</h1>
	 * @param p1 A point object for drawing the line
	 * @param p2 A point object for drawing the line
	 */
	public Line2(Point p1, Point p2) {
		this.p1=p1;
	}
	
	/*
	 * Method to draw the line to screen 
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)p1.getX(), (int)p1.getY(), 10, 10);
	}


}
