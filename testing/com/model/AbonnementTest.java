package com.model;

import static org.junit.Assert.*;
import java.time.format.DateTimeFormatter;

import java.time.LocalDate;


import org.junit.Before;
import org.junit.Test;



public class AbonnementTest {

	private Abonnement abonnement;
	//private Route route;
	
	@Before
	public void setUp() throws Exception {
		abonnement = new Abonnement();
	}
	
	
//	@Test
//	public void testAbonnement() {
//			
//		route = new Route();
//		
//		assertTrue(abonnement.getAbonnement_id()== 1 );
//		assertTrue(abonnement.getKlant_id()== 1);
//		assertTrue(abonnement.getMedewerker_id()== 1);
//		assertTrue(abonnement.getKlasse()== 1);
//		assertTrue(abonnement.getActief()== 1);
//		assertTrue(abonnement.getPrijs()== 1);
//		assertTrue(abonnement.getRoute()== route);
//		assertTrue(abonnement.getBeginStation()== "");
//		assertTrue(abonnement.getEindStation()== "");
//		assertTrue(abonnement.getStation()== "");
//		
//		assertEquals(abonnement.getBeginDatum(), "2016-05-15");
//		assertEquals(abonnement.getEindDatum(), "2016-05-16");
//	}
	
	
	@Test
	public void testSetAbonnement_id() {
		abonnement.setAbonnement_id(5);
		assertEquals(5, abonnement.getAbonnement_id());
		abonnement.setAbonnement_id(-100); //-----------------------mag dit?----------------
		assertEquals(-100, abonnement.getAbonnement_id());
		abonnement.setAbonnement_id(0); //-----------------------mag dit?----------------
		assertEquals(0, abonnement.getAbonnement_id());
	}
	
	@Test
	public void testSetKlant_id() {
		abonnement.setKlant_id(5);
		assertEquals(5, abonnement.getKlant_id());
		abonnement.setKlant_id(-100); //-----------------------mag dit?----------------
		assertEquals(-100, abonnement.getKlant_id());
		abonnement.setKlant_id(0); //-----------------------mag dit?----------------
		assertEquals(0, abonnement.getKlant_id());
	}
	
	@Test
	public void testSetMedewerker_id() {
		abonnement.setMedewerker_id(5);
		assertEquals(5, abonnement.getMedewerker_id());
		abonnement.setMedewerker_id(-100); //-----------------------mag dit?----------------
		assertEquals(-100, abonnement.getMedewerker_id());
		abonnement.setMedewerker_id(0); //-----------------------mag dit?----------------
		assertEquals(0, abonnement.getMedewerker_id());
	}
	
	@Test
	public void testSetKlasse() {
		abonnement.setKlasse(1);
		assertEquals(1, abonnement.getKlasse());
		abonnement.setKlasse(-100); //-----------------------mag dit?----------------
		assertEquals(-100, abonnement.getKlasse());
		abonnement.setKlasse(0); //-----------------------mag dit?----------------
		assertEquals(0, abonnement.getKlasse());
	}
	
	@Test
	public void testSetActief() {
		abonnement.setActief(0);
		assertEquals(0, abonnement.getActief());
		abonnement.setActief(1);
		assertEquals(1, abonnement.getActief());
//		------------------werkt niet dus perfect!--------------------
//		abonnement.setActief(100); 
//		assertEquals(-100, abonnement.getActief());
//		abonnement.setActief(-100); 
//		assertEquals(0, abonnement.getActief());
	}
	
	@Test
	public void testSetPrijs() {
		abonnement.setPrijs(1);
		assertEquals(Double.doubleToLongBits(1), Double.doubleToLongBits(abonnement.getPrijs()));
		abonnement.setPrijs(-100); //-----------------------mag dit?----------------
		assertEquals(Double.doubleToLongBits(-100), Double.doubleToLongBits(abonnement.getPrijs()));
		abonnement.setPrijs(0); 
		assertEquals(Double.doubleToLongBits(0), Double.doubleToLongBits(abonnement.getPrijs()));
	}

	
//testSetRoute()
	
	@Test
	public void testSetBeginStation() {
		abonnement.setBeginStation("Dilbeek");
		assertEquals("Dilbeek", abonnement.getBeginStation());
		abonnement.setBeginStation("");
		//assertEquals("Dilbeek", abonnement.getBeginStation());
		assertEquals("", abonnement.getBeginStation());
		abonnement.setBeginStation(null);
		//assertEquals("", abonnement.getBeginStation());
	}
	
	@Test
	public void testSetEindStation() {
		abonnement.setEindStation("Dilbeek");
		assertEquals("Dilbeek", abonnement.getEindStation());
		abonnement.setEindStation("");
		//assertEquals("Dilbeek", abonnement.getEindStation());
		assertEquals("", abonnement.getEindStation());
		//abonnement.setEindStation(null);
		//assertEquals("", abonnement.getEindStation());
	}
	
	@Test
	public void testSetSation() {
		abonnement.setStation("Dilbeek");
		assertEquals("Dilbeek", abonnement.getStation());
		abonnement.setStation("");
		assertEquals("", abonnement.getStation());
		abonnement.setStation(null);
		assertEquals(null, abonnement.getStation());
	}
	
	//nog problemen door parser
	@Test
	public void testSetBeginDatum() {
		
		String input = "16/08/2016";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "dd/MM/yyyy" );
		LocalDate localDate = LocalDate.parse ( input , formatter );	
	    abonnement.setBeginDatum(localDate);
	    
	    assertEquals("16/08/2016", abonnement.getBeginDatum());	
	}
	
	@Test
	public void testSetEindDatum() {
		
		String input = "16/08/2016";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "dd/MM/yyyy" );
		LocalDate localDate = LocalDate.parse ( input , formatter );	
	    abonnement.setEindDatum(localDate);
	    
	    assertEquals("16/08/2016", abonnement.getEindDatum());
	}
	
	
	
	
	
	
}
