package com.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KlantTest {

private Klant klant;
	
	@Before
	public void setUp() throws Exception {
		klant = new Klant();
	}

	@Test
	public void testSetKlantid() {
		klant.setKlantid(1);
		assertEquals(1, klant.getKlantid());
		klant.setKlantid(-100); //-----------------------mag dit?----------------
		assertEquals(-100, klant.getKlantid());
		klant.setKlantid(0); //-----------------------mag dit?----------------
		assertEquals(0, klant.getKlantid());
	}
	
	@Test
	public void testSetAdresid() {
		klant.setAdresid(1);
		assertEquals(1, klant.getAdresid());
		klant.setAdresid(-100); //-----------------------mag dit?----------------
		assertEquals(-100, klant.getAdresid());
		klant.setAdresid(0); //-----------------------mag dit?----------------
		assertEquals(0, klant.getAdresid());
	}
	
	@Test
	public void testGsmnummer() {
		klant.setGsmnummer("+321321321321");
		assertEquals("+321321321321", klant.getGsmnummer());
		klant.setGsmnummer("");
		assertEquals("", klant.getGsmnummer());
		//klant.setGsmnummer(null);
		//assertEquals("", klant.getGsmnummer());
	}
	
	@Test
	public void testCommentaar() {
		klant.setCommentaar("TEST123");
		assertEquals("TEST123", klant.getCommentaar());
		klant.setCommentaar("");
		assertEquals("", klant.getCommentaar());
		//klant.setCommentaar(null);
		//assertEquals("", klant.getCommentaar());
	}
	
	@Test
	public void testNaam() {
		klant.setNaam("Mertens");
		assertEquals("Mertens", klant.getNaam());
		klant.setNaam("");
		assertEquals("", klant.getNaam());
		//klant.setNaam(null);
		//assertEquals("", klant.getNaam());
	}
	
	@Test
	public void testVoornaam() {
		klant.setVoornaam("Pieter");
		assertEquals("Pieter", klant.getVoornaam());
		klant.setVoornaam("");
		assertEquals("", klant.getVoornaam());
		//klant.setVoornaam(null);
		//assertEquals("", klant.getVoornaam());
	}
	
	@Test
	public void testActief() {
		klant.setActief(true);
		assertEquals(true, klant.isActief()); //isActief ipv getActief?
		klant.setActief(false);
		assertEquals(false, klant.isActief());		
	}
	
	@Test
	public void testGeboortedatum() {
		
		
	}
	
	
	
}
