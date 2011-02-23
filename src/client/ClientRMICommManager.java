package client;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import clientController.IController;

import server.ITTRServer;
import server.TTRServer;


/**
 * An implementation of the client's RMI communication manager for TTR.  This class is constructed with a controller with which to pass server requests to.
 * This manager is only intended to send and receive messages with the server - it is the job of the controller to request that messages be sent and to
 * interpret the messages from the server.
 * @author Marc
 *
 */
public class ClientRMICommManager implements ICommManager, Remote {

	private ITTRServer server = null;
	private Vector<IController> controllers = new Vector<IController>();
	
	public boolean connect() {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			// Get the server
			Registry registry = LocateRegistry.getRegistry("localhost");
			server = (ITTRServer) registry.lookup("TTRServer");
			
			// Tell the server to connect with me
			Registry registry2 = LocateRegistry.getRegistry();
			ICommManager stub = (ICommManager)UnicastRemoteObject.exportObject((ICommManager)this, 0);
			registry.bind("ClientRMICommManager", stub);
		} catch (RemoteException e) {
			return false;
		} catch (NotBoundException e) {
			return false;
		} catch (AlreadyBoundException e) {
			
		}
		return true;
		
	}
	
	public void requestVersion() throws RemoteException {
		server.receiveVersionRequest(this);
	}
	
	public void receiveVersion(String version) throws RemoteException {
		for (IController c : controllers) {
			c.onReceiveVersion(version);
		}
	}
	public void registerController(IController controller)
			throws RemoteException {
		controllers.add(controller);
		
	}
	public void unregisterController(IController controller)
			throws RemoteException {
		controllers.remove(controller);
		
	}

	

}
