package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GameroomManager {

	ArrayList<Gameroom> gamerooms = new ArrayList<Gameroom>();
	HashMap<CommManager, Gameroom> gameroomUserIsIn = new HashMap<CommManager, Gameroom>();
	ArrayList<CommManager> chatroomUsers = new ArrayList<CommManager>();
	HashSet<CommManager> users = new HashSet<CommManager>();
	
	public synchronized String[] getGameroomList() {
		String[] gameroomNames = new String[gamerooms.size()];
		for (int i = 0; i < gamerooms.size(); i++) {
			gameroomNames[i] = gamerooms.get(i).getName();
		}
		return gameroomNames;
	}

	public synchronized void removeGameroom(Gameroom gameroom) {
		gamerooms.remove(gameroom);
		
	}

	public synchronized boolean createGameroom(String gameroomName, CommManager commManager) {
		if (gameroomUserIsIn.get(commManager) == null) {
			Gameroom gameroom = new Gameroom(gameroomName, commManager, this);
			gamerooms.add(gameroom);
			gameroomUserIsIn.put(commManager, gameroom);
			chatroomUsers.remove(commManager);
			return true;
		}
		return false; 
	}
	
	public synchronized void addUser(CommManager client) {
		if (!chatroomUsers.contains(client))
			chatroomUsers.add(client);
		users.add(client);
	}

	public synchronized void removeUser(CommManager client) {
		users.remove(client);
		chatroomUsers.remove(client);
		Gameroom gr;
		if ((gr = gameroomUserIsIn.get(client)) != null) {
			gr.removePerson(client);
		}
		gameroomUserIsIn.remove(client);
	}
}
