package com.model;
/**
 * 
 * @author Michael
 *
 */
public class Korting {
	private int korting_id, percentage;
	private String naam, beschrijving;
	private boolean actief;
	
	public Korting(int korting_id,String naam,String beschrijving,int percentage,boolean actief){
		this.korting_id=korting_id;
		this.naam=naam;
		this.beschrijving=beschrijving;
		this.percentage=percentage;
		this.actief=actief;
	}
	
	public Korting(String naam,String beschrijving, int percentage, boolean actief){
		this.naam=naam;
		this.beschrijving=beschrijving;
		this.percentage=percentage;
		this.actief=actief;
	}
	
	public Korting() {
		super();
	}
	
	public int getKorting() {
		return korting_id;
	}
	public void setKorting(int korting) {
		this.korting_id = korting;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public String getBeschrijving() {
		return beschrijving;
	}
	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}
	public boolean isActief() {
		return actief;
	}
	public void setActief(boolean actief) {
		this.actief = actief;
	}
	
	public String toString(){
		String s;
		s=naam+ ", "+percentage+"%";
		return s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (actief ? 1231 : 1237);
		result = prime * result + ((beschrijving == null) ? 0 : beschrijving.hashCode());
		result = prime * result + korting_id;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		result = prime * result + percentage;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korting other = (Korting) obj;
		if (actief != other.actief)
			return false;
		if (beschrijving == null) {
			if (other.beschrijving != null)
				return false;
		} else if (!beschrijving.equals(other.beschrijving))
			return false;
		if (korting_id != other.korting_id)
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		if (percentage != other.percentage)
			return false;
		return true;
	}
	
	
}
