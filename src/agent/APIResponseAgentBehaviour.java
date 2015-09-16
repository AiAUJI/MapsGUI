package agent;

import java.util.Vector;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

/**
 * Behaviour that sends responses fromn the GUI to the interested agents.
 * 
 */
public class APIResponseAgentBehaviour extends Behaviour{

	private static final long serialVersionUID = -1133744985711453960L;

	Agent agent;
	String msg;

	public APIResponseAgentBehaviour(Agent agent, String msg){

		super();

		this.agent = agent;
		this.msg = msg;
	}

	@Override
	public void action() {

		//Find who is interested, for that we use a template and the yellow pages
		Vector<AID> listenerAgents = new Vector<AID>();

		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();

		//Filter agents
		sd.setType("Listener");

		template.addServices(sd);

		//Ask the yellow pages
		try{

			DFAgentDescription[] result = DFService.search(this.agent, template);

			for(DFAgentDescription agent: result){

				listenerAgents.add(agent.getName());
			}

		}catch (FIPAException fe){

			fe.printStackTrace();
		}

		//Prepare the message
		ACLMessage message = new ACLMessage(ACLMessage.INFORM);

		//We need to keep track of the stage and the round
		message.setContent(this.msg);

		//Add receiver agents
		for(AID aid: listenerAgents){

			message.addReceiver(aid);
		}

		//Actually send it
		this.agent.send(message);

	}

	@Override
	public boolean done() {
		
		this.agent.doDelete();
		return true;
	}
}
