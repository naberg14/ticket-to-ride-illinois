package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import clientControllers.IController;

/*
 * 
 
 */
/**
 * An RMI Communication Manager Interface that serves as a means of relaying messages between the server and the client.
 * Implementations may connect with a controller, or handle the messages in some other way.
 * 
 * @author Marc
 * 
 */
public interface ICommManager extends Remote {

	/**
	 * Requests the version of the application from the server.  The server will return by calling {@link  client.ICommManager#receiveVersion(String version)  receiveVersion}
	 * on callback.
	 * @throws RemoteException
	 */
	public void requestVersion() throws RemoteException;
	
	/**
	 * Called by server after client calls {@link  client.ICommManager#requestVersion(ICommManager callback)  requestVersion} to handle the return of the request.
	 * @param version The current version of the application
	 * @throws RemoteException
	 */
	public void receiveVersion(String version) throws RemoteException;
	
	/**
	 * Adds the controller as a listener to the CommManager.
	 * @param controller The controller to begin listening for messages
	 * @throws RemoteException
	 */
	public void registerController(IController controller) throws RemoteException;
	
	/**
	 * Removes the controller as a listener to the CommManager.
	 * @param controller The controller to be removed from the listening array
	 * @throws RemoteException
	 */
	public void unregisterController(IController controller) throws RemoteException;
	
	/**
	 * Connects the CommManager to the server.
	 * @return <code>true</code> if the connection succeeded
	 * @throws RemoteException
	 */
	public boolean connect() throws RemoteException;
	
}
