package com.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public class LoginTest {

private Login login;
	
	@Before
	public void setUp() throws Exception {
		login = new Login();
	}
	
	@Test
	public void testSetLogin_id() {
		login.setLogin_id(1);
		assertEquals(1, login.getLogin_id());
		login.setLogin_id(-100); //-----------------------mag dit?----------------
		assertEquals(-100, login.getLogin_id());
		login.setLogin_id(0); //-----------------------mag dit?----------------
		assertEquals(0, login.getLogin_id());
	}
	
	@Test
	public void testSetMedewerker_id() {
		login.setMedewerker_id(1);
		assertEquals(1, login.getMedewerker_id());
		login.setMedewerker_id(-100); //-----------------------mag dit?----------------
		assertEquals(-100, login.getMedewerker_id());
		login.setMedewerker_id(0); //-----------------------mag dit?----------------
		assertEquals(0, login.getMedewerker_id());
	}
	
	@Test
	public void testSetUsername() {
		login.setUsername("ThijsCoremans");
		assertEquals("ThijsCoremans", login.getUsername());
		login.setUsername("");
		assertEquals("", login.getUsername());
		//login.setUsername(null);
		//assertEquals("", login.getUsername());
	}
	
	@Test
	public void testSetPassword() {
		login.setPassword("Test1234");
		assertEquals("Test1234", login.getPassword());
		login.setPassword("");
		assertEquals("", login.getPassword());
		//login.setPassword(null);
		//assertEquals("", login.getPassword());
	}
	
	
	
	

}
