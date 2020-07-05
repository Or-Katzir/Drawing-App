import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;

/*
 * 
 * Class to handle all the saving options in to the disc 
 * 
 * @author Or Katzir 
 * 
 * @version 2
 * 
 *-- This class needs work --
 * 
 */
public class Saving {
	
	private File currentDir;
	private DrawPanel panel;
	
	
	public Saving(DrawPanel p) {
		panel = p;
		currentDir = new File("C:\\");
	}

	public void writeObject(File f) throws IOException {

		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
		out.writeObject(panel.getShapes());
		out.close();
	}

	public File createFile(String fileName) throws IOException {
		File f = new File(fileName);
		f.createNewFile();
		return f;

	}

	 public File getFile()
	    {
	        JFileChooser fc = new JFileChooser(currentDir);
	        fc.showOpenDialog(null);
	        currentDir = fc.getSelectedFile();
	        return fc.getSelectedFile();
	    }
	

	 public void readObject(File f) throws IOException
	    {
	        ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
	        Shapes[] shapes;
	        try {
	            while(true)
	            {
	            	shapes=(Shapes[]) in.readObject();
	            	panel.setShapes(shapes);
	            	panel.setIndex(shapes[0].getIndex());
	            	
	            }
	        }
	        catch(EOFException e)
	        {
	           // res = res + "End Of Records";
	        }
	        catch(ClassNotFoundException e){}
	        in.close();
	    }
	

}
