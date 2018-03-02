/**
 * 
 */
package com.model;

/**
 * @author Vik Mortier
 *
 */
public class Station {
	
	private String naam;
	private double latitude;
	private double longitude;
	
	
	
	public Station() {
		super();
	}

	public Station(String naam, double latitude, double longitude) {
		super();
		this.naam = naam;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	@Override
	public String toString() {
		return "Station [naam=" + naam + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	

}
