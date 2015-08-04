package agent;

import jade.core.Agent;

/**
 * Demo agent to see how the API works.
 */
public class DemoAgent extends Agent{
	
	private static final long serialVersionUID = -7988169408710773757L;

	protected void setup(){
		
		addBehaviour(new DemoBehaviour(this));		
	}
}