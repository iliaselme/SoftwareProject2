package com.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.boon.primitive.Int;
import org.junit.Before;
import org.junit.Test;

public class TrainInfoTest {

private TrainInfo trainInfo;
	
	@Before
	public void setUp() throws Exception {
		trainInfo = new TrainInfo();
	}
	
	@Test
	public void testSetStopID() {
		trainInfo.setStopID(1);
		assertEquals(Double.doubleToLongBits(1), Double.doubleToLongBits(trainInfo.getStopID()));
		trainInfo.setStopID(-100); //-----------------------mag dit?----------------
		assertEquals(Double.doubleToLongBits(-100), Double.doubleToLongBits(trainInfo.getStopID()));
		trainInfo.setStopID(0); 
		assertEquals(Double.doubleToLongBits(0), Double.doubleToLongBits(trainInfo.getStopID()));
	}

	
	@Test
	public void testSetStation() {
		trainInfo.setStation("Dilbeek");
		assertEquals("Dilbeek", trainInfo.getStation());
		trainInfo.setStation("");
		//assertEquals("Dilbeek", trainInfo.getStation());
		assertEquals("", trainInfo.getStation());
		trainInfo.setStation(null);
		//assertEquals("", trainInfo.getStation());
	}
	
	@Test
	public void testSetPlatform() {
		trainInfo.setPlatform("Dilbeek");
		assertEquals("Dilbeek", trainInfo.getPlatform());
		trainInfo.setPlatform("");
		//assertEquals("Dilbeek", trainInfo.getPlatform());
		assertEquals("", trainInfo.getPlatform());
		trainInfo.setPlatform(null);
		//assertEquals("", trainInfo.getPlatform());
	}
	
	//nog problemen door parser
		@Test
		public void testSetTime() {
			
			String input = "16/08/2016 08:30:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "dd/MM/yyyy hh:mm:ss" );
			LocalDateTime localDate = LocalDateTime.parse ( input , formatter );	
			trainInfo.setTime(localDate);
		    
		    assertEquals("16/08/2016 08:30:00", trainInfo.getTime());
		}
}
