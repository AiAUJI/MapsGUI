package agent;

import model.Graph;
import model.Marker;
import model.Polyline;
import model.Route;

import java.util.Map;

import controller.Controller;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

/**
 * Behaviour that listens for messages and objects from other agents and passes
 * them to the controller to be drawn.
 * 
 */
public class APIAgentBehaviour extends Behaviour{

	private static final long serialVersionUID = 159994210262114748L;

	Controller controller;
	Agent agent;
	boolean done;

	public APIAgentBehaviour(Agent agent, Controller controller){

		super();

		this.agent = agent;
		this.controller = controller;
		this.done = false;
	}

	@Override
	public void action() {

		ACLMessage receivedMessage = this.agent.blockingReceive();

		//I have received a message
		if(receivedMessage != null){

			String command = receivedMessage.getOntology();
			Marker marker = null;
			Route route = null;
			Polyline polyline = null;
			Graph graphData = null;
			ACLMessage reply;

			switch(command){
			
				case "drawMarker":
					
					try {

						marker = (Marker) receivedMessage.getContentObject();
					} catch (UnreadableException e) {

						System.out.println("Error receiving the object.");
						e.printStackTrace();
						
						//Reply with an error code
						reply = receivedMessage.createReply();
						reply.setContent("NOT OK: The marker you passed was not correct.");
						this.agent.send(reply);
					}
					
					controller.drawMarker(marker);
					
					//Reply with a success code
					reply = receivedMessage.createReply();
					reply.setContent("OK");
					this.agent.send(reply);
					
					break;

				case "updateMarker":
					
					try {

						marker = (Marker) receivedMessage.getContentObject();
					} catch (UnreadableException e) {

						System.out.println("Error receiving the object.");
						e.printStackTrace();
						
						//Reply with an error code
						reply = receivedMessage.createReply();
						reply.setContent("NOT OK: The marker you passed was not correct.");
						this.agent.send(reply);
					}
					
					controller.updateMarker(marker);
					
					//Reply with a success code
					reply = receivedMessage.createReply();
					reply.setContent("OK");
					this.agent.send(reply);
					
					break;
					
				case "deleteMarker":

					try {

						marker = (Marker) receivedMessage.getContentObject();
					} catch (UnreadableException e) {

						System.out.println("Error receiving the object.");
						e.printStackTrace();
						
						//Reply with an error code
						reply = receivedMessage.createReply();
						reply.setContent("NOT OK: The marker you passed was not correct.");
						this.agent.send(reply);
					}
					
					controller.deleteMarker(marker);
					
					//Reply with a success code
					reply = receivedMessage.createReply();
					reply.setContent("OK");
					this.agent.send(reply);
					
					break;
					
				case "drawRoute":
					
					try {

						route = (Route) receivedMessage.getContentObject();
					} catch (UnreadableException e) {

						System.out.println("Error receiving the object.");
						e.printStackTrace();
						
						//Reply with an error code
						reply = receivedMessage.createReply();
						reply.setContent("NOT OK: The route you passed was not correct.");
						this.agent.send(reply);
					}
					
					controller.drawRoute(route);
					
					//Reply with a success code
					reply = receivedMessage.createReply();
					reply.setContent("OK");
					this.agent.send(reply);
					
					break;
					
				case "deleteRoute":
					
					try {

						route = (Route) receivedMessage.getContentObject();
					} catch (UnreadableException e) {

						System.out.println("Error receiving the object.");
						e.printStackTrace();
						
						//Reply with an error code
						reply = receivedMessage.createReply();
						reply.setContent("NOT OK: The route you passed was not correct.");
						this.agent.send(reply);
					}
					
					controller.deleteRoute(route);
					
					//Reply with a success code
					reply = receivedMessage.createReply();
					reply.setContent("OK");
					this.agent.send(reply);
					
					break;
					
				case "drawPolyline":
					
					try {

						polyline = (Polyline) receivedMessage.getContentObject();
					} catch (UnreadableException e) {

						System.out.println("Error receiving the object.");
						e.printStackTrace();
						
						//Reply with an error code
						reply = receivedMessage.createReply();
						reply.setContent("NOT OK: The polyline you passed was not correct.");
						this.agent.send(reply);
					}
					
					controller.drawPolyline(polyline);
					
					//Reply with a success code
					reply = receivedMessage.createReply();
					reply.setContent("OK");
					this.agent.send(reply);
					
					break;
				
				case "deleteMarkers":
					
					controller.deleteMarkers();
					
					//Reply with a success code
					reply = receivedMessage.createReply();
					reply.setContent("OK");
					this.agent.send(reply);
					
					break;
					
				case "deleteRoutes":
					
					controller.deleteRoutes();
					
					//Reply with a success code
					reply = receivedMessage.createReply();
					reply.setContent("OK");
					this.agent.send(reply);
					
					break;
					
				case "deleteAll":
					
					controller.deleteAll();
					
					//Reply with a success code
					reply = receivedMessage.createReply();
					reply.setContent("OK");
					this.agent.send(reply);
					
					break;
					
				case "addStringToLog1":
					
					String string1 = receivedMessage.getContent();
					
					controller.addStringLogs1(string1);
					
					//Reply with a success code
					reply = receivedMessage.createReply();
					reply.setContent("OK");
					this.agent.send(reply);
					
					break;
					
				case "addStringToLog2":
					
					String string2 = receivedMessage.getContent();
					
					controller.addStringLogs2(string2);
					
					//Reply with a success code
					reply = receivedMessage.createReply();
					reply.setContent("OK");
					this.agent.send(reply);
					
					break;
					
				case "updateChartOne":
														
					try {

						graphData = (Graph) receivedMessage.getContentObject();
					} catch (UnreadableException e) {

						System.out.println("Error receiving the object.");
						e.printStackTrace();
						
						//Reply with an error code
						reply = receivedMessage.createReply();
						reply.setContent("NOT OK: The dictionary you passed was not correct.");
						this.agent.send(reply);
					}
										
					controller.updateChartOne(graphData.data);
					
					//Reply with a success code
					reply = receivedMessage.createReply();
					reply.setContent("OK");
					this.agent.send(reply);
					
					break;
					
				case "updateChartTwo":
									
					try {

						graphData = (Graph) receivedMessage.getContentObject();
					} catch (UnreadableException e) {

						System.out.println("Error receiving the object.");
						e.printStackTrace();
						
						//Reply with an error code
						reply = receivedMessage.createReply();
						reply.setContent("NOT OK: The dictionary you passed was not correct.");
						this.agent.send(reply);
					}
					
					controller.updateChartTwo(graphData.data);
					
					//Reply with a success code
					reply = receivedMessage.createReply();
					reply.setContent("OK");
					this.agent.send(reply);
					
					break;
										
				default:
					
					//Reply with an error code
					reply = receivedMessage.createReply();
					reply.setContent("NOT OK: The function doesn't exist. Check your Ontology.");
					this.agent.send(reply);
					
					break;
			}
		}
	}

	@Override
	public boolean done() {

		return this.done;
	}
}
