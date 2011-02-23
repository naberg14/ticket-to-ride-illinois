package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import version.Version;
import client.ClientRMICommManager;
import client.ICommManager;

public class TTRServer implements ITTRServer {

	public void printRemotely() {
		System.out.println("received request to print");
	}
	public static void main(String [] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			ITTRServer server = new TTRServer();
			ITTRServer stub = (ITTRServer) UnicastRemoteObject.exportObject(server, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("TTRServer", stub);
			System.out.println("Great success!");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void receiveVersionRequest(ICommManager callback) throws RemoteException {
		callback.receiveVersion(Version.currentVersion);
	}
}
