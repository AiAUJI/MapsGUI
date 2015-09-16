package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Polyline implements Serializable{

	private static final long serialVersionUID = 5044389417860781774L;
	
	public String id;
	public List<double[]> coordinates;

	/**
	 * Constructor.
	 */
	public Polyline(){
		
		this.id = UUID.randomUUID().toString();
		this.coordinates = new ArrayList<double[]>();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param coordinates List of pairs of doubles.
	 */
	public Polyline(List<double[]> coordinates){
		
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
