import java.awt.Color;
import java.awt.Graphics;


public class Circle extends Shapes{
	
	private int x,y;
	private boolean fill;
	private Color  c=new Color(0,0,0);
	
	public Circle(int x, int y) {
		this.x=x;
		this.y=y;

	}
	
	public void setColor(Color c) {
		this.c=c;
	}
	
	
	public void draw(Graphics g) {
		g.setColor(c);
		
		if(fill)
			g.fillOval(x-20, y-20, 40, 40);
		
		g.drawOval(x-20, y-20, 40, 40);
	}

	public boolean isFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}

}
