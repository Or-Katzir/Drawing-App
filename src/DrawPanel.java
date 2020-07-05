

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JPanel;



/*
 * <h2>Class to create the panel for the drawing </h2>
 * @author Or Katzir 
 * @version 2
 */
public class DrawPanel extends JPanel {

	private Point p1;//Point object for drawing the shapes
	private Point p2;//Point object for drawing the shapes
	private int shape = -1; // Integer to represent which shape to draw next, initialized to -1 that represents no shape 
	private final int SIZE	 = 100000; //Max length of the shape array, can be change if needed.. 
	private Shapes[] shapes; // The array of the draw, holds all the shapes in the draw
	private int index = 0; //The index in the shapes array showing where to draw the next shape 
	private Color color = new Color(0, 0, 0); //The current color to draw the shapes 
	private boolean fill = false; // boolean represents if the shape should be filled or not 
	private MouseHandler handler;
	
	/*
	 * Create new panel for drawing 
	 * Create new shapes array with max size 
	 * Adding all the mouse listeners to the panel
	 */
	public DrawPanel() {

		super();
		shapes = new Shapes[SIZE];
		handler = new MouseHandler();
		
		addMouseListener(handler);
		addMouseMotionListener(handler);
		
	}

	/*
	 * Private class to handle
	 */
	private class MouseHandler extends MouseAdapter{
		
		private boolean active = false; //boolean variable for drawing resizable shapes
		
		public void mousePressed(MouseEvent event) {
			p1 = event.getPoint();
		} 
		
		
		public void mouseReleased(MouseEvent event) {
			p2 = null;
			active = false;
		}
		
		
		public void mouseDragged(MouseEvent event) {
			 
			if (index >= SIZE || shape < 0) // Check if the index of the next shape is in the array limits or if shape is -1
				return;
			
			if (shape < 4) // Check if the current shape is a line 
			{ 
				if (p2 == null)
					p1 = event.getPoint();
				else
					p1 = p2;
					p2 = event.getPoint();
				{
					drawLines();
				}
			}
			else 
			{
				p2 = event.getPoint();
				if(active) {
					index--;
				}
				active = true;
			
				if (shape == 4) {
					if(active)
						drawCircle();
				}
				if (shape == 5) {
					if(active) {
						drawRectangle();
					}
				}
			}
			repaint();
		}
		
		
		//End of MouseHandler class
	}
	
	
	
	
	private void drawLines() {
		// 	The lines start from 1 which is the most thinest to 3 which is the most thickest
		if (shape == 1) 
			shapes[index] = new Line(p1, p2);
		if (shape == 2)
			shapes[index] = new Line2(p1, p2);
		if (shape == 3)
			shapes[index] = new Line3(p1, p2);

		shapes[index].setColor(color);// setting the color of the shape, default is black
		index++;
	}
	
	private void drawCircle() {
		shapes[index] = new Circle(p1, p2, color, fill);
		index++;
	}
	
	private void drawRectangle() {
		shapes[index] = new Rectangle(p1, p2, color, fill);
		index++;
	}
	
	
	/*
	 * Painting the shapes to the screen after action from the user
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < index; i++)
			shapes[i].draw(g);
	}
	

	
	// getters and setters
	
	/*
	 * Set the current color to draw
	 * @param color the new color to draw the shape 
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/*
	 * set the shape array index 
	 * @param index the new index for the array
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/*
	 * Return the current index of the shape array
	 * @return the current index of the shapes array
	 */
	public int getIndex() {
		return index;
	}

	/*
	 * Set the current shape to draw
	 * @param shape the shape object to draw next
	 */
	public void setShape(int shape) {
		this.shape = shape;
	}

	/*
	 * Return the last shape in the draw
	 * @return the last shape object in the shape array
	 */
	public Shapes getShape() {
		if (index > 0)
			return shapes[index - 1];
		return shapes[index];
	}

	/*
	 * Return the array of all the shapes 
	 * @return the array of all the shapes in the draw
	 */
	public Shapes[] getShapes() {
		return shapes;
	}

	/*
	 * Set the shapes array
	 * @param shapes the new shapes array to draw
	 */
	public void setShapes(Shapes[] shapes) {
		this.shapes = shapes;
		repaint();
	}

	/*
	 * Return the current number that represents the shape to draw
	 * Values 1 to 3 are lines from thinest to thickest, 4 is circle, 5 is rectangle 
	 * @return the current number that represents the shape to draw
	 */
	public int getShapeNum() {
		return shape;
	}

	/*
	 * Return the current fill option 
	 * @return the current fill option
	 */
	public boolean getFill() {
		return fill;
	}

	/*
	 * Set the fill option in the draw 
	 * If true all the rectangle and the circles will be filled with color
	 * @param fill boolean option to set the fill
	 */
	public void setFill(boolean fill) {
		this.fill = fill;
	}

	
}








