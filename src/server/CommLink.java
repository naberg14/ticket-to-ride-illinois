package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CommLink extends Thread {

	private Socket client = null;
	private PrintWriter out = null; 
	private BufferedReader in = null;
	private int messagesSeenSinceLastHeartbeat = 0;
	private CommManager manager = null;
	
	public CommLink(Socket socket, CommManager commManager) {
		client = socket;
		manager = commManager;
		try {
			out = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		String inputLine;
		
		while (true) {
			
			try {
				inputLine = in.readLine();
				if (inputLine != null) {
					processInput(inputLine);
				}
			} catch (IOException e) {
				break; 
			}
		}
		
	}

	private synchronized void processInput(String inputLine) {
		messagesSeenSinceLastHeartbeat++;
		if (inputLine.equals("<Pong/>")) {
			// Do nothing
		}
		else manager.processInput(inputLine);
	}
	
	public synchronized void sendHeartbeat() {
		out.println("<Ping/>");
		messagesSeenSinceLastHeartbeat = 0;
	}
	
	public void send(String msg) {
		out.println(msg);
	}
	
	public int getNumberOfResponsesSinceHeartbeat() {
		return messagesSeenSinceLastHeartbeat;
	}

	public synchronized void close() {
		try {
			client.close();
			manager = null; 
		} catch (IOException e) {
			// Do nothing
		}
		
	}

}
