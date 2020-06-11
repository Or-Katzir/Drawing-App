import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

public class Line extends Shapes {

	private Point p1,p2;
	private Color c=new Color(0,0,0);
	
	public Line(Point p1, Point p2) {
		this.p1=p1;
		this.p2=p2;
	}
	
	public void setColor(Color c) {
		this.c=c;
	}
	
	public void draw(Graphics g) {
		
		g.setColor(c);
		g.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());

	}

}
