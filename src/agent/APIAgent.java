package agent;

import jade.core.Agent;

import javax.swing.SwingUtilities;

import view.GUI;
import controller.Controller;

/**
 * Agent that receives messages and objects and draws them in the GUI.
 */
public class APIAgent extends Agent{
		
	private static final long serialVersionUID = -3410239131357834599L;

	protected void setup(){
		
		final GUI view = new GUI();
		Controller controller = new Controller();
		
		//MVC stuff
		view.setController(controller);
		controller.setGUI(view);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				view.createGUI();
			}
		});
					
		addBehaviour(new APIAgentBehaviour(this, controller));
	}
}
