package model;

import java.io.Serializable;
import java.util.Map;

public class Graph implements Serializable{

	private static final long serialVersionUID = -5383270866621610125L;
	
	public Map<String, Double> data = null;
	
	public Graph(Map<String, Double> data){
		
		this.data = data;
	}
}
