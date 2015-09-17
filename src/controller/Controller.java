package controller;

import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import view.GUI;
import model.Marker;
import model.Polyline;
import model.Route;

/**
 * Manages the communication from the world to the GUI.
 *
 */
public class Controller {

	GUI gui;
	ContainerController container;
	
	//Messages to the GUI
	public Controller(){
		
		this.gui = null;
	}
	
	public void setGUI(GUI gui){
		
		this.gui = gui;
	}
	
	public void setContainer(ContainerController container){
		
		this.container = container;
	}
	
	public void drawMarker(Marker marker){
		
		gui.drawMarker(marker);
	}
	
	public void updateMarker(Marker marker){
		
		gui.deleteMarker(marker);
		gui.drawMarker(marker);
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
	
	//Messages to the agents
	public void broadcastMessage(String msg){
		
		//This will create a singleton agent that will notify the interested parties about any event.
		try {
			
			this.container.createNewAgent("Sender", "agent.APIResponseAgent", new Object[]{msg}).start();
		} catch (StaleProxyException e) {
			
			e.printStackTrace();
		}
	}
}
