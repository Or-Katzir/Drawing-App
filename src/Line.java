import java.awt.Graphics;
import java.awt.Point;

/*
 * <h2>Class for creating a line object to draw to the screen</h2>
 * 
 * @author Or Katzir
 * 
 * @version 2
 */
public class Line extends Shapes {

	private Point p1,p2;
	
	/*
	 * <h1>Constructor to create a line object</h1>
	 * @param p1 A point object for drawing the line
	 * @param p2 A point object for drawing the line
	 */
	public Line(Point p1, Point p2) {
		this.p1=p1;
		this.p2=p2;
	}

	/*
	 * Method to draw the line to screen 
	 */
	public void draw(Graphics g) {	
		g.setColor(color);
		g.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());

	}

}
