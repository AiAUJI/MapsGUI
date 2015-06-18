package agent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Marker;
import model.Polyline;
import model.Route;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

/**
 * This behaviour sends a Marker, Route and Polyline to the API.
 * This is for demo purposes only.
 *
 */
public class SenderBehaviour extends Behaviour{

	private static final long serialVersionUID = -2826285439534419110L;

	private Agent agent;

	public SenderBehaviour(Agent agent){

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
		marker.name = "Test";
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

			String ontology = receivedMessage.getOntology();
			
			System.out.println("The API server replied with: " + ontology);
		}

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

			String ontology = receivedMessage.getOntology();
			
			System.out.println("The API server replied with: " + ontology);
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

			String ontology = receivedMessage.getOntology();
			
			System.out.println("The API server replied with: " + ontology);
		}
	}

	@Override
	public boolean done() {

		return true;
	}
}
