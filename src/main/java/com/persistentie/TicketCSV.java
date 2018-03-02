package com.persistentie;

import java.beans.PropertyDescriptor;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import com.model.Ticket;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

public class TicketCSV {
	private static org.apache.logging.log4j.Logger logger = LogManager.getLogger();
	
	private static String[] kolommen = new String[] {"vertrekStation", "eindStation", "ticket_id", "klasse", "type", "prijs", "datumAankoop", "datumHeen", "datumTerug" , "medewerker_id" , "korting_id", "medewerker_id", "formule_id"};
	
	//voegt ticket toe aan een nieuwe lijn in het bestand
	public static void addTicket(Ticket ticket){
		List<Ticket> ticketLijst = TicketCSV.getTickets();
		try (CSVWriter csvWriter = new CSVWriter(new FileWriter("./persistentie/persistentTicket.csv"))){
			BeanToCsv<Ticket> btc = new BeanToCsv<Ticket>();
			ColumnPositionMappingStrategy<Ticket> mapStrategy = new ColumnPositionMappingStrategy<Ticket>();
			mapStrategy.setType(Ticket.class);
			mapStrategy.setColumnMapping(kolommen);
			ticketLijst.add(ticket);
			btc.write(mapStrategy,csvWriter,ticketLijst);
		} catch (Exception e) {
			logger.error("addTicket", e);
		}
	}
	
	public static void removeFirstTicket(){
		List<Ticket> ticketLijst = TicketCSV.getTickets();
		try (CSVWriter csvWriter = new CSVWriter(new FileWriter("./persistentie/persistentTicket.csv"))){
			BeanToCsv<Ticket> btc = new BeanToCsv<Ticket>();
			ColumnPositionMappingStrategy<Ticket> mapStrategy = new ColumnPositionMappingStrategy<Ticket>();
			mapStrategy.setType(Ticket.class);
			mapStrategy.setColumnMapping(kolommen);
			ticketLijst.remove(0);
			btc.write(mapStrategy,csvWriter,ticketLijst);
		} catch (Exception e) {
			logger.error("addTicket", e);
		}
	}
	
	public static List<Ticket> getTickets(){
		List<Ticket> ticketLijst = new ArrayList();
		try (CSVReader csvReader = new CSVReader(new FileReader("./persistentie/persistentTicket.csv"), ',', '"', 1)){
			String[] result = null;
			while((result = csvReader.readNext())!=null){
				Ticket help = new Ticket(
					result[0].toString().trim(), //vertrekStation
					result[1].toString().trim(), //eindStation
					Integer.parseInt(result[2].toString().trim()), //ticket_id
					Integer.parseInt(result[3].toString().trim()), //klasse
					Integer.parseInt(result[4].toString().trim()), //type
					Double.parseDouble(result[5].toString().trim()), //prijs
					LocalDateTime.parse(result[6].toString().trim(),DateTimeFormatter.ISO_LOCAL_DATE_TIME), //datumAankoop
					LocalDate.parse(result[7].toString().trim(),DateTimeFormatter.ISO_LOCAL_DATE), //datumHeen
					LocalDate.parse(result[8].toString().trim(),DateTimeFormatter.ISO_LOCAL_DATE), //datumTerug
					Integer.parseInt(result[9].toString().trim()), //medewerker_id
					Integer.parseInt(result[10].toString().trim()), //korting_id
					Integer.parseInt(result[11].toString().trim())//formule_id
				);
				ticketLijst.add(help);
			}
		} catch (Exception e) {
			logger.error("getTickets", e);
		}
		return ticketLijst;
	}
}
	

