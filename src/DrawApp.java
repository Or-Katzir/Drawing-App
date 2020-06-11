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
 *
 */
public class DrawApp extends JFrame implements ActionListener {

	private DrawPanel panel; // The panel to draw on 
	private JButton clear; // Button to clear the screen
	private JButton del; // Button to delete the last shape
	private JButton colorbtn; // Button to choose the color of the shape
	private Color color; //The color of the shape
	private JComboBox shapesMenu; // Drop down menu to choose the shapes to draw
	private JCheckBox fill; // Check box to choose if to fill the shape or not
	private JButton save; // Button to save the drawing to an existing file in the system
	private JButton open; // Button to load a draw to the application screen from computer files
	private JButton saveNew; // Button to save new file
	private Saving saving;

	/**
	 * Creating new GUI of the drawing application
	 */
	public DrawApp() {
		super("Drawing Program2");
		
		panel = new DrawPanel();

		

		saving = new Saving(panel);
		
		
		clear = new JButton("Clear");
		del = new JButton("Delete last");
		colorbtn = new JButton("Color");
		save = new JButton("Save");
		open=new JButton("Open");
		saveNew = new JButton("Save as");
		
		open.addActionListener(this);
		save.addActionListener(this);
		clear.addActionListener(this);
		del.addActionListener(this);
		colorbtn.addActionListener(this);
		saveNew.addActionListener(this);

		String str[] = { "SHAPES", "Line", "Line2", "Line3", "Circle", "Square" };
		shapesMenu = new JComboBox(str);

		shapesMenu.addActionListener(this);

		fill = new JCheckBox("Fill shape");
		fill.addActionListener(this);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(del);
		buttonPanel.add(clear);
		buttonPanel.add(colorbtn);
		buttonPanel.add(saveNew);
		buttonPanel.add(save);
		buttonPanel.add(open);
		buttonPanel.add(shapesMenu);
		buttonPanel.add(fill);

		buttonPanel.setBackground(Color.LIGHT_GRAY);
		
		add(buttonPanel, BorderLayout.NORTH);

		add(panel, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 700);
		setVisible(true);
	}

	/*
	 * Handles the input button from the user
	 */
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==open) //Opens an existing file from the system
		{
			   File f = saving.getFile();
	                try {
	                    saving.readObject(f);
	                }
	                catch(IOException ex)
	                {
	                	JOptionPane.showMessageDialog(this, "Error in reading from File", "Error", JOptionPane.ERROR_MESSAGE);
	                } 
		}
		if(e.getSource()== saveNew)
		{
			String fileName = JOptionPane.showInputDialog(this, "Enter file name");
			
			try {
				panel.getShapes()[0].setIndex(panel.getIndex());
				File f = saving.createFile(fileName);
				saving.writeObject(f);
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}
		if (e.getSource() == save) {
			
			panel.getShapes()[0].setIndex(panel.getIndex());
		
			File f = saving.getFile();
            try {
                saving.writeObject(f);
                JOptionPane.showMessageDialog(this, "Writing is done");
            }
            catch(IOException ex)
            {
       
                JOptionPane.showMessageDialog(this, "Error in Writing from File", "Error", JOptionPane.ERROR_MESSAGE);
	               
            }
			
		} else if (e.getSource() == clear) {
			panel.setIndex(0);
			panel.repaint();
		} else if (e.getSource() == fill) {
			if (fill.isSelected()) {
				panel.setFill(true);
			} else
				panel.setFill(false);
		} else if (e.getSource() == colorbtn) {
			color = JColorChooser.showDialog(this, "Select a color", color);
			panel.setColor(color);
		} else if (e.getSource() == del) {

			deleteShape();

		} else {
			String str = (String) shapesMenu.getSelectedItem();
			switch (str) {
			case "Line":
				panel.setShape(1);
				break;
			case "Line2":
				panel.setShape(2);
				break;
			case "Line3":
				panel.setShape(3);
				break;
			case "Circle":
				panel.setShape(4);
				break;
			case "Square":
				panel.setShape(5);
				break;
			}
		}
	}

	public void deleteShape() {
		boolean temp = (panel.getShape() instanceof Circle) || (panel.getShape() instanceof Rectangle);

		if (temp) {
			panel.setIndex(panel.getIndex() - 1);
		} else {
			int x = 30; //delete the line shape form the screen in the size of 30 dots 

			while (!temp & x > 0 & panel.getIndex() > 0) {
				panel.setIndex(panel.getIndex() - 1);
				temp = panel.getShape() instanceof Circle || panel.getShape() instanceof Rectangle;
				x--;
			}
		}
		panel.repaint();
	}

	public  DrawPanel getPanel() {
		return panel;
	}

	
	


}
