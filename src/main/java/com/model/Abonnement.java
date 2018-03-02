package com.model;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * @author Vik Mortier
 *
 */

public class Abonnement {

	private int abonnement_id;
	private int klant_id;
	private int medewerker_id;
	private int klasse;
	private int actief;
	private double prijs;
	private Route route;
	private String beginStation;
	private String eindStation;
	private String station; // waar abonnement werd aangemaakt
	private int korting_id;
	private LocalDate beginDatum;
	private LocalDate eindDatum;
	private LocalDateTime aankoopDatum;
	
	
	
	public int getMedewerker_id() {
		return medewerker_id;
	}

	public void setMedewerker_id(int medewerker_id) {
		this.medewerker_id = medewerker_id;
	}

	public int getKorting_id() {
		return korting_id;
	}

	public void setKorting_id(int korting_id) {
		this.korting_id = korting_id;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}


	public Abonnement() {
		super();
	}

	public Abonnement(int abonnement_id, int klant_id, int medewerker_id, int klasse, int actief, double prijs, Route route, String beginStation, String eindStation, LocalDate beginDatum, LocalDate eindDatum,String station,int korting_id, LocalDateTime aankoopDatum) {
		this.abonnement_id = abonnement_id;
		this.aankoopDatum = aankoopDatum;
		this.klant_id = klant_id;
		this.klasse = klasse;
		this.actief = actief;
		this.prijs = prijs;
		this.route = route;
		this.beginStation = beginStation;
		this.eindStation = eindStation;
		this.beginDatum = beginDatum;
		this.eindDatum = eindDatum;
		this.medewerker_id=medewerker_id;
		this.station= station;
		this.korting_id = korting_id;
	}

	public int getAbonnement_id() {
		return abonnement_id;
	}

	public void setAbonnement_id(int abonnement_id) {
		this.abonnement_id = abonnement_id;
	}

	public int getKlant_id() {
		return klant_id;
	}

	public void setKlant_id(int klant_id) {
		this.klant_id = klant_id;
	}

	public int getKlasse() {
		return klasse;
	}

	public void setKlasse(int klasse) {
		this.klasse = klasse;
	}

	public int getActief() {
		return actief;
	}

	public void setActief(int actief) {
		this.actief = actief;
	}

	public double getPrijs() {
		return prijs;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public String getBeginStation() {
		return beginStation;
	}

	public void setBeginStation(String beginStation) {
		this.beginStation = beginStation;
	}

	public String getEindStation() {
		return eindStation;
	}

	public void setEindStation(String eindStation) {
		this.eindStation = eindStation;
	}

	public LocalDate getBeginDatum() {
		return beginDatum;
	}

	public void setBeginDatum(LocalDate beginDatum) {
		this.beginDatum = beginDatum;
	}

	public LocalDate getEindDatum() {
		return eindDatum;
	}

	public void setEindDatum(LocalDate eindDatum) {
		this.eindDatum = eindDatum;
	}

	@Override
	public String toString() {
		return "Abonnement [abonnement_id=" + abonnement_id + ", klant_id=" + klant_id + ", klasse=" + klasse
				+ ", prijs=" + prijs + ", route=" + route + ", beginDatum=" + beginDatum
				+ ", eindDatum=" + eindDatum + "]";
	}


	public LocalDateTime getAankoopDatum() {
		return aankoopDatum;
	}

	public void setAankoopDatum(LocalDateTime aankoopDatum) {
		this.aankoopDatum = aankoopDatum;
	}
}