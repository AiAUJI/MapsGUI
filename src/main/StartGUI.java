package main;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

/**
 * This class allows to have a container that will be used to add new agents on runtime.
 *
 */
public class StartGUI {

	public void start(){
		
		//Get a hold on JADE runtime
		Runtime rt = Runtime.instance();

		//Exit the JVM when there are no more containers around
		rt.setCloseVM(true);
		
		//Create a profile
		Profile profile = new ProfileImpl(null, 1099, null);
			
		//Container that will hold the agents
		jade.wrapper.AgentContainer mainContainer = rt.createMainContainer(profile);
		
		//Start RMA
		try {
			AgentController agent = mainContainer.createNewAgent("rma", "jade.tools.rma.rma", new Object[0]);
			
			agent.start();
			
		} catch (StaleProxyException e1) {
			
			System.out.println("Error starting the rma agent");
			e1.printStackTrace();
		}
				
		//Create the API Agent
		try {

			AgentController agent = mainContainer.createNewAgent("GUI", "agent.APIAgent", new Object[0]);
			
			agent.start();

		} catch (StaleProxyException e) {

			System.out.println("Error starting the GUI agent");
			e.printStackTrace();
		}
		
		//Launch the demo agent
		try {

			AgentController agent = mainContainer.createNewAgent("Demo", "agent.DemoAgent", new Object[0]);
			
			agent.start();

		} catch (StaleProxyException e) {

			System.out.println("Error starting the Demo agent");
			e.printStackTrace();
		}
	}
}
