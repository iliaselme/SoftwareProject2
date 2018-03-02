package com.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StationTest {

private Station station;
	
	@Before
	public void setUp() throws Exception {
		station = new Station();
	}
	
	@Test
	public void testSetNaam() {
		station.setNaam("Dilbeek");
		assertEquals("Dilbeek", station.getNaam());
		station.setNaam("");
		assertEquals("", station.getNaam());
	}
	
	@Test
	public void testSetLatitude() {
		final double DELTA = 1e-15;
		station.setLatitude(1.5);
		assertEquals(1.5, station.getLatitude(), DELTA);
		station.setLatitude(-100); //-----------------------mag dit?----------------
		assertEquals(-100, station.getLatitude(), DELTA);
		station.setLatitude(0); //-----------------------mag dit?----------------
		assertEquals(0, station.getLatitude(), DELTA);
	}
	
	@Test
	public void testSetLongitude() {
		final double DELTA = 1e-15;
		station.setLongitude(1.5);
		assertEquals(1.5, station.getLongitude(), DELTA);
		station.setLongitude(-100); //-----------------------mag dit?----------------
		assertEquals(-100, station.getLongitude(), DELTA);
		station.setLongitude(0); //-----------------------mag dit?----------------
		assertEquals(0, station.getLongitude(), DELTA);
	}

}
