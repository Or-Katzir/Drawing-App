import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shapes {
	private int x,y;
	private boolean fill=false;
	private Color c=new Color(0,0,0);
	
	public Rectangle(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public void setColor(Color c) {
		this.c=c;
	}
	
	
	public void draw(Graphics g) {
		g.setColor(c);
		
		if(fill)
			g.fillRect(x-20, y-20, 40, 40);
	
		g.drawRect(x-20, y-20, 40, 40);
	}
	
	public boolean isFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}

}
