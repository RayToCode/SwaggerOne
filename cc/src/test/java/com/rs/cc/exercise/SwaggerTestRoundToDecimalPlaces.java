package com.rs.cc.exercise;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SwaggerTestRoundToDecimalPlaces {

	//Arrange
	Swagger swagger;
	
	@Before
	public void setUp() throws Exception {
		//Arrange
		swagger = new Swagger();
	}

	@Test
	public void test_roundToDecimalPlaces__Returns__100_55__When__100_545__And__2__PassedIn() {
		//Act
		double result = swagger.roundToDecimalPlaces(100.545, 2);
		//Assert
		assertEquals(100.55, result, 0.000001);
	}
	
	@Test
	public void test_roundToDecimalPlaces__Returns__100_56__When__100_555__And__2__PassedIn() {
		//Act
		double result = swagger.roundToDecimalPlaces(100.555, 2);
		//Assert
		assertEquals(100.56, result, 0.000001);
	}
	
	
	@Test
	public void test_roundToDecimalPlaces__Returns__100_555__When__100_5455__And__3__PassedIn() {
		//Act
		double result = swagger.roundToDecimalPlaces(100.5455, 3);
		//Assert
		assertEquals(100.546, result, 0.000001);
	}
	
	@Test
	public void test_roundToDecimalPlaces__Returns__100_556__When__100_5555__And__3__PassedIn() {
		//Act
		double result = swagger.roundToDecimalPlaces(100.5555, 3);
		//Assert
		assertEquals(100.556, result, 0.000001);
	}

	@Test
	public void test_roundToDecimalPlaces__Returns__Minus100_54__When__Minus100_545__And__2__PassedIn() {
		//Act
		double result = swagger.roundToDecimalPlaces(-100.545, 2);
		//Assert
		assertEquals(-100.54, result, 0.000001);
	}
	
	@Test
	public void test_roundToDecimalPlaces__Returns__Minus100_55__When__Minus100_555__And__2__PassedIn() {
		//Act
		double result = swagger.roundToDecimalPlaces(-100.555, 2);
		//Assert
		assertEquals(-100.55, result, 0.000001);
	}
	
	
	@Test
	public void test_roundToDecimalPlaces__Returns__Minus100_545__When__Minus100_5455__And__3__PassedIn() {
		//Act
		double result = swagger.roundToDecimalPlaces(-100.5455, 3);
		//Assert
		assertEquals(-100.545, result, 0.000001);
	}
	
	@Test
	public void test_roundToDecimalPlaces__Returns__Minus100_555__When__Minus100_5555__And__3__PassedIn() {
		//Act
		double result = swagger.roundToDecimalPlaces(-100.5555, 3);
		//Assert
		assertEquals(-100.555, result, 0.000001);
	}
}
