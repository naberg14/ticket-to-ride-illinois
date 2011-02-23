package clientViews;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.JRootPane;

import model.Game;

import com.sun.awt.AWTUtilities;

public class LauncherView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6774252611094987175L;

	public LauncherView() {
		showLoadingScreen();
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void hide() {
		setSize(0, 0);
	}
	
	public void showLoadingScreen() {
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		AWTUtilities.setWindowShape(this, new RoundRectangle2D.Float(0, 0, 800, 533, 45, 45));
		inputContent("loading_content.ui");
		setSize(800, 533);
		setLocationRelativeTo(null);
	}
	
	public void showLoginScreen() {
		dispose();
		setUndecorated(false);
		AWTUtilities.setWindowShape(this, new Rectangle2D.Float(0, 0, 800, 600));
		setResizable(false);
		// inputContent("login.ui"); 
		
		Container content = getContentPane();
		content.removeAll();
		ImagePanel image = new ImagePanel("icon.png");
		image.setPreferredSize(new Dimension(350, 350));
		ImagePanel image2 = new ImagePanel("icon.png");
		image2.setPreferredSize(new Dimension(350, 350));
		content.add(image, BorderLayout.LINE_START);
		content.add(image2, BorderLayout.LINE_END);
		setSize(800, 533);
		setVisible(true);
	}
	
	public void showUpdateScreen() {
		// TODO Auto-generated method stub
		
	}
	
	private void inputContent(String filename) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fis);
			Container content = (Container)in.readObject();
			setContentPane(content);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}
	
	private void outputContent(String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(getContentPane());
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
