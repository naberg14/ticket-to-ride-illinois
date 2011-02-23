package model;

public class City implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7972960946195792334L;
	public String name = null;
	public Station station = null;
	
	public City(String name) {
		this.name = name;
	}
}
