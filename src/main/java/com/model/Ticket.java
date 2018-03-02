/**
 *
 */
package com.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ehbrail.WerknemerController;

/**
 * @author Vik Mortier
 *
 */

public class Ticket {

	private String vertrekStation;
	private String eindStation;

	private int ticket_id;
	private int korting_id;
	private int klasse;
	private int type;
	private int medewerker_id;
	private double prijs;

	private LocalDateTime datumAankoop;
	private LocalDate datumHeen;
	private LocalDate datumTerug;
	private int formuleId;

	/*
		Voorbeeld localDate, LocaldateTime (present)
		LocalDate todayLocalDate = LocalDate.now(ZoneId.of( "Europe/Brussels" ) );
		LocalDateTime todayLocalDateTime = LocalDateTime.now(ZoneId.of( "Europe/Brussels" ));

	 */

	public Ticket() {
	}

	public Ticket(String vertrekStation, String eindStation, int ticket_id, int klasse, int type, double prijs, LocalDateTime datumAankoop, LocalDate datumHeen, LocalDate datumTerug, int medewerker_id, int korting_id, int formuleId) {
		this.vertrekStation = vertrekStation;
		this.eindStation = eindStation;
		this.ticket_id = ticket_id;
		this.klasse = klasse;
		this.type = type;
		this.prijs = prijs;
		this.datumAankoop = datumAankoop;
		this.datumHeen = datumHeen;
		this.datumTerug = datumTerug;
		this.medewerker_id = medewerker_id;
		this.korting_id=korting_id;
		this.formuleId=formuleId;
	}

	public int getKorting_id(){
		return korting_id;
	}
	public void setKorting_id(int korting_id){
		this.korting_id=korting_id;
	}
	public int getMedewerker_id() {
		return medewerker_id;
	}

	public void setMedewerker_id(int medewerker_id) {
		this.medewerker_id = medewerker_id;
	}

	public String getVertrekStation() {
		return vertrekStation;
	}

	public void setVertrekStation(String vertrekStation) {
		this.vertrekStation = vertrekStation;
	}

	public String getEindStation() {
		return eindStation;
	}

	public void setEindStation(String eindStation) {
		this.eindStation = eindStation;
	}

	public int getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public int getKlasse() {
		return klasse;
	}

	public void setKlasse(int klasse) {
		this.klasse = klasse;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getPrijs() {
		return prijs;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}

	public LocalDateTime getDatumAankoop() {
		return datumAankoop;
	}

	public void setDatumAankoop(LocalDateTime datumAankoop) {
		this.datumAankoop = datumAankoop;
	}

	public LocalDate getDatumHeen() {
		return datumHeen;
	}

	public void setDatumHeen(LocalDate datumHeen) {
		this.datumHeen = datumHeen;
	}

	public LocalDate getDatumTerug() {
		return datumTerug;
	}

	public void setDatumTerug(LocalDate datumTerug) {
		this.datumTerug = datumTerug;
	}
	
	public int getFormuleId(){
		return formuleId;
	}
	
	public void setFormuleId(int formuleId){
		this.formuleId=formuleId;
	}

	@Override
	public String toString() {
		return "Ticket{" +
				"vertrekStation='" + vertrekStation + '\'' +
				", eindStation='" + eindStation + '\'' +
				", ticket_id=" + ticket_id +
				", klasse=" + klasse +
				", type=" + type +
				", prijs=" + prijs +
				", datumAankoop=" + datumAankoop +
				", datumHeen=" + datumHeen +
				", datumTerug=" + datumTerug +
				", medewerker_id=" + medewerker_id +
				", korting_id=" + korting_id +
				'}';
	}
}
