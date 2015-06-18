package controller;

import view.GUI;
import model.Marker;
import model.Polyline;
import model.Route;

public class Controller {

	GUI gui;
	
	public Controller(){
		
		this.gui = null;
	}
	
	public void setGUI(GUI gui){
		
		this.gui = gui;
	}
	
	public void drawMarker(Marker marker){
		
		gui.drawMarker(marker);
	}
	
	public void updateMarker(Marker marker){
		//TODO: Update marker
	}
	
	public void deleteMarker(Marker marker){
		
		gui.deleteMarker(marker);
	}
	
	public void drawRoute(Route route){
	
		gui.drawRoute(route);
	}
	
	public void deleteRoute(Route route){
	
		gui.deleteRoute(route);
	}
	
	public void drawPolyline(Polyline polyline){
		
		gui.drawPolyline(polyline);
	}
	
	public void deleteMarkers(){
		
		gui.deleteMarkers();
	}
	
	public void deleteRoutes(){
	
		gui.deleteRoutes();
	}
	
	public void deleteAll(){
		
		gui.deleteAll();
	}
}
