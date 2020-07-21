package com.rs.cc.exercise;

import java.net.HttpURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Runner {

	public static void main(String[] args) {
		//input data
		String userId = "1";
		String cityToFind = "Kax";
		
		double maxDistance = 50.00;
		double latitude = 51.513391;
		double longitude = -0.088661;
		String city = "London";
		
		//Swagger API paths
		String resourceUsers 		= "https://bpdts-test-app.herokuapp.com/users";
		String resourceUser 		= "https://bpdts-test-app.herokuapp.com/user/"+userId;
		String resourceInstructions = "https://bpdts-test-app.herokuapp.com/instructions";
		String resourceCity = "https://bpdts-test-app.herokuapp.com/city/"+cityToFind+"/users";
		
		
		//Get all users within range of city
		Swagger swag1 = new Swagger(latitude, longitude, maxDistance, city, resourceUsers);
		
		HttpURLConnection conn = swag1.connect("GET", resourceUsers);
		if(conn != null) {
			String jsonData = swag1.getUsers(conn);
			
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
		
		
		//Get a user by id
		System.out.println("\nGet user by id: " + userId);
		Swagger swag2 = new Swagger();

		HttpURLConnection conn2 = swag2.connect("GET", resourceUser);
		if(conn2 != null) {
			String jsonData = swag2.getUsers(conn2);
			System.out.println(jsonData);
		}
		
		
		//Get instructions
		System.out.println("\nGet instructions");
		Swagger swag3 = new Swagger();
		
		HttpURLConnection conn3 = swag3.connect("GET", resourceInstructions);
		if(conn3 != null) {
			String jsonData = swag3.getUsers(conn3);
			System.out.println(jsonData);
		}
		
		//Get all users in a certain city
		System.out.println("\nGet all users in city: " + cityToFind);
		Swagger swag4 = new Swagger();
		
		HttpURLConnection conn4 = swag4.connect("GET", resourceCity);
		if(conn4 != null) {
			String jsonData = swag4.getUsers(conn4);
			System.out.println(jsonData);
		}		
		

	}

}

