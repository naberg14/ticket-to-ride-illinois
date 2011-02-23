package clientViews;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3056644313018771846L;
	private BufferedImage image;
	private String filename;
	
    public ImagePanel(String filename) {
       this.filename = filename;
    	try {                
    		image = ImageIO.read(new File(filename));
    		this.setSize(image.getWidth(), image.getHeight());
    	} catch (IOException ex) {
    		// handle exception...
    	}
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters

    }

    private void writeObject(java.io.ObjectOutputStream out)throws IOException{
    	out.writeObject(filename);
    }
    
    private void readObject(java.io.ObjectInputStream in)throws IOException, ClassNotFoundException{
    	filename = (String)in.readObject();
    	try {                
    		image = ImageIO.read(new File(filename));
    		this.setSize(image.getWidth(), image.getHeight());
    	} catch (IOException ex) {
    		// handle exception...
    	} 
    }
}