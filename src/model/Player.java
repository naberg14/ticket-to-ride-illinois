package model;

import java.util.ArrayList;




public class Player implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7510784748245230017L;
	public String username = null;
	public ArrayList<Card> cards = new ArrayList<Card>();
	public ArrayList<Route> routes = new ArrayList<Route>();

	@SuppressWarnings("unused")
	private Player() {
		
	}
	
	public Player(String name) {
		username = name;
	}
}
