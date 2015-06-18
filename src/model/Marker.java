package model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Represents all the data of a marker in the map.
 */

public class Marker implements Serializable{

	private static final long serialVersionUID = -7178263963523083855L;
	
	public String id;
	public double coordinates[];
	public String name;
	public String information;
	public String icon;
	
	/**
	 * Default constructor. 
	 */
	public Marker(){
		
		this.id = UUID.randomUUID().toString();
		this.coordinates = new double[]{0.0,0.0};
		this.name = "";
		this.information = "";
		this.icon = "";
	}
	
	/**
	 * Constructor.
	 * 
	 * @param coordinates An array of Latitude and Longitude.
	 * @param name Name of the marker.
	 * @param information Information to be displayed. (Supports HTML syntax).
	 * @param icon URL to the icon to be displayed.
	 */
	public Marker(double[] coordinates, String name, String information, String icon){
		
		this.id = UUID.randomUUID().toString();
		this.coordinates = coordinates;
		this.name = name;
		this.information = information;
		this.icon = icon;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param coordinates An array of Latitude and Longitude.
	 * @param name Name of the marker.
	 */
	public Marker(double[] coordinates, String name){
		
		this.id = UUID.randomUUID().toString();
		this.coordinates = coordinates;
		this.name = name;
		this.information = "";
		this.icon = "";
	}
		
	/**
	 * This returns a nice String to pass to the JS function.
	 */
	@Override
	public String toString(){
		
		return "\"" + this.id + "\", [" + this.coordinates[0] + ", " + this.coordinates[1] + "], \"" + this.name + "\", \"" + this.information + "\", \"" + this.icon + "\"";
	}
}
