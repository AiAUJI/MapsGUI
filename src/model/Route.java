package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents all the data of a Route. Coordinates is a List of pairs of doubles.
 */
public class Route implements Serializable{

	private static final long serialVersionUID = -1142767651158063546L;
	
	public String id;
	public List<double[]> coordinates;
	
	/**
	 * Default constructor.
	 */
	public Route(){
		
		this.id = UUID.randomUUID().toString();
		this.coordinates = new ArrayList<double[]>();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param coordinates List of pairs of Latitude and longitude.
	 */
	public Route(List<double[]> coordinates){
		
		this.id = UUID.randomUUID().toString();
		this.coordinates = coordinates;
	}
	
	@Override
	public String toString(){
		
		StringBuilder result = new StringBuilder();
		
		for(int i=0; i<this.coordinates.size(); i++){
			
			if(i==0){
				result.append("\"" + this.id + "\", [");
			}
			
			result.append("[" + this.coordinates.get(i)[0] + ", " + this.coordinates.get(i)[1] + "]");

			//Last pair
			if(i == (this.coordinates.size()-1)){
				
				result.append("]");
			}else{
				
				result.append(", ");
			}
		}
		
		return result.toString();
	}
}
