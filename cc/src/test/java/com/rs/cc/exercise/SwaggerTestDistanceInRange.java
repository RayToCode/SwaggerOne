package com.rs.cc.exercise;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SwaggerTestDistanceInRange {
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
	public void test_distanceInRange__Returns__False__When__Paris__PassedIn() {
		//Act
		boolean result = swagger.distanceInRange(51.513391, -0.088661, 48.856613, 2.352222, 50.00);
		//Assert
		assertFalse(result);
	}
	
	@Test
	//https://www.latlong.net/
	//London - Munich - 569 - https://www.nhc.noaa.gov/gccalc.shtml
	//London, Bank, City Centre coordinates used 
	//Munich coordinates found using - https://www.latlong.net/
	public void test_distanceBetween__Returns__False__When__Munich__PassedIn() {
		//Act
		boolean result = swagger.distanceInRange(51.513391, -0.088661, 48.135124, 11.581981, 50.00);
		//Assert
		assertFalse(result);
	}

	@Test
	//https://www.latlong.net/
	//London - Twickenham - 569 - https://www.nhc.noaa.gov/gccalc.shtml
	//London, Bank, City Centre coordinates used 
	//Twickenham coordinates found using - https://www.latlong.net/
	public void test_distanceBetween__Returns__True__When__Twickenham__PassedIn() {
		//Act
		boolean result = swagger.distanceInRange(51.513391, -0.088661, 51.445641, -0.329750, 50.00);
		//Assert
		assertTrue(result);
	}

}