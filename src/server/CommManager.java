package server;

import java.net.Socket;

public class CommManager extends Thread {

	private GameroomManager gameroomManager = null;
	private CommLink commLink = null;
	private String username = null; 
	
	public CommManager(Socket client, GameroomManager gameroomManager) {
		this.gameroomManager = gameroomManager;
		commLink = new CommLink(client, this);
		gameroomManager.addUser(this);
	}

	public void run() {
		// Begin CommLink
		commLink.start();
		
		// Begin Heartbeat
		// Send First Ping
		commLink.sendHeartbeat();
		while (true) {
			// Wait 4 seconds
			try {
				sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Check how many messages received since then
			// If 0, close.
			if (commLink.getNumberOfResponsesSinceHeartbeat() == 0) {
				commLink.close();
				commLink = null;
				gameroomManager.removeUser(this);
				break;
			}
			
			
			// If not, send new heartbeat.
			commLink.sendHeartbeat();
		}
    }

	public void processInput(String inputLine) {
		if (inputLine.matches("<Username name = '[a-zA-Z_0-9]+[ a-zA-Z_0-9]*' />")) {
			String[] splitmsg = inputLine.split("'");
			username = splitmsg[1];
		}
		else if (username == null) {
			processInvalidRequest();
		}
		else if (inputLine.equals("<Request Gameroom List />")) {
			processGameroomListRequest();
		}
		else if (inputLine.matches("<Create Gameroom name = '[a-zA-Z_0-9]+[ a-zA-Z_0-9]*' />")) {
			String[] splitmsg = inputLine.split("'");
			processCreateGameroomRequest(splitmsg[1]);
		}
		else processInvalidRequest();
	}

	private void processInvalidRequest() {
		commLink.send("<Error msg = 'Invalid Request - improperly formatted or not logged in.' />");
		
	}

	private void processCreateGameroomRequest(String gameroomName) {
		if (gameroomManager.createGameroom(gameroomName, this)) {
			commLink.send("<Success original_request = 'create_gameroom' />");
		}
		else {
			commLink.send("<Error original_request = 'create_gameroom' msg = 'You cannot create a gameroom if you are already in one.' />");
		}
	}

	private void processGameroomListRequest() {
		String[] gamerooms = gameroomManager.getGameroomList();
		StringBuffer message = new StringBuffer("<Gameroom List size = '" + gamerooms.length + "' names = [");
		for (int i = 0; i < gamerooms.length; i ++) {
			message.append("'" + gamerooms[i] + "'");
			if (i != gamerooms.length - 1)
				message.append(",");
		}
		message.append("] />");
		commLink.send(message.toString());
	}
	
	public synchronized void sendMessage(String message) {
		if (commLink != null) {
			commLink.send(message);
		}
	}
	
}
