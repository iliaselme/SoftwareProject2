package com.model;

import com.ehbrail.AdminController;

public class Formule {
	String formule;
	boolean active;
	int formuleId;
	int medewerkerId;
	
	public Formule(){
		formule=null;
		active=false;
	}
	
	public Formule(String formule, boolean active, int formuleId){
		this.formule=formule;
		this.active=active;
		this.formuleId=formuleId;
		medewerkerId=AdminController.getLogin().getMedewerker_id();
	}
	
	public Formule(String formule, boolean active, int formuleId, int medewerkerId){
		this.formule=formule;
		this.active=active;
		this.formuleId=formuleId;
		this.medewerkerId=medewerkerId;
	}
	
	public String getFormule() {
		return formule;
	}
	public void setFormule(String formule) {
		this.formule = formule;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public int getMedewerkerId(){
		return medewerkerId;
	}

	public void setMedewerkerId(int id){
		this.medewerkerId=id;
	}
	
	public int getFormuleId(){
		return formuleId;
	}
	
	public void setFormuleId(int formuleId){
		this.formuleId=formuleId;
	}
}
