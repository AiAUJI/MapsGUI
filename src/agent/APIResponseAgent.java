package agent;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;



public class APIResponseAgent {

	public static void main (String args[]) {

		Runtime rt = Runtime.instance();

		rt.setCloseVM(true);

		// Creation of a new main container

		Profile p = new ProfileImpl();

		p.setParameter(Profile.MAIN_HOST, "192.168.10.1");

		p.setParameter(Profile.MAIN_PORT, "1099");

		AgentContainer ac = rt.createMainContainer(p);

		// Agent creation on local container
													//agent_name, agent_class, agent_arguments
		//AgentController agent = ac.createNewAgent ("APIResponseAgent", "agents.AgentName", o);

		//agent.start();

	}

}