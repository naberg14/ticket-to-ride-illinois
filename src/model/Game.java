package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class Game implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5241636390975184933L;

	public static final int NUMBEROFUPFACECARDS = 5;
	
	public ArrayList<Player> players = new ArrayList<Player>();
	public int currentTurn = 0; // Index of players that represents the current turn.
	public Queue<Card> drawableCards = new LinkedList<Card>();
	public ArrayList<Card> upfaceDrawableCards = new ArrayList<Card>(NUMBEROFUPFACECARDS);
	public ArrayList<Card> discardedCards = new ArrayList<Card>();
	public Queue<Route> drawableRoutes = new LinkedList<Route>();
	public ArrayList<Route> discardedRoutes = new ArrayList<Route>();
	public ArrayList<Route> longRoutes = new ArrayList<Route>();
	public Map map = new Map();
	public HashMap<Player, Integer> numberOfTrainsRemaining = new HashMap<Player, Integer>();
	public HashMap<Player, Integer> numberOfStationsRemaining = new HashMap<Player, Integer>();
	public int startingNumberOfTrains = 45;
	public int startingNumberOfStations = 3;
	public Player lastPlayer = null;
	public HashMap<Player, Integer> finalScores = null;
	public boolean gameBegun = false;
	
}
