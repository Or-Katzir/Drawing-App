
import java.awt.*;
import java.awt.event.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

/**
 * 
 * This class will create the GUI for the application
 * @author Or Katzir
 * @version 2
 *
 */
public class DrawApp extends JFrame implements ActionListener {

	private DrawPanel drawPanel; //The panel to draw on 
	private JButton clear; //Button to clear the screen
	private JButton delete; //Button to delete the last shape
	private JButton colorbtn; //Button to choose the color of the shape
	private JButton save; //Button to save the drawing to an existing file in the system
	private JButton open; //Button to load a draw to the application screen from computer files
	private JButton newSave; //Button to save new file
	private Color color; //The color of the shape
	private JComboBox shapesMenu; //Drop down menu to choose the shapes to draw
	private JCheckBox fill; //Check box to choose if to fill the shape or not
	private Saving saving; //Saving class object to handle all the saving 
	private File file; //File object 
	private JPanel buttonPanel; //Panel to hold all the buttons
	
	
	/**
	 * Creating new GUI of the drawing application
	 */
	public DrawApp() {
		super("Drawing Program2");
		
		drawPanel = new DrawPanel();
		saving = new Saving(drawPanel);
		
		buttonsSetup();
		add(drawPanel, BorderLayout.CENTER);

		setSize(800, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
	/*
	 * Private method to set up all the buttons 
	 */
	private void buttonsSetup() {
		
		buttonPanel = new JPanel();
		
		clear = new JButton("Clear");
		delete = new JButton("Delete last");
		colorbtn = new JButton("Color");
		save = new JButton("Save");
		open=new JButton("Open");
		newSave = new JButton("Save as");
		
		open.addActionListener(this);
		save.addActionListener(this);
		clear.addActionListener(this);
		delete.addActionListener(this);
		colorbtn.addActionListener(this);
		newSave.addActionListener(this);

		String str[] = { "SHAPES", "Line", "Line2", "Line3", "Circle", "Square" };
		shapesMenu = new JComboBox(str);
		shapesMenu.addActionListener(this);

		fill = new JCheckBox("Fill shape");
		fill.addActionListener(this);

		buttonPanel.add(delete);
		buttonPanel.add(clear);
		buttonPanel.add(colorbtn);
		buttonPanel.add(newSave);
		buttonPanel.add(save);
		buttonPanel.add(open);
		buttonPanel.add(shapesMenu);
		buttonPanel.add(fill);

		buttonPanel.setBackground(Color.LIGHT_GRAY);
		
		add(buttonPanel, BorderLayout.NORTH);
	}
	
	
	/*
	 * Handles the buttons input from the user
	 */
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==open) //Opens an existing file from the system
		{
			 openFile();
		}
		if(e.getSource()== save){
			
			save();
		}
		if (e.getSource() == newSave) {
			
			newSave();
			
		} else if (e.getSource() == clear) {
			
			drawPanel.setIndex(0);
			drawPanel.repaint();
			
		} else if (e.getSource() == fill) {
			
			if (fill.isSelected()) {
				drawPanel.setFill(true);
			} else
				drawPanel.setFill(false);
			
		} else if (e.getSource() == colorbtn) {
			
			drawPanel.setColor(JColorChooser.showDialog(this, "Select a color", color));
			
		} else if (e.getSource() == delete) {

			deleteShape();

		} else {
			
			selectShape();
		}
	}
	
	
	/*
	 * Private method to set the selected shape from the user
	 */
	private void selectShape() {
		
		String str = (String) shapesMenu.getSelectedItem();
		switch (str) {
		case "Line":
			drawPanel.setShape(1);
			break;
		case "Line2":
			drawPanel.setShape(2);
			break;
		case "Line3":
			drawPanel.setShape(3);
			break;
		case "Circle":
			drawPanel.setShape(4);
			break;
		case "Square":
			drawPanel.setShape(5);
			break;
		}
	}
	
	/*
	 * Private method to open an existing draw from the file system 
	 */
	private void openFile() {
		  file = saving.getFile();
          try {
              saving.readObject(file);
          }
          catch(IOException ex)
          {
          	JOptionPane.showMessageDialog(this, "Error in reading from File", "Error", JOptionPane.ERROR_MESSAGE);
          } 
	}
	
	/*
	 * Private method to save the draw to a new file
	 * @param fileName the name of the new file
	 */
	private void newSave() {
		
		String fileName = JOptionPane.showInputDialog(this, "Enter file name");
		
		try {
			drawPanel.getShapes()[0].setIndex(drawPanel.getIndex());//*****check this *****
			file = saving.createFile(fileName);
			saving.writeObject(file);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
	}

	/*
	 * Private method to save the draw to an existing file in the system
	 */
	private void save() {
		
		drawPanel.getShapes()[0].setIndex(drawPanel.getIndex());
		
		file = saving.getFile();
        try {
            saving.writeObject(file);
            JOptionPane.showMessageDialog(this, "Writing is done");
        }
        catch(IOException ex)
        {
   
            JOptionPane.showMessageDialog(this, "Error in Writing from File", "Error", JOptionPane.ERROR_MESSAGE);
               
        }
	}
	
	/*
	 * Private method to delete the last shape from the array 
	 * If the shape is a line the method will delete a length of 30 pixels 
	 */
	private void deleteShape() {
		boolean temp = (drawPanel.getShape() instanceof Circle) || (drawPanel.getShape() instanceof Rectangle);

		if (temp) {
			drawPanel.setIndex(drawPanel.getIndex() - 1);
		} else {
			int x = 30; //delete the line shape form the screen in the size of 30 pixels 

			while (!temp & x > 0 & drawPanel.getIndex() > 0) {
				drawPanel.setIndex(drawPanel.getIndex() - 1);
				temp = drawPanel.getShape() instanceof Circle || drawPanel.getShape() instanceof Rectangle;
				x--;
			}
		}
		drawPanel.repaint();
	}

	public  DrawPanel getPanel() {
		return drawPanel;
	}

	

}




