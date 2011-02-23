package model;

import java.util.ArrayList;

public class Connection implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8236446587003121706L;
	public City a = null;
	public City b = null;
	public Player takenBy = null;
	public ArrayList<Card> requiredCards = new ArrayList<Card>();
}
