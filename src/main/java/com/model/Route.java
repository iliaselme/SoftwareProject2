package com.model;

import java.util.List;

/**
 * @author Vik Mortier
 *
 */

public class Route {
	
	private double afstand;
	private List<Integer> perrons;
	private List<Station> stations;
	
	
	public Route() {
		super();
	}
	
	public Route(double afstand, List<Integer> perrons, List<Station> stations) {
		super();
		this.afstand = afstand;
		this.perrons = perrons;
		this.stations = stations;
	}
	
	public double getAfstand() {
		return afstand;
	}
	public void setAfstand(double afstand) {
		this.afstand = afstand;
	}
	public List<Integer> getPerrons() {
		return perrons;
	}
	public void setPerrons(List<Integer> perrons) {
		this.perrons = perrons;
	}
	public List<Station> getStations() {
		return stations;
	}
	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
	
	@Override
	public String toString() {
		return "Route [afstand=" + afstand + ", perrons=" + perrons + ", stations=" + stations + "]";
	}

	
}
