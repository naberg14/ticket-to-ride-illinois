package client;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.rmi.RemoteException;

import clientController.LauncherController;
import clientView.LauncherView;

public class Launcher {

	private static File f;
	private static FileChannel channel;
	private static FileLock lock;

	public static void main(String[] args) {
		// Ensure that the game is not already running.
		lockInstanceLock();
		
		// Show loading screen while we talk with the server, three second splash page.
		LauncherView view = new LauncherView();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		ClientRMICommManager commManager = new ClientRMICommManager();
		view.hide();
		view.setAlwaysOnTop(false);
		LauncherController controller = new LauncherController (view, commManager);
		try {
			commManager.registerController(controller);
			if (commManager.connect()) {
				commManager.requestVersion();
			}
			else {
				controller.switchToCannotConnectScreen();
			}
		} catch (RemoteException e) {
		}
		
	}

	private static void lockInstanceLock() {
		try {
			f = new File("TicketToRide.lock");
			// Check if the lock exist
			if (f.exists()) {
				// if exist try to delete it
				f.delete();
			}
			// Try to get the lock
			channel = new RandomAccessFile(f, "rw").getChannel();
			lock = channel.tryLock();
			if(lock == null)
			{
				// File is lock by other application
				channel.close();
				throw new RuntimeException("Only one instance of Ticket To Ride can run.");
			}
			// Add shutdown hook to release lock when application shutdown
			ShutdownHook shutdownHook = new ShutdownHook();
			Runtime.getRuntime().addShutdownHook(shutdownHook);
		} catch(IOException e) {
			throw new RuntimeException("Could not start process.", e);
		}
	}
	
    public static void unlockFile() {
        // release and delete file lock
        try {
            if(lock != null) {
                lock.release();
                channel.close();
                f.delete();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    static class ShutdownHook extends Thread {

        public void run() {
            unlockFile();
        }
    }

}
   