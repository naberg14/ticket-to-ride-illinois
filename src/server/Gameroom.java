package server;

import java.util.ArrayList;

public class Gameroom {

	private String name = null;
	private ArrayList<CommManager> peopleInRoom = new ArrayList<CommManager>();
	private GameroomManager manager = null;
	
	public Gameroom(String name, CommManager creator, GameroomManager gameroomManager) {
		this.name = name;
		peopleInRoom.add(creator);
		manager = gameroomManager;
	}
	
	public String getName() {
		return name;
	}
	
	public synchronized void addPerson(CommManager person) {
		if (!peopleInRoom.contains(person))
			peopleInRoom.add(person);
	}
	
	public synchronized void removePerson(CommManager person) {
		peopleInRoom.remove(person);
		if (peopleInRoom.isEmpty()) {
			manager.removeGameroom(this);
		}
			
	}

}
