import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawPanel extends JPanel {

	private Point p1;
	private Point p2;
	private int shape = 10; // Integer represents which shape to draw next
	private final int SIZE	 = 100000; //max length of the shape array, can be change if needed.. 
	private Shapes[] shapes; // Array of the draw, holding all the shapes in the draw
	private int index = 0; //The index in the shapes array showing where to draw the next shape
	Color color = new Color(0, 0, 0);
	private boolean fill = false; // boolean represents if the shape should be filled or not 

	
	/*
	 * Create new panel for drawing 
	 * Create new shapes array with max size 
	 * Adding all the mouse listeners for the panel
	 */
	public DrawPanel() {

		super();
		shapes = new Shapes[SIZE];
		
		
		addMouseMotionListener(new MouseMotionAdapter() { //Drawing the lines to the screen
			public void mouseDragged(MouseEvent e) {
				if (shape < 4) { // Check if the current shape is a line 
					if (p2 == null)
						p1 = e.getPoint();
					else
						p1 = p2;
					p2 = e.getPoint();
					if (index < SIZE) // Check if the index of the next shape is in the array limits 
					{
						// 	The lines start from 1 which is the most thinest to 3 which is the most thickest
						if (shape == 1) 
							shapes[index] = (Shapes) new Line(p1, p2);
							//testShape[index]=1;
						if (shape == 2)
							shapes[index] = (Shapes) new Line2(p1, p2);
						   // testShape[index]=2;
						if (shape == 3)
							shapes[index] = (Shapes) new Line3(p1, p2);
							//testShape[index]=3;

						shapes[index].setColor(color);// setting the color of the shape, default is black
						index++;
						
						repaint();
					}
				}
			}
		});

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (shape == 4) {
					shapes[index] = (Shapes) new Circle(e.getX(), e.getY());
					shapes[index].setColor(color);
					shapes[index].setFill(fill);
					index++;
				}
				if (shape == 5) {
					shapes[index] = (Shapes) new Rectangle(e.getX(), e.getY());
					shapes[index].setColor(color);
					shapes[index].setFill(fill);
					index++;
				}
				repaint();
			}
		});

		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				p2 = null;
			}
		});
		
		
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
	
	public void setColor(Color c) {
		color = c;
	}

	public void setIndex(int x) {
		index = x;
	}

	public int getIndex() {
		return index;
	}

	public void setShape(int x) {
		shape = x;
	}

	public Shapes getShape() {
		if (index > 0)
			return shapes[index - 1];
		return shapes[index];
	}

	public Shapes[] getShapes() {
		return shapes;
	}

	public void setShapes(Shapes[] s) {
		shapes = s;
		repaint();
	}

	public int getShapeNum() {
		return shape;
	}

	public boolean isFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}

}
