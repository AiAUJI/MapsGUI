package main;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;

/**
 * This class allows to create all the agents without using commandline arguments.
 *
 */
public class Main {

	public static void main(String[] args) {
		
		StartGUI sg = new StartGUI();
		sg.start();
	}

}
