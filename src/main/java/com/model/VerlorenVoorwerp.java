package com.model;
/**
*
* @author Ilias El Mesaoudi
**/


import java.sql.Date;


public class VerlorenVoorwerp {

	private int voorwerpid;
	private String naam;
	private String omschrijving;
	private Date datum;
	private String station;
	private boolean aanwezig;

	@Override
	public String toString() {
		return "VerlorenVoorwerp voorwerpid=" + voorwerpid + ", naam=" + naam + ", omschrijving=" + omschrijving
				+ ", datum=" + datum + ", station=" + station + ",aanwezig=" + aanwezig;
	}

	public boolean getAanwezig() {
		return aanwezig;
	}

	public void setAanwezig(boolean aanwezig) {
		this.aanwezig = aanwezig;
	}

	public VerlorenVoorwerp() {
	}

	public VerlorenVoorwerp(int voorwerpid, String naam, String omschrijving, Date datum,
			String station) {
		this.voorwerpid = voorwerpid;
		this.naam = naam;
		this.omschrijving = omschrijving;
		this.datum = datum;
		this.station = station;
	}

	public VerlorenVoorwerp(String naam, String omschrijving, Date datum, String station) {
		this.naam = naam;
		this.omschrijving = omschrijving;
		this.datum = datum;
		this.station = station;
	}

	public int getVoorwerpid() {
		return voorwerpid;
	}

	public void setVoorwerpid(int voorwerpid) {
		this.voorwerpid = voorwerpid;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}
}
