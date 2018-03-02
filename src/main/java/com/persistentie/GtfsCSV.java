package com.persistentie;

import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import com.model.Routes.Stationinfo;
import com.opencsv.CSVReader;

public class GtfsCSV {
	private static org.apache.logging.log4j.Logger logger = LogManager.getLogger();
	
	public static List<Stationinfo> getStationsLijst(){
		List<Stationinfo> lijst = new ArrayList<Stationinfo>();
		try (CSVReader csvReader = new CSVReader(new FileReader("./persistentie/stops.csv"), ',', '"', 1)){
			String[] result = null;
			while((result = csvReader.readNext())!=null){
				Stationinfo station = new Stationinfo();
				station.setId(result[0]);
				station.setName(result[1]);
				station.setLocationY(result[2]);
				station.setLocationX(result[3]);
				lijst.add(station);
			}
		} catch (Exception e) {
			logger.error("getStations", e);
		}
		return lijst;
	}
}
