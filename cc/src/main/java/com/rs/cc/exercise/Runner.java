package com.rs.cc.exercise;

import java.net.HttpURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Runner {

	public static void main(String[] args) {
		//input data
		String resource = "https://bpdts-test-app.herokuapp.com/users";
		double maxDistance = 50.00;
		double latitude = 51.513391;
		double longitude = -0.088661;
		String city = "London";
		
		//create class
		Swagger swag1 = new Swagger(latitude, longitude, maxDistance, city, resource);
		
		HttpURLConnection conn = swag1.connect("GET", resource);
		if(conn != null) {
			String jsonData = swag1.read(conn);
			
			JSONParser parser = new JSONParser();
			JSONArray jsonArray = null;
			
			try {
				jsonArray = (JSONArray) parser.parse(jsonData);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			System.out.println("Find users in " + maxDistance + " miles range of " + city + ":\n");
		
			int numberOfUsers = swag1.checkUsersInRange(jsonArray);		
			if (numberOfUsers == 0) {
				System.out.println("No users were in range!");
			} else {
				System.out.println("Total number of users in range: " + numberOfUsers);
			}
			
			conn.disconnect();
		}
		else {
			System.out.println("Error occurred.");
		}
		

	}

}

