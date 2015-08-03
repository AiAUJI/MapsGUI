var directionsDisplay;
var directionsService = new google.maps.DirectionsService();
var map;

//Dictionary to hold markers so we can erase them later x["Key"] = "Value";
var markersDictionary = new Object();

//Dictionary to hold the routes so we can erase them later
var routesDictionary = new Object();

function initialize() {

	directionsDisplay = new google.maps.DirectionsRenderer();
	directionsDisplay.setOptions( { suppressMarkers: true } );

	var castellon = new google.maps.LatLng(39.949175, -0.085829);

	var mapOptions = {

			zoom:10,
			center: castellon
	}

	map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);

	directionsDisplay.setMap(map);
}

//Draws a marker in the map, when clicked it displays information.

//id: unique id of the marker.
//coordinates: An array with the Latitude and the Longitude.
//name: Name of the marker (Will be displayed).
//information: (Optional) Information to display when the marker is clicked.
//icon: (Optional) URL to the icon to be displayed.
function drawMarker(id, coordinates, name, information, icon){

	icon = icon || 'http://www.google.com/intl/en_us/mapfiles/ms/micons/blue-dot.png';
	information = information || "";

	//Create the marker in the given position
	var marker = new google.maps.Marker({
		position: new google.maps.LatLng(coordinates[0], coordinates[1]),
		map: map,
		title: name,
		icon: icon
	});

	//Information to show when the marker is clicked
	var infowindow = new google.maps.InfoWindow({
		content: '<h1 id="firstHeading" class="firstHeading">' + name + '</h1></br><p>' + information + '</p></br> <button type="button" id="delete">Delete</button>'
	});
	
	//Add the listener to the button in the infoWindow
	google.maps.event.addListener(infowindow, 'domready', function() {
	    document.getElementById("delete").addEventListener("click", function(e) {
	    	
	    	deleteButtonClickedCallback(id);
	    	deleteMarker(id);
	    });
	});

	//Add the listener to the marker
	google.maps.event.addListener(marker, 'click', function() {
		infowindow.open(map, marker);
		markerClickedCallback(id);
	});

	//Add it to the dictionary
	markersDictionary[id] = marker;
}

//Function to draw a segment between two points. Path is calculated
//using Google Maps API, so use with care.
//
//start: An array with the Latitude and the Longitude.
//end: An array with the Latitude and the Longitude.
function drawSegment(id, startCoord, endCoord){

	var start = new google.maps.LatLng(startCoord[0], startCoord[1]);
	var end = new google.maps.LatLng(endCoord[0], endCoord[1]);

	//Payload to be sent to the Maps API
	var request = {
			origin: start,
			destination: end,
			travelMode: google.maps.TravelMode.DRIVING
	}	

	//Returned path
	directionsService.route(request, function(result, status){

		if (status == google.maps.DirectionsStatus.OK){
			drawPolyline(id, result.routes[0].legs);
		}
	});
}

//Function to draw a complete route.
//
//id: Unique id of the route.
//route: A list of coordinates.
function drawRoute(id, route){

	for(var i=0; i<route.length-1; i++){

		drawSegment(id, route[i], route[i+1]);
	}
}

//Function that given some coordinates, draws a Polyline
//
//id: Unique id of the polyline, it will be treated as a route.
//coordinates: Edges of the polyline.
function drawPolylineByCoordinates(id, coordinates){
	
	var polylineCoordinates = [];
	
	for(i=0; i<coordinates.length; i++){
		
		polylineCoordinates.push(new google.maps.LatLng(coordinates[i][0], coordinates[i][1]));
	}

	var polyline = new google.maps.Polyline({
		path: polylineCoordinates,
		geodesic: true,
		strokeColor: '#FF0000',
		strokeOpacity: 1.0,
		strokeWeight: 2
	});
	
	//Draw the polyline
	polyline.setMap(map);
	
	//Add the line to the dictionary
	if(routesDictionary[id] === undefined){
	
		routesDictionary[id] = [];
	}
		
	routesDictionary[id].push(polyline);
}

//Given the steps of a Segment, it draws a polyline.
function drawPolyline(id, legs){

	var line = new google.maps.Polyline({

		path: [],
		strokeColor: '#FF0000',
		strokeOpacity: 1.0,
		strokeWeight: 2
	});

	for (i=0;i<legs.length;i++) {

		var steps = legs[i].steps;

		for (j=0;j<steps.length;j++) {

			var nextSegment = steps[j].path;

			for (k=0;k<nextSegment.length;k++) {
				line.getPath().push(nextSegment[k]);
			}
		}
	}

	//Draw the polyline
	line.setMap(map);
	
	//Add the route to the dictionary
	if(routesDictionary[id] === undefined){
	
		routesDictionary[id] = [];
	}
		
	routesDictionary[id].push(line);
}


//Function to erase all the markers
function deleteMarkers(){

	var keys = Object.keys(markersDictionary);

	for(var i=0; i<keys.length; i++){

		markersDictionary[keys[i]].setMap(null);
	}

	markersDictionary = new Object();	
}

//Function to delete all the routes
function deleteRoutes(){

	var keys = Object.keys(routesDictionary);

	//For each route in the dictionary
	for(var i=0; i<keys.length; i++){
		
		var route = routesDictionary[keys[i]];
		
		//For each polyline in the route
		for(var j=0; j<route.length; j++){

			route[j].setMap(null);
		}
	}

	routesDictionary = new Object();
}

//Function to delete a marker given the id
function deleteMarker(id){
	
	markersDictionary[id].setMap(null);
	markersDictionary[id] = undefined;
}

//Function to delete a route given the id
function deleteRoute(id){
	
	var route = routesDictionary[id];
	
	//For each polyline in the route
	for(var j=0; j<route.length; j++){

		route[j].setMap(null);
	}
	
	routesDictionary[id] = undefined;
}

//Function to remove all the drawn stuff
function deleteAll(){

	deleteMarkers();
	deleteRoutes();
}

google.maps.event.addDomListener(window, 'load', initialize);

//Test function
function test(){

	//Itinerary
//	var cv10itinerary = [[39.833798, -0.201700], [39.860272, -0.168719], [39.926379, -0.190941], [39.949556, -0.179121], [39.990981, -0.121271], [40.007006, -0.114036], [40.014712, -0.092352], [40.032196, -0.082224]];
//	drawRoute(0, cv10itinerary);
//
//	var e15itinerary = [[39.827536, -0.140245], [39.897382, -0.130884], [39.983104, -0.097485], [40.024962, -0.032670], [40.041682, 0.023091]];
//	drawRoute(1, e15itinerary);
//
//	var n340itinerary = [[39.859056, -0.168703], [39.871630, -0.143372], [39.903338, -0.117448], [39.923850, -0.105405], [39.951146, -0.085252], [39.994098, -0.089581], [40.002671, -0.075800], [40.037544, 0.030727]];
//	drawRoute(2, n340itinerary);
//
//	var cs22itinerary = [[39.969558, -0.068225], [39.967494, -0.060066], [39.956184, -0.026676], [39.967977, -0.016848], [39.979819, 0.013011]];
//	drawRoute(3, cs22itinerary);
//
//	var cv18itinerary = [[39.869173, -0.144927], [39.898306, -0.083645], [39.944003, -0.054709], [39.964604, -0.054440]];
//	drawRoute(4, cv18itinerary);
	
	//Marker
	drawMarker("0", [39.833798, -0.201700], "Test1", "INFO");
	drawMarker("1", [39.860272, -0.168719], "Test2", "INFO");
	MyFunction('Hello JxBrowser!', 1, 2, 3, true);

}