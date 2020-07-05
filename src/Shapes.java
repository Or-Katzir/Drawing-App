
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;


/*
 * <h2> This is the father class of all the shapes in the draw</h2>
 * 
 * @author Or Katzir 
 * 
 * @version 2
 * 
 * 	Class implements Serializable for a ability to save class to the disc  
 */
public abstract class Shapes implements Serializable{
	
	private int index; //integer value to represents the current number of shapes in the array - used for saving purpose 
	protected Color color = new Color(0,0,0);
	protected boolean fill = false;

	public void draw(Graphics g) {}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}
	
	public boolean isFill() {
		return fill;
	}

	
	/*
	 * For saving purpose  - *****-need to fix this-***
	 */
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	} 
	
	
}
