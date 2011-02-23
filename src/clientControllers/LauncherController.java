package clientControllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import version.Version;
import client.ClientRMICommManager;
import client.ICommManager;
import clientViews.LauncherView;

public class LauncherController implements IController {

	private LauncherView view; 
	
	private LauncherController() {
		
	}
	
	public LauncherController(LauncherView view) {
		this.view = view;
	}

	/** 
	 * Checks if the client is up to date, and updates if necessary.  Manipulates the view to reflect whether or not there is an update.
	 * @param version The current version of the application
	 */
	public void onReceiveVersion(String version) {
		if (version.equals(Version.currentVersion)) {
			view.showLoginScreen();
		}
		else {
			view.showUpdateScreen();
			try {
				downloadUpdates();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void downloadUpdates() throws MalformedURLException, IOException {
		// TODO Get a download description file.
		BufferedInputStream in = new BufferedInputStream(new URL("http://ticket-to-ride-illinois.googlecode.com/svn/bin/Ticket%20To%20Ride%20Launcher%20v%200.01.jar").openStream());
		FileOutputStream fos = new FileOutputStream("Ticket To Ride Launcher.jar");
		BufferedOutputStream bout = new BufferedOutputStream(fos,1024);
		byte[] data = new byte[1024];
		int x=0;
		while((x=in.read(data,0,1024))>=0)
		{
			bout.write(data,0,x);
		}
		bout.close();
		in.close();
	}

	public void switchToLoginScreen() {
		// TODO Auto-generated method stub
		
	}

	public void switchToCannotConnectScreen() {
		// TODO Auto-generated method stub
		
	}

}
