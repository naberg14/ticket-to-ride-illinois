package model;

import java.util.ArrayList;

public class Map implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6069298441671749752L;
	public ArrayList<City> cities = new ArrayList<City>();
	public ArrayList<Connection> connections = new ArrayList<Connection>();
	
}
