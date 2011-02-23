package client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.rmi.RemoteException;

import version.Version;

import clientControllers.LauncherController;
import clientViews.LauncherView;

public class Launcher {

	private static File f;
	private static FileChannel channel;
	private static FileLock lock;

	public static void main(String[] args) {
		// Ensure that the game is not already running.
		lockInstanceLock();
		
		// Show loading screen, three second splash page.
		LauncherView view = new LauncherView();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		view.hide();
		view.setAlwaysOnTop(false);
		
		LauncherController controller = new LauncherController (view);
		String version = getVersion();
		controller.onReceiveVersion(version);
		
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
    
    private static String getVersion() {
    	URL url;
		InputStream is = null;
		BufferedReader in;
		String version = null;

		try {
		    url = new URL("https://ticket-to-ride-illinois.googlecode.com/svn/version");
		    is = url.openStream();
		    in = new BufferedReader(new InputStreamReader(is));
		    version = in.readLine();
		} catch (MalformedURLException mue) {
		     mue.printStackTrace();
		} catch (IOException ioe) {
		     ioe.printStackTrace();
		} finally {
		    try {
		        is.close();
		    } catch (IOException ioe) {
		    }
		}
		return version;
    }

}
   