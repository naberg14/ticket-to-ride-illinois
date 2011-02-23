package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.ICommManager;

/**
 * An interface for communicating with client RMI communication managers.
 * @author Marc
 *
 */
public interface ITTRServer extends Remote {

	/**
	 * Looks up the current version of the application and returns it to the client via <code>callback</code>.
	 * @param callback The requesting communication manager, to which the response is sent to.
	 * @throws RemoteException 
	 */
	public void receiveVersionRequest(ICommManager callback) throws RemoteException;
}
