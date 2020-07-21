package com.rs.cc.exercise;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class SwaggerTestCheckUsersInRange {
	//Arrange
	Swagger swagger;
	JSONArray jsonUsersArray;
	JSONObject jsonUsersObj;
	
	@Before
	public void setUp() throws Exception {
		//Arrange
		swagger = new Swagger(51.513391, -0.088661, 50.00);
		jsonUsersObj = new JSONObject();
		jsonUsersArray = new JSONArray();
	}


	
	@Test
	//Setup json object with user data
	//Setup json array with user object
	//User outside of range
	public void test_checkUsersInRange__Returns__0__When__OutOfRangeUser1__PassedIn() {
		//Arrange
		jsonUsersObj.put("latitude", 34.003135);
		jsonUsersObj.put("last_name", "Shieldon");
		jsonUsersObj.put("id", 1);
		jsonUsersObj.put("ip_address", "192.57.232.111");
		jsonUsersObj.put("first_name", "Maurise");
		jsonUsersObj.put("email", "mshieldon0@squidoo.com");
		jsonUsersObj.put("longitude", -117.7228641);
		
		jsonUsersArray.add(jsonUsersObj);
		//Act
		int result = swagger.checkUsersInRange(jsonUsersArray);
		//Assert
		assertEquals(0, result);
	}
	
	@Test
	//Setup json object with user data
	//Setup json array with user object
	//User outside of range
	public void test_checkUsersInRange__Returns__0__When__OutOfRangeUser2__PassedIn() {
		//Arrange
		jsonUsersObj.put("latitude", 48.856613);
		jsonUsersObj.put("last_name", "Shieldon");
		jsonUsersObj.put("id", 1);
		jsonUsersObj.put("ip_address", "192.57.232.111");
		jsonUsersObj.put("first_name", "Maurise");
		jsonUsersObj.put("email", "mshieldon0@squidoo.com");
		jsonUsersObj.put("longitude", 2.352222);
		
		jsonUsersArray.add(jsonUsersObj);
		//Act
		int result = swagger.checkUsersInRange(jsonUsersArray);
		//Assert
		assertEquals(0, result);
	}

	
	@Test
	//Setup json object with user data
	//Setup json array with user object
	//User outside of range
	public void test_checkUsersInRange__Returns__0__When__OutOfRangeUser3__PassedIn() {
		//Arrange
		jsonUsersObj.put("latitude", 48.135124);
		jsonUsersObj.put("last_name", "Shieldon");
		jsonUsersObj.put("id", 1);
		jsonUsersObj.put("ip_address", "192.57.232.111");
		jsonUsersObj.put("first_name", "Maurise");
		jsonUsersObj.put("email", "mshieldon0@squidoo.com");
		jsonUsersObj.put("longitude", 11.581981);
		
		jsonUsersArray.add(jsonUsersObj);
		//Act
		int result = swagger.checkUsersInRange(jsonUsersArray);
		//Assert
		assertEquals(0, result);
	}

	@Test
	//Setup json object with user data
	//Setup json array with user object
	//User outside of range
	public void test_checkUsersInRange__Returns__1__When__InRangeUser1__PassedIn() {
		//Arrange
		jsonUsersObj.put("latitude", 51.445641);
		jsonUsersObj.put("last_name", "Shieldon");
		jsonUsersObj.put("id", 1);
		jsonUsersObj.put("ip_address", "192.57.232.111");
		jsonUsersObj.put("first_name", "Maurise");
		jsonUsersObj.put("email", "mshieldon0@squidoo.com");
		jsonUsersObj.put("longitude", -0.329750);
		
		jsonUsersArray.add(jsonUsersObj);

		//Act
		int result = swagger.checkUsersInRange(jsonUsersArray);
		//Assert
		assertEquals(1, result);
	}

}
