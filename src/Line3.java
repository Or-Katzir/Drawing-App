
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Line3 extends Shapes{

	private Point p1;
	private Color c=new Color(0,0,0);
	
	public Line3(Point p1, Point p2) {
		this.p1=p1;
	}
	
	public void setColor(Color c) {
		this.c=c;
	}
	
	
	public void draw(Graphics g) {
		
		g.setColor(c);
		g.fillOval((int)p1.getX(), (int)p1.getY(), 35, 35);
		
	}


}