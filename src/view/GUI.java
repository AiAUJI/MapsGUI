package view;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserFunction;
import com.teamdev.jxbrowser.chromium.JSValue;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import controller.Controller;

import javax.swing.*;

import model.Marker;
import model.Polyline;
import model.Route;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GUI {

	Controller controller;
	Browser browser;

	/**
	 * Default constructor.
	 */
	public GUI(){

		this.controller = null;
		this.browser = new Browser();

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
		main.setLayout(new BorderLayout());

		//Icon and title
		ImageIcon img = new ImageIcon(GUI.class.getClassLoader().getResource("icon/ujilogo.png"));
		main.setTitle("Universitat Jaume I");
		main.setIconImage(img.getImage());

		//Add the browser
		main.add(browserView, BorderLayout.CENTER);

		//The button panel
		JPanel controls = new JPanel();
		controls.setLayout(new BorderLayout());

		main.add(controls, BorderLayout.EAST);

		//Add the label and the list
		JPanel routesPanel = new JPanel(new BorderLayout());

		routesPanel.add(new JLabel("Load routes", JLabel.CENTER), BorderLayout.NORTH);

		//Get all .txt files from routes folder
		String url = GUI.class.getClassLoader().getResource("routes").toString().split(":")[1];

		File[] routes = new File(url).listFiles();

		String listData[] = new String[routes.length];

		for(int i=0; i<routes.length; i++){

			listData[i] = routes[i].getName();
		}

		// Create a new listbox control
		JList<String> listbox = new JList<String>(listData);
		routesPanel.add(listbox, BorderLayout.CENTER);

		controls.add(routesPanel, BorderLayout.NORTH);

		//A new panel for the buttons
		JPanel buttons = new JPanel(new BorderLayout());

		JButton draw = new JButton("Draw");
		JButton reset = new JButton("Reset");
		buttons.add(draw, BorderLayout.WEST);
		buttons.add(reset, BorderLayout.EAST);

		routesPanel.add(buttons, BorderLayout.SOUTH);

		url = GUI.class.getClassLoader().getResource("html/index.html").toString();
		browser.loadURL(url);

		draw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO: Improve
				BufferedReader br;

				//Para cada uno de las rutas seleccionadas
				for(int i: listbox.getSelectedIndices()){

					//Abrimos el fichero y leemos el contenido
					String points = "";

					try {
						br = new BufferedReader(new FileReader(routes[i].getAbsolutePath()));
						points = br.readLine();
						br.close();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					browser.executeJavaScriptAndReturnValue("window.drawRoute(\"" + i + "\", " + points + ");");
				}
			}
		});

		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				deleteAll();
			}
		});
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

				System.out.println(args[0].getString() + " has been clicked");

				return JSValue.create("ret");
			}
		});
		
		//Triggered when the delete button in a marker is clicked
		browser.registerFunction("deleteButtonClickedCallback", new BrowserFunction() {
			public JSValue invoke(JSValue... args) {

				System.out.println("Delete " + args[0].getString());

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
