package com.persistentie;

import java.util.List;

import com.database.FormuleDAO;
import com.database.KortingDAO;
import com.model.Formule;
import com.model.Korting;
import com.model.Routes.Stationinfo;

// deze klasse staat in voor het bijhouden van data afkomstig uit de database, die gebruikt wordt wanneer er geen informatie online kan gevonden worden.
public class Cache {
	private static boolean binnengehaald = false;
	private List<Korting> kortingen = null;
	private Formule formule = null;
	private List<Stationinfo> stationsInfo = null;
	
	public Cache(){
		setKortingen(KortingDAO.getActieveKortingen());
		setFormule(FormuleDAO.getFormuleActive());
		setStationsInfo(GtfsCSV.getStationsLijst());
		if(kortingen != null && formule != null){
			binnengehaald = true;
		}
	}

	public static boolean isBinnengehaald() {
		return binnengehaald;
	}

	public void setBinnengehaald(boolean binnengehaald) {
		this.binnengehaald = binnengehaald;
	}

	public List<Korting> getKortingen() {
		return kortingen;
	}

	public void setKortingen(List<Korting> kortingen) {
		this.kortingen = kortingen;
	}

	public Formule getFormule() {
		return formule;
	}

	public void setFormule(Formule formule) {
		this.formule = formule;
	}

	public List<Stationinfo> getStationsInfo() {
		return stationsInfo;
	}

	public void setStationsInfo(List<Stationinfo> stationsInfo) {
		this.stationsInfo = stationsInfo;
	}

}
