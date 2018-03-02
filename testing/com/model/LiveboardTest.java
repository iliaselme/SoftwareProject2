package com.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LiveboardTest {
	
private Liveboard liveboard;
	
	@Before
	public void setUp() throws Exception {
		liveboard = new Liveboard();
	}
	
	@Test
	public void testSetDelay() {
		liveboard.setDelay(1);
		assertEquals(1, liveboard.getDelay());
		liveboard.setDelay(-100); //-----------------------mag dit?----------------
		assertEquals(-100, liveboard.getDelay());
		liveboard.setDelay(0); //-----------------------mag dit?----------------
		assertEquals(0, liveboard.getDelay());
	}
	
	@Test
	public void testSetPlatform() {
		liveboard.setPlatform("9 3/4");
		assertEquals("9 3/4", liveboard.getPlatform());
		liveboard.setPlatform("");
		assertEquals("", liveboard.getPlatform());
		//liveboard.setPlatform(null);
		//assertEquals("", liveboard.getPlatform());
	}
	
	@Test
	public void testSetStation() {
		liveboard.setStation("Dilbeek");
		assertEquals("Dilbeek", liveboard.getStation());
		liveboard.setStation("");
		assertEquals("", liveboard.getStation());
		//liveboard.setStation(null);
		//assertEquals("", liveboard.getStation());
	}
	
	@Test
	public void testSetVehicule() {
		liveboard.setVehicle("The Jacobite");
		assertEquals("The Jacobite", liveboard.getVehicle());
		liveboard.setVehicle("");
		assertEquals("", liveboard.getVehicle());
		//liveboard.setVehicle(null);
		//assertEquals("", liveboard.getVehicle());
	}
	
//test localDateTime DepartureTime

}
