package clientController;

import version.Version;
import client.ClientRMICommManager;
import client.ICommManager;
import clientView.LauncherView;

public class LauncherController implements IController {

	private LauncherView view; 
	private ICommManager commManager;
	
	private LauncherController() {
		
	}
	
	public LauncherController(LauncherView view, ICommManager commManager) {
		this.view = view;
		this.commManager = commManager;
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
			// performUpdate
		}
		
	}

	public void switchToLoginScreen() {
		// TODO Auto-generated method stub
		
	}

	public void switchToCannotConnectScreen() {
		// TODO Auto-generated method stub
		
	}

}
