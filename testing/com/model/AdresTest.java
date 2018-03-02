package com.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AdresTest {

	private Adres adres;
	
	@Before
	public void setUp() throws Exception {
		adres = new Adres();
	}
	
	
	@Test
	public void testSetAdres_id() {
		adres.setAdres_id(1);
		assertEquals(1, adres.getAdres_id());
		adres.setAdres_id(-100); //-----------------------mag dit?----------------
		assertEquals(-100, adres.getAdres_id());
		adres.setAdres_id(0); //-----------------------mag dit?----------------
		assertEquals(0, adres.getAdres_id());
	}
	
	@Test
	public void testSetHuisnummer() {
		adres.setHuisnr(1);
		assertEquals(1, adres.getHuisnr());
		adres.setHuisnr(-100); //-----------------------mag dit?----------------
		assertEquals(-100, adres.getHuisnr());
		adres.setHuisnr(0); //-----------------------mag dit?----------------
		assertEquals(0, adres.getHuisnr());
	}
	
	@Test
	public void testSetPostcode() {
		adres.setPostcode(1);
		assertEquals(1, adres.getPostcode());
		adres.setPostcode(-100); //-----------------------mag dit?----------------
		assertEquals(-100, adres.getPostcode());
		adres.setPostcode(0); //-----------------------mag dit?----------------
		assertEquals(0, adres.getPostcode());
	}
	
	
	@Test
	public void testSetPlaatsnaam() {
		adres.setPlaatsnaam("Dilbeek");
		assertEquals("Dilbeek", adres.getPlaatsnaam());
		adres.setPlaatsnaam("");
		//assertEquals("Dilbeek", adres.getPlaatsnaam());
		assertEquals("", adres.getPlaatsnaam());
		adres.setPlaatsnaam(null);
		//assertEquals("", adres.getPlaatsnaam());
	}
	
	@Test
	public void testSetStraat() {
		adres.setStraat("Nijverheidskaai");
		assertEquals("Nijverheidskaai", adres.getStraat());
		adres.setStraat("");
		//assertEquals("Nijverheidskaai", adres.getStraat());
		assertEquals("", adres.getStraat());
		adres.setStraat(null);
		//assertEquals("", adres.getStraat());
	}
	
	@Test
	public void testSetBrievenbus() {
		adres.setBrievenbus("107A");
		assertEquals("107A", adres.getBrievenbus());
		adres.setBrievenbus("");
		//assertEquals("107A", adres.getBrievenbus());
		assertEquals("", adres.getBrievenbus());
		adres.setBrievenbus(null);
		//assertEquals("", adres.getBrievenbus());
	}
	
	
	
	
	

}
