package com.rs.cc.exercise;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SwaggerTestDistanceBetween {
	//Arrange
	Swagger swagger;
	
	@Before
	public void setUp() throws Exception {
		//Arrange
		swagger = new Swagger();
	}

	
	@Test
	//London - Paris - 212 - https://www.nhc.noaa.gov/gccalc.shtml
	//London, Bank, City Centre coordinates used 
	//Paris coordinates found using - https://www.latlong.net/
	public void test_distanceBetween__Returns__212__When__Paris__PassedIn() {
		//Act
		Double result = swagger.distanceBetween(51.513391, -0.088661, 48.856613, 2.352222);
		//Assert
		assertEquals(212.94484509334274, result, 0.000001);
	}
	
	@Test
	//https://www.latlong.net/
	//London - Munich - 569 - https://www.nhc.noaa.gov/gccalc.shtml
	//London, Bank, City Centre coordinates used 
	//Munich coordinates found using - https://www.latlong.net/
	public void test_distanceBetween__Returns__569__When__Munich__PassedIn() {
		//Act
		Double result = swagger.distanceBetween(51.513391, -0.088661, 48.135124, 11.581981);
		//Assert
		assertEquals(569.3410968718933, result, 0.000001);
	}

	@Test
	//https://www.latlong.net/
	//London - Twickenham - 569 - https://www.nhc.noaa.gov/gccalc.shtml
	//London, Bank, City Centre coordinates used 
	//Twickenham coordinates found using - https://www.latlong.net/
	public void test_distanceBetween__Returns__11__When__Twickenham__PassedIn() {
		//Act
		Double result = swagger.distanceBetween(51.513391, -0.088661, 51.445641, -0.329750);
		//Assert
		assertEquals(11.381478896497955, result, 0.000001);
	}
}
