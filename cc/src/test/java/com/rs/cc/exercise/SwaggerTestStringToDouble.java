package com.rs.cc.exercise;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SwaggerTestStringToDouble {
	//Arrange
	Swagger swagger;
	
	@Before
	public void setUp() throws Exception {
		//Arrange
		swagger = new Swagger();
	}


	@Test
	public void test_stringToDouble__Returns__100__When__String100__PassedIn() {
		//Act
		double result = swagger.stringToDouble("100");
		//Assert
		assertEquals(100, result, 0.000001);
	}
	
	@Test
	public void test_stringToDouble__Returns__100_0__When__Double100_0__PassedIn() {
		//Act
		Double result = swagger.stringToDouble(100.0);
		//Assert
		assertEquals(100, result, 0.000001);
	}
	
	@Test
	public void test_stringToDouble__Returns__Minus100__When__StringMinus100__PassedIn() {
		//Act
		double result = swagger.stringToDouble("-100");
		//Assert
		assertEquals(-100, result, 0.000001);
	}
	
	@Test
	public void test_stringToDouble__Returns__Minus100_0__When__DoubleMinus100_0__PassedIn() {
		//Act
		Double result = swagger.stringToDouble(-100.0);
		//Assert
		assertEquals(-100, result, 0.000001);
	}	

}
