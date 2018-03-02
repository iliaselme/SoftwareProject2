package com.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StationCSVTest {

private StationCSV stationCSV;
	
	@Before
	public void setUp() throws Exception {
		stationCSV = new StationCSV();
	}
	
	@Test
	public void testSetUri() {
		stationCSV.setUri("Dilbeek");
		assertEquals("Dilbeek", stationCSV.getUri());
		stationCSV.setUri("");
		assertEquals("", stationCSV.getUri());
	}
	
	@Test
	public void testSetName() {
		stationCSV.setName("Dilbeek");
		assertEquals("Dilbeek", stationCSV.getName());
		stationCSV.setName("");
		assertEquals("", stationCSV.getName());
	}
	
	@Test
	public void testSetNameFR() {
		stationCSV.setName("Dilbeek");
		assertEquals("Dilbeek", stationCSV.getName());
		stationCSV.setName("");
		assertEquals("", stationCSV.getName());
	}
	
	@Test
	public void testSetNameNL() {
		stationCSV.setName("Dilbeek");
		assertEquals("Dilbeek", stationCSV.getName());
		stationCSV.setName("");
		assertEquals("", stationCSV.getName());
	}
	
	@Test
	public void testSetNameDe() {
		stationCSV.setName("Dilbeek");
		assertEquals("Dilbeek", stationCSV.getName());
		stationCSV.setName("");
		assertEquals("", stationCSV.getName());
	}
	
	@Test
	public void testSetNameENG() {
		stationCSV.setName("Dilbeek");
		assertEquals("Dilbeek", stationCSV.getName());
		stationCSV.setName("");
		assertEquals("", stationCSV.getName());
	}
	
	@Test
	public void testSetCountryCode() {
		stationCSV.setName("BE");
		assertEquals("BE", stationCSV.getName());
		stationCSV.setName("");
		assertEquals("", stationCSV.getName());
	}
	
	@Test
	public void testSetLatitude() {
		final double DELTA = 1e-15;
		stationCSV.setLatitude(1.5);
		assertEquals(1.5, stationCSV.getLatitude(), DELTA);
		stationCSV.setLatitude(-100); //-----------------------mag dit?----------------
		assertEquals(-100, stationCSV.getLatitude(), DELTA);
		stationCSV.setLatitude(0); //-----------------------mag dit?----------------
		assertEquals(0, stationCSV.getLatitude(), DELTA);
	}
	
	@Test
	public void testSetLongitude() {
		final double DELTA = 1e-15;
		stationCSV.setLongitude(1.5);
		assertEquals(1.5, stationCSV.getLongitude(), DELTA);
		stationCSV.setLongitude(-100); //-----------------------mag dit?----------------
		assertEquals(-100, stationCSV.getLongitude(), DELTA);
		stationCSV.setLongitude(0); //-----------------------mag dit?----------------
		assertEquals(0, stationCSV.getLongitude(), DELTA);
	}
	
	

}
