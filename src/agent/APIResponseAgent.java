package agent;

import jade.core.Agent;

public class APIResponseAgent extends Agent{

	private static final long serialVersionUID = 4439475826835824790L;

	protected void setup() {
		
		//Get message
		String msg = (String) this.getArguments()[0];
		
		addBehaviour(new APIResponseAgentBehaviour(this, msg));
	}

}