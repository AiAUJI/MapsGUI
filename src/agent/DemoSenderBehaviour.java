package agent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Graph;
import model.Marker;
import model.Polyline;
import model.Route;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

/**
 * This behaviour listens to the callbacks that are created when clicking or deleting a marker.
 *
 */
public class DemoSenderBehaviour extends Behaviour{

	private static final long serialVersionUID = -2826285439534419110L;

	private Agent agent;

	public DemoSenderBehaviour(Agent agent){

		super();

		this.agent = agent;
	}

	@Override
	public void action() {

		System.out.println("SLEEPING");

		//Wait for the GUI to be fully completed
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}

		System.out.println("DONE SLEEPING, sending marker");

		//Prepare the message
		ACLMessage message = new ACLMessage(ACLMessage.INFORM);

		//The Ontology is used to choose the function.
		message.setOntology("drawMarker");

		Marker marker = new Marker();

		marker.coordinates = new double[]{39.833798, -0.201700};
		marker.name = "Regular marker";
		marker.information = "Hello, I am only a <b>test</b> marker. <br> I do like <h1>HTML</h1>";

		//Set the object to send
		try {

			message.setContentObject(marker);
		} catch (IOException e) {

			System.out.println("Error trying to serialize the object.");
			e.printStackTrace();
		}

		//Sets the agent to send by name
		message.addReceiver( new AID( "GUI", AID.ISLOCALNAME) );

		//Actually send it
		this.agent.send(message);

		//Wait for the API server response
		ACLMessage receivedMessage = this.agent.blockingReceive();

		if(receivedMessage != null){

			String response = receivedMessage.getContent();

			System.out.println("The API server replied with: " + response);
		}

		//Send string to the logs
		message.setOntology("addStringToLog1");
		message.setContent("Added the regular marker.");

		//Actually send it
		this.agent.send(message);

		//Wait for the API server response
		receivedMessage = this.agent.blockingReceive();

		if(receivedMessage != null){

			String response = receivedMessage.getContent();

			System.out.println("The API server replied with: " + response);
		}

		//Police marker

		//Prepare the message
		message = new ACLMessage(ACLMessage.INFORM);

		//The Ontology is used to choose the function.
		message.setOntology("drawMarker");

		marker = new Marker();

		marker.coordinates = new double[]{39.956991, -0.165349};
		marker.name = "Police";
		marker.information = "To serve and protect.";
		marker.icon = "https://raw.githubusercontent.com/AiAUJI/MapsGUI/master/src/icon/police.png";

		//Set the object to send
		try {

			message.setContentObject(marker);
		} catch (IOException e) {

			System.out.println("Error trying to serialize the object.");
			e.printStackTrace();
		}

		//Sets the agent to send by name
		message.addReceiver( new AID( "GUI", AID.ISLOCALNAME) );

		//Actually send it
		this.agent.send(message);

		//Wait for the API server response
		receivedMessage = this.agent.blockingReceive();

		if(receivedMessage != null){

			String response = receivedMessage.getContent();

			System.out.println("The API server replied with: " + response);
		}

		//Send string to the logs
		message.setOntology("addStringToLog1");
		message.setContent("Added the Police.");

		//Actually send it
		this.agent.send(message);

		//Wait for the API server response
		receivedMessage = this.agent.blockingReceive();

		if(receivedMessage != null){

			String response = receivedMessage.getContent();

			System.out.println("The API server replied with: " + response);
		}

		//Ambulance marker

		//Prepare the message
		message = new ACLMessage(ACLMessage.INFORM);

		//The Ontology is used to choose the function.
		message.setOntology("drawMarker");

		marker = new Marker();

		marker.coordinates = new double[]{40.039723, -0.062782};
		marker.name = "Ambulance marker";
		marker.information = "Hello, I am only a <b>test</b> marker. <br> I do like <h1>HTML</h1>";
		marker.icon = "https://raw.githubusercontent.com/AiAUJI/MapsGUI/master/src/icon/Untitled.png";

		//Set the object to send
		try {

			message.setContentObject(marker);
		} catch (IOException e) {

			System.out.println("Error trying to serialize the object.");
			e.printStackTrace();
		}

		//Sets the agent to send by name
		message.addReceiver( new AID( "GUI", AID.ISLOCALNAME) );

		//Actually send it
		this.agent.send(message);

		//Wait for the API server response
		receivedMessage = this.agent.blockingReceive();

		if(receivedMessage != null){

			String response = receivedMessage.getContent();

			System.out.println("The API server replied with: " + response);
		}

		//Send string to the logs
		message.setOntology("addStringToLog1");
		message.setContent("Added the Ambulance marker.");

		//Actually send it
		this.agent.send(message);

		//Wait for the API server response
		receivedMessage = this.agent.blockingReceive();

		if(receivedMessage != null){

			String response = receivedMessage.getContent();

			System.out.println("The API server replied with: " + response);
		}

		//ROUTE
		//Prepare the message
		message = new ACLMessage(ACLMessage.INFORM);

		//The Ontology is used to choose the function.
		message.setOntology("drawRoute");

		//Coordinates for the route
		List<double[]> coordinates = new ArrayList<double[]>();

		//This ones are for the CV-10
		coordinates.add(new double[]{39.833798, -0.201700});
		coordinates.add(new double[]{39.860272, -0.168719});
		coordinates.add(new double[]{39.926379, -0.190941});
		coordinates.add(new double[]{39.949556, -0.179121});
		coordinates.add(new double[]{39.990981, -0.121271});
		coordinates.add(new double[]{40.007006, -0.114036});
		coordinates.add(new double[]{40.014712, -0.092352});
		coordinates.add(new double[]{40.032196, -0.082224});

		Route route = new Route(coordinates);

		//Set the object to send
		try {

			message.setContentObject(route);
		} catch (IOException e) {

			System.out.println("Error trying to serialize the object.");
			e.printStackTrace();
		}

		//Sets the agent to send by name
		message.addReceiver( new AID( "GUI", AID.ISLOCALNAME) );

		//Actually send it
		this.agent.send(message);

		//Wait for the API server response
		receivedMessage = this.agent.blockingReceive();

		if(receivedMessage != null){

			String response = receivedMessage.getContent();

			System.out.println("The API server replied with: " + response);
		}

		//Send string to the logs
		message.setOntology("addStringToLog2");
		message.setContent("Added first route.");

		//Actually send it
		this.agent.send(message);

		//Wait for the API server response
		receivedMessage = this.agent.blockingReceive();

		if(receivedMessage != null){

			String response = receivedMessage.getContent();

			System.out.println("The API server replied with: " + response);
		}

		//Prepare the message
		message = new ACLMessage(ACLMessage.INFORM);

		//The Ontology is used to choose the function.
		message.setOntology("drawPolyline");

		coordinates = new ArrayList<double[]>();

		//This draws a Sierpinski triangle
		coordinates.add(new double[]{39.97,-0.064});
		coordinates.add(new double[]{39.97, -0.044});
		coordinates.add(new double[]{39.98, -0.049});
		coordinates.add(new double[]{39.97, -0.054});
		coordinates.add(new double[]{39.98, -0.059});
		coordinates.add(new double[]{39.97, -0.064});
		coordinates.add(new double[]{39.99, -0.054});
		coordinates.add(new double[]{39.98, -0.049});
		coordinates.add(new double[]{39.98, -0.059});

		Polyline polyline = new Polyline(coordinates);

		//Set the object to send
		try {

			message.setContentObject(polyline);
		} catch (IOException e) {

			System.out.println("Error trying to serialize the object.");
			e.printStackTrace();
		}

		//Sets the agent to send by name
		message.addReceiver( new AID( "GUI", AID.ISLOCALNAME) );

		//Actually send it
		this.agent.send(message);

		//Wait for the API server response
		receivedMessage = this.agent.blockingReceive();

		if(receivedMessage != null){

			String response = receivedMessage.getContent();

			System.out.println("The API server replied with: " + response);
		}

		//Send string to the logs
		message.setOntology("addStringToLog2");
		message.setContent("Added TriForce, because is dangerous to go alone.");

		//Actually send it
		this.agent.send(message);

		//Wait for the API server response
		receivedMessage = this.agent.blockingReceive();

		if(receivedMessage != null){

			String response = receivedMessage.getContent();

			System.out.println("The API server replied with: " + response);
		}

		//Graph stuff
		//Graph one
		Map<String, Double> data = new HashMap<String, Double>();

		data.put("Area 0", Math.random());
		data.put("Area 1", Math.random());
		data.put("Area 2", Math.random());
		data.put("Area 3", Math.random());
		data.put("Area 4", Math.random());
		data.put("Area 5", Math.random());
		data.put("Area 6", Math.random());
		data.put("Area 7", Math.random());
		data.put("Area 8", Math.random());
		data.put("Area 9", Math.random());
		data.put("Area 10", Math.random());

		Graph graphData = new Graph(data);
		
		//Prepare the message
		message = new ACLMessage(ACLMessage.INFORM);

		//The Ontology is used to choose the function.
		message.setOntology("updateChartOne");

		System.out.println("SENDING DICT");
		//Set the object to send
		try {

			message.setContentObject(graphData);
		} catch (IOException e) {

			System.out.println("Error trying to serialize the object.");
			e.printStackTrace();
		}

		//Sets the agent to send by name
		message.addReceiver( new AID( "GUI", AID.ISLOCALNAME) );

		//Actually send it
		this.agent.send(message);

		//Wait for the API server response
		receivedMessage = this.agent.blockingReceive();

		if(receivedMessage != null){

			String response = receivedMessage.getContent();

			System.out.println("The API server replied with: " + response);
		}
		
		//Graph two
		data = new HashMap<String, Double>();

		data.put("Area 0", Math.random());
		data.put("Area 1", Math.random());
		data.put("Area 2", Math.random());
		data.put("Area 3", Math.random());
		data.put("Area 4", Math.random());
		data.put("Area 5", Math.random());
		data.put("Area 6", Math.random());
		data.put("Area 7", Math.random());
		data.put("Area 8", Math.random());
		data.put("Area 9", Math.random());
		data.put("Area 10", Math.random());

		graphData = new Graph(data);
		
		//Prepare the message
		message = new ACLMessage(ACLMessage.INFORM);

		//The Ontology is used to choose the function.
		message.setOntology("updateChartTwo");

		System.out.println("SENDING DICT");
		//Set the object to send
		try {

			message.setContentObject(graphData);
		} catch (IOException e) {

			System.out.println("Error trying to serialize the object.");
			e.printStackTrace();
		}

		//Sets the agent to send by name
		message.addReceiver( new AID( "GUI", AID.ISLOCALNAME) );

		//Actually send it
		this.agent.send(message);

		//Wait for the API server response
		receivedMessage = this.agent.blockingReceive();

		if(receivedMessage != null){

			String response = receivedMessage.getContent();

			System.out.println("The API server replied with: " + response);
		}
	}

	@Override
	public boolean done() {

		return true;
	}
}
