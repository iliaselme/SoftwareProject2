package com.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

public class TicketTest {

private Ticket ticket;
	
	@Before
	public void setUp() throws Exception {
		ticket = new Ticket();
	}

	//nog problemen door parser
	
	@Test
	public void testSetDatumAankoop() {
		
		String input = "16/08/2016 08:30:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "dd/MM/yyyy hh:mm:ss" );
		LocalDate localDate = LocalDate.parse ( input , formatter );	
		ticket.setDatumTerug(localDate);
	    
	    assertEquals("16/08/2016 08:30:00", ticket.getDatumTerug());
	}
	
	
	@Test
	public void testSetDatumHeen() {
		
		String input = "16/08/2016";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "dd/MM/yyyy" );
		LocalDate localDate = LocalDate.parse ( input , formatter );	
		ticket.setDatumHeen(localDate);
	    
	    assertEquals("16/08/2016", ticket.getDatumHeen());	
	}

	@Test
	public void testSetDatumTerug() {
		
		String input = "16/08/2016";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "dd/MM/yyyy" );
		LocalDate localDate = LocalDate.parse ( input , formatter );	
		ticket.setDatumTerug(localDate);
	    
	    assertEquals("16/08/2016", ticket.getDatumTerug());
	}
	
	
	
}

