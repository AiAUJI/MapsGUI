package agent;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

/**
 * This behaviour sends a Marker, Route and Polyline to the API.
 * This is for demo purposes only.
 *
 */
public class DemoListenerBehaviour extends Behaviour{

	private static final long serialVersionUID = -2826285439534419110L;

	private Agent agent;

	public DemoListenerBehaviour(Agent agent){

		super();

		this.agent = agent;
		
		//Register this bidder in the "yellow pages"
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(agent.getAID());

		ServiceDescription sd = new ServiceDescription();
		sd.setType("Listener");
		sd.setName("Demo-listener");

		dfd.addServices(sd);

		try{

			DFService.register(agent, dfd);
		}catch (FIPAException fe){

			fe.printStackTrace();
		}
	}

	@Override
	public void action() {

		//Wait for the API server response
		ACLMessage receivedMessage = this.agent.blockingReceive();

		if(receivedMessage != null){

			String response = receivedMessage.getContent();

			System.out.println("Something has been clicked!! -->> " + response);
		}
	}

	@Override
	public boolean done() {

		return false;
	}
}
