import java.awt.Color;

import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shapes implements Serializable{
	
	private int index;

	public void draw(Graphics g) {}
	
	public void setColor(Color c) {}

	public void setFill(boolean fill) {}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	} 
		
	
	
}
