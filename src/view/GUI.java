package view;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserFunction;
import com.teamdev.jxbrowser.chromium.JSValue;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.Marker;
import model.Polyline;
import model.Route;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The class that creates the Graphical User Interface
 *
 */
public class GUI {

	Controller controller;
	Browser browser;
	JTextArea logs1;
	JTextArea logs2;

	/**
	 * Default constructor.
	 */
	public GUI(){
		
		this.controller = null;
		this.browser = new Browser();
		this.logs1 = null;
		this.logs2 = null;

		addCallbackFunctions(browser);
	}

	public void createGUI(){

		BrowserView browserView = new BrowserView(browser);

		//Main window
		JFrame main = new JFrame();
		main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		main.setSize(800, 600);
		main.setLocationRelativeTo(null);
		main.setVisible(true);

		main.getContentPane().setLayout(new GridBagLayout());

		//Something that has to do with something
		GridBagConstraints constraintsMainPanel = new GridBagConstraints();

		//TODO CHANGE EVERYTHING

		//Icon and title
		ImageIcon img = new ImageIcon(GUI.class.getClassLoader().getResource("icon/ujilogo.png"));
		main.setTitle("Universitat Jaume I");
		main.setIconImage(img.getImage());

		//Swing voodoo
		constraintsMainPanel.fill = GridBagConstraints.BOTH;
		constraintsMainPanel.gridwidth = 2; //Take two columns
		constraintsMainPanel.gridheight = 2; //Take two rows
		constraintsMainPanel.weightx = 0.8;
		constraintsMainPanel.weighty = 0.8;
		constraintsMainPanel.gridx = 0;
		constraintsMainPanel.gridy = 0;

		//Add the browser
		main.add(browserView, constraintsMainPanel);

		//URL to be loaded by the browser
		String url = GUI.class.getClassLoader().getResource("html/index.html").toString();
		browser.loadURL(url);

		//Logs panel
		JPanel logsPanel = new JPanel();
		logsPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints constraintsLogsPanel = new GridBagConstraints();

		//First log panel
		JPanel logOnePanel = new JPanel();
		logOnePanel.setLayout(new BorderLayout());

		JLabel logOneTitle = new JLabel("Log 1", SwingConstants.CENTER);
		logOnePanel.add(logOneTitle, BorderLayout.NORTH);

		this.logs1 = new JTextArea(5, 5);
		JScrollPane logOneScroll = new JScrollPane(this.logs1); 
		this.logs1.setEditable(false);
		
		logOnePanel.add(logOneScroll, BorderLayout.CENTER);
		
		constraintsLogsPanel.fill = GridBagConstraints.BOTH;
		constraintsLogsPanel.gridwidth = 1; //Take one column
		constraintsLogsPanel.gridheight = 1; //Take one row
		constraintsLogsPanel.weightx = 1;
		constraintsLogsPanel.weighty = 0.5;
		constraintsLogsPanel.gridx = 0;
		constraintsLogsPanel.gridy = 0;

		logsPanel.add(logOnePanel, constraintsLogsPanel);

		//Second log panel
		JPanel logTwoPanel = new JPanel();
		logTwoPanel.setLayout(new BorderLayout());

		JLabel logTwoTitle = new JLabel("Log 2", SwingConstants.CENTER);
		logTwoPanel.add(logTwoTitle, BorderLayout.NORTH);

		this.logs2 = new JTextArea(5, 5);
		JScrollPane logTwoScroll = new JScrollPane(this.logs2); 
		this.logs2.setEditable(false);
		logTwoPanel.add(logTwoScroll, BorderLayout.CENTER);

		constraintsLogsPanel.fill = GridBagConstraints.BOTH;
		constraintsLogsPanel.gridwidth = 1; //Take one column
		constraintsLogsPanel.gridheight = 1; //Take one row
		constraintsLogsPanel.weightx = 1;
		constraintsLogsPanel.weighty = 0.5;
		constraintsLogsPanel.gridx = 0;
		constraintsLogsPanel.gridy = 1;

		logsPanel.add(logTwoPanel, constraintsLogsPanel);
		
		//More swing voodoo
		constraintsMainPanel.fill = GridBagConstraints.BOTH;
		constraintsMainPanel.gridwidth = 1; //Take one column
		constraintsMainPanel.gridheight = 1; //Take one row
		constraintsMainPanel.weightx = 0.2;
		constraintsMainPanel.weighty = 0.8;
		constraintsMainPanel.gridx = 2;
		constraintsMainPanel.gridy = 0;

		main.add(logsPanel, constraintsMainPanel);

		//Graphs panel
		JPanel graphsPanel = new JPanel();
		graphsPanel.setLayout(new GridBagLayout());

		GridBagConstraints constraintsGraphsPanel = new GridBagConstraints();
		
		//First graph panel
		logOnePanel = new JPanel();
		logOnePanel.setLayout(new BorderLayout());

		logOneTitle = new JLabel("Graph 1", SwingConstants.CENTER);
		logOnePanel.add(logOneTitle, BorderLayout.NORTH);

		JTextArea logOne = new JTextArea(5, 20);
		logOneScroll = new JScrollPane(logOne); 
		logOne.setEditable(false);
		logOnePanel.add(logOneScroll, BorderLayout.CENTER);
		
		constraintsGraphsPanel.fill = GridBagConstraints.BOTH;
		constraintsGraphsPanel.gridwidth = 1; //Take one column
		constraintsGraphsPanel.gridheight = 1; //Take one row
		constraintsGraphsPanel.weightx = 0.5;
		constraintsGraphsPanel.weighty = 1;
		constraintsGraphsPanel.gridx = 0;
		constraintsGraphsPanel.gridy = 0;

		graphsPanel.add(logOnePanel, constraintsGraphsPanel);

		//Second graph panel
		logTwoPanel = new JPanel();
		logTwoPanel.setLayout(new BorderLayout());

		logTwoTitle = new JLabel("Graph 2", SwingConstants.CENTER);
		logTwoPanel.add(logTwoTitle, BorderLayout.NORTH);

		JTextArea logTwo = new JTextArea(5, 20);
		logTwoScroll = new JScrollPane(logTwo); 
		logTwo.setEditable(false);
		logTwoPanel.add(logTwoScroll, BorderLayout.CENTER);

		constraintsGraphsPanel.fill = GridBagConstraints.BOTH;
		constraintsGraphsPanel.gridwidth = 1; //Take one column
		constraintsGraphsPanel.gridheight = 1; //Take one row
		constraintsGraphsPanel.weightx = 0.5;
		constraintsGraphsPanel.weighty = 1;
		constraintsGraphsPanel.gridx = 1;
		constraintsGraphsPanel.gridy = 0;

		graphsPanel.add(logTwoPanel, constraintsGraphsPanel);
		
		//More swing voodoo
		constraintsMainPanel.fill = GridBagConstraints.BOTH;
		constraintsMainPanel.gridwidth = 2; //Take one column
		constraintsMainPanel.gridheight = 1; //Take one row
		constraintsMainPanel.weightx = 0.8;
		constraintsMainPanel.weighty = 0.2;
		constraintsMainPanel.gridx = 0;
		constraintsMainPanel.gridy = 2;

		main.add(graphsPanel, constraintsMainPanel);

		//Big UJI logo
		BufferedImage myPicture = null;

		try {
			myPicture = ImageIO.read(new File(GUI.class.getClassLoader().getResource("icon/ujilogogrande.png").getPath()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(null != myPicture){
			JLabel picLabel = new JLabel(new ImageIcon(myPicture), JLabel.CENTER);

			//Even more swing voodoo
			constraintsMainPanel.fill = GridBagConstraints.BOTH;
			constraintsMainPanel.gridwidth = 1; //Take one column
			constraintsMainPanel.gridheight = 1; //Take one row
			constraintsMainPanel.weightx = 0.2;
			constraintsMainPanel.weighty = 0.2;
			constraintsMainPanel.gridx = 2;
			constraintsMainPanel.gridy = 2;
			
			picLabel.setPreferredSize(new Dimension(80, 80));

			main.add(picLabel, constraintsMainPanel);
		}
	}
	
	/**
	 * This method adds a string to the logs 1 in the GUI.
	 * 
	 * @param string String to be added.
	 */
	
	public void addStringLogs1(String string){
		
		String text = this.logs1.getText();

		if(text.isEmpty()){
		
			this.logs1.setText(string);
		} else {
			
			this.logs1.setText(text + '\n' + string);
		}
	}
	
	/**
	 * This method adds a string to the logs 2 in the GUI.
	 * 
	 * @param string String to be added.
	 */
	
	public void addStringLogs2(String string){
		
		String text = this.logs2.getText();
		
		if(text.isEmpty()){
		
			this.logs2.setText(string);
		} else {
			
			this.logs2.setText(text + '\n' + string);
		}
	}

	/**
	 * This method adds the functions that JS will use in order to communicate with Java.
	 * 
	 * @param browser Browser instance where the functions will be registered.
	 */
	public void addCallbackFunctions(Browser browser){

		//Triggered when a marker is clicked
		browser.registerFunction("markerClickedCallback", new BrowserFunction() {
			public JSValue invoke(JSValue... args) {

				controller.broadcastMessage("Clicked " + args[0].getString());

				return JSValue.create("ret");
			}
		});

		//Triggered when the delete button in a marker is clicked
		browser.registerFunction("deleteButtonClickedCallback", new BrowserFunction() {
			public JSValue invoke(JSValue... args) {

				controller.broadcastMessage("Deleted " + args[0].getString());

				return JSValue.create("ret");
			}
		});
	}

	/**
	 * Setter.
	 * 
	 * @param controller Controller to be set.
	 */
	public void setController(Controller controller){

		this.controller = controller;
	}

	public void drawPolyline(Polyline polyline){

		browser.executeJavaScriptAndReturnValue("window.drawPolylineByCoordinates(" + polyline.toString() + ");");
	}

	public void drawMarker(Marker marker){

		browser.executeJavaScriptAndReturnValue("window.drawMarker(" + marker.toString() + ");");
	}

	public void updateMarker(Marker marker){
		//TODO: Update marker
	}

	public void deleteMarker(Marker marker){

		browser.executeJavaScriptAndReturnValue("window.deleteMarker(" + marker.id + ");");
	}

	public void drawRoute(Route route){

		browser.executeJavaScriptAndReturnValue("window.drawRoute(" + route.toString() + ");");
	}

	public void deleteRoute(Route route){

		browser.executeJavaScriptAndReturnValue("window.deleteRoute(" + route.id + ");");
	}

	public void deleteMarkers(){

		browser.executeJavaScriptAndReturnValue("window.deleteMarkers();");
	}

	public void deleteRoutes(){

		browser.executeJavaScriptAndReturnValue("window.deleteRoutes();");
	}

	public void deleteAll(){

		browser.executeJavaScriptAndReturnValue("window.deleteAll();");
	}
}
