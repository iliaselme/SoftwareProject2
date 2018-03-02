package com.persistentie;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import com.database.KortingDAO;
import com.model.Korting;
import com.model.Ticket;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;

public class KortingCSV {
	
	private static org.apache.logging.log4j.Logger logger = LogManager.getLogger();
	private static String[] kolommen = new String[] {"korting_id", "naam", "beschrijving", "percentage", "actief"};
	
	//overschrijft inhoud met nieuwe kortingen
	public static void updateAllKortingen(){
		LinkedList<Korting> lijst = (LinkedList<Korting>) KortingDAO.getActieveKortingen();
		for (Korting k: lijst){
			   System.out.println(k.toString());
		   }
		
		try (CSVWriter csvWriter = new CSVWriter(new FileWriter("./persistentie/Kortingen.csv",false))){
			BeanToCsv<Korting> btc = new BeanToCsv<Korting>();
			ColumnPositionMappingStrategy<Korting> mapStrategy = new ColumnPositionMappingStrategy<Korting>();
			mapStrategy.setType(Korting.class);
			mapStrategy.setColumnMapping(kolommen);
			btc.write(mapStrategy,csvWriter,lijst);
		} catch (Exception e) {
			logger.error("updateAllKortingen", e);
		}

	}
	
	public static void addKortingToCSV(Korting korting){
		List<Korting> lijst = KortingCSV.getKortingenFromCSV();
		try (CSVWriter csvWriter = new CSVWriter(new FileWriter("./persistentie/Kortingen.csv"))){
			BeanToCsv<Korting> btc = new BeanToCsv<Korting>();
			ColumnPositionMappingStrategy<Korting> mapStrategy = new ColumnPositionMappingStrategy<Korting>();
			mapStrategy.setType(Korting.class);
			mapStrategy.setColumnMapping(kolommen);
			lijst.add(korting);
			btc.write(mapStrategy,csvWriter,lijst);
		} catch (Exception e) {
			logger.error("addKortingToCSV", e);
		}
	}
	
	public static List<Korting> getKortingenFromCSV(){
		List<Korting> lijst = new ArrayList<Korting>();
		try (CSVReader csvReader = new CSVReader(new FileReader("./persistentie/Kortingen.csv"), ',', '"', 1)){
			String[] result = null;
			while((result = csvReader.readNext())!=null){
				Korting help = new Korting(
					Integer.parseInt(result[0]), 	// korting_id
					result[1],						// naam
					result[2],						// beschrijving
					Integer.parseInt(result[3]),	// percentage
					Boolean.valueOf(result[4]));	// actief
				lijst.add(help);
			}
		} catch (Exception e) {
			logger.error("getKortingenFromCSV", e);
		}
		return lijst;
	}
}
