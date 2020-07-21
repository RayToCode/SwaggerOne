package com.rs.cc.exercise;

import static org.junit.Assert.*;

import java.net.ConnectException;
import java.net.HttpURLConnection;

import org.junit.Before;
import org.junit.Test;

public class SwaggerTestConnect {
	//Arrange
	Swagger swagger;
	
	@Before
	public void setUp() throws Exception {
		//Arrange
		swagger = new Swagger();
	}


	@Test
	//incorrect URL passed in.
	//correct URL is https://bbc.co.uk
	public void test_connect__Returns__XYZ__When__BBC__PassedIn() throws ConnectException {
		//Act
		HttpURLConnection result = swagger.connect("GET", "http://bbc.co.uk");
		//Assert
		assertEquals(result, null);
	}
	
	
	@Test
	//incorrect URL passed in.
	//correct URL is http://localhost
	public void test_connect__Returns__NULL__When__Localhost__PassedIn() throws ConnectException {
		//Act
		HttpURLConnection result = swagger.connect("GET", "https://localhost");
		//Assert
		assertEquals(result, null);
	}

}
