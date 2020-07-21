package com.rs.cc.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Swagger implements SwaggerInterface {
	
	//instance variables
	private double latitudeOrigin = 0.0;
	private double longitudeOrigin = 0.0;
	private double distance = 0.0;
	private String city = "";
	private String resource = "";
	private int precision = 2;
	
	
	//constructors
	public Swagger() {
		super();
	}
	
	
	public Swagger(double latitude, double longitude, double distance) {
		super();
		this.latitudeOrigin = latitude;
		this.longitudeOrigin = longitude;
		this.distance = distance;
	}	


	public Swagger(double latitude, double longitude, double distance, String city, String resource) {
		this(latitude, longitude, distance);
		this.city = city;
		this.resource = resource;
	}
	
	
	public Swagger(double latitude, double longitude, double distance, String city, String resource, int precision) {
		this(latitude, longitude, distance, city, resource);
		this.precision = precision;
	}	
	
	//getter and setter methods
	public double getLatitudeOrigin() {
		return latitudeOrigin;
	}


	public void setLatitudeOrigin(double latitudeOrigin) {
		this.latitudeOrigin = latitudeOrigin;
	}


	public double getLongitudeOrigin() {
		return longitudeOrigin;
	}


	public void setLongitudeOrigin(double longitudeOrigin) {
		this.longitudeOrigin = longitudeOrigin;
	}


	public double getDistance() {
		return distance;
	}


	public void setDistance(double distance) {
		this.distance = distance;
	}


	public String getResource() {
		return resource;
	}


	public void setResource(String resource) {
		this.resource = resource;
	}


	public String getCity() {
		return city;
	}


	public int getPrecision() {
		return precision;
	}


	public void setPrecision(int precision) {
		this.precision = precision;
	}


	//other methods
	/**
	 * @author Raymond Snare
	 * @param method
	 * @param resource
	 * @return
	 */
	public HttpURLConnection connect(String method, String resource) {
		int responsecode = 0;
		
		try {
				URL url = new URL(resource);
	
				// Parse URL into HttpURLConnection in order to open the connection
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	
				// Set the request to GET or POST as per the requirements
				conn.setRequestMethod(method);
	
				// Use the connect method to create the connection bridge
				conn.connect();
	
				// Get the response status of the Rest API
				responsecode = conn.getResponseCode();
				if(responsecode == 200) {
					return conn;
				}
				
		} catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
				e.printStackTrace();
		}
		return null;
	}
	
	

	
	/**
	 * @author Raymond Snare
	 * 
	 * @param conn
	 * @return
	 */
	public String getUsers(HttpURLConnection conn) {
		//Use StringBuilder to collect String values
		StringBuilder jsonBuilder = new StringBuilder();
		
		String jsonStr = "";
		
		BufferedReader br = null;
		// BufferedReader will read the JSON data from the stream
		try {
			// open the stream and put it into BufferedReader
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
            	jsonBuilder.append(inputLine); 
            }
            //covert back to String
			jsonStr = jsonBuilder.toString();
			
			
		} catch  (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsonStr;
	}

	
	
	/**
	 * @author Raymond Snare
	 * 
	 * @param jsonArray
	 * @return
	 */
	public int checkUsersInRange(JSONArray jsonArray) {
		int countUsers = 0;
		
		for (int index = 0; index < jsonArray.size(); index++) {

			JSONObject jsonObj = (JSONObject) jsonArray.get(index);
			
			Object aObj = null;
			// convert if required latitude value to a Double
			aObj = jsonObj.get("latitude");
			Double latitudeD = stringToDouble(aObj);

			// convert if required longitude value to a Double
			aObj = jsonObj.get("longitude");
			Double longitudeD = stringToDouble(aObj);

			// check if location in range and print result
			if (distanceInRange(getLatitudeOrigin(), getLongitudeOrigin(), latitudeD, longitudeD, getDistance())) {
				System.out.println("ID: " + jsonObj.get("id"));
				String firstName = (String) jsonObj.get("first_name");
				String lastName = (String) jsonObj.get("last_name");
				System.out.println("User name: " + firstName + " " + lastName);
				System.out.println("Distance: " + roundToDecimalPlaces(
						distanceBetween(getLatitudeOrigin(), getLongitudeOrigin(), latitudeD, longitudeD), getPrecision()));
				System.out.println("");
				
				//increment users count
				countUsers++;
			}
		}
		
		return countUsers;
	}
	
	
	/**
	 * @author Raymond Snare
	 * 
	 * @param latitudeOrigin
	 * @param longitudeOrigin
	 * @param latitudeDestination
	 * @param longitudeDestination
	 * @param distanceMilesMax
	 * @return
	 */
	protected boolean distanceInRange(double latitudeOrigin, double longitudeOrigin, double latitudeDestination, double longitudeDestination, double distanceMilesMax) {
		//use distanceBetween to calculate istance in miles
		double distanceMiles = distanceBetween(latitudeOrigin, longitudeOrigin, latitudeDestination, longitudeDestination);

		//check if value is in range
		if (distanceMiles <= distanceMilesMax) {
			return true;
		}
		
		return false;
	}

	/**
	 * @author Raymond Snare
	 * 
	 * @param latitudeOrigin
	 * @param longitudeOrigin
	 * @param latitudeDestination
	 * @param longitudeDestination
	 * @return
	 */
	protected double distanceBetween(double latitudeOrigin, double longitudeOrigin, double latitudeDestination, double longitudeDestination) {
		//conversion factor metres to miles
		double R = 6371e3; // metres
		
		//This uses the ‘haversine’ formula to calculate the great-circle distance between two points.
		//That is, the shortest distance over the earth’s surface – giving an ‘as-the-crow-flies’ 
		//distance between the points (ignoring any hills they fly over, of course!).
		double φ1 = latitudeOrigin * Math.PI / 180; // φ, λ in radians
		double φ2 = latitudeDestination * Math.PI / 180;
		double Δφ = (latitudeDestination - latitudeOrigin) * Math.PI / 180;
		double Δλ = (longitudeDestination - longitudeOrigin) * Math.PI / 180;

		double a = Math.sin(Δφ / 2) * Math.sin(Δφ / 2) + Math.cos(φ1) * Math.cos(φ2) * Math.sin(Δλ / 2) * Math.sin(Δλ / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		//convert metres to miles
		double distanceMetres = R * c; // in metres

		double distanceMiles = distanceMetres * 0.00062137119224; // convert to miles
	
		return distanceMiles;
	}
	
	
	/**
	 * @author Raymond Snare
	 * 
	 * @param input
	 * @param places
	 * @return
	 */
	protected double roundToDecimalPlaces(double input, int places) {

		//calculate the the number of decimal places
		int multiple = (int) Math.pow(10, places);

		double result = input * multiple;
		result = (double) Math.round(result) / multiple;

		return result;
	}

	
	/**
	 * @author Raymond Snare
	 * 
	 * @param jsonObj
	 * @return
	 */
	protected Double stringToDouble(Object jsonObj) {

		Double output = 0.0;

		//check if object is either a String or a Double
		//convert to Double if required
		//always return Double value.
		if (jsonObj instanceof Double) {
			output = (Double) jsonObj;
		} else if (jsonObj instanceof String) {
			output = Double.parseDouble((String) jsonObj);
		}

		return output;
	}
}


