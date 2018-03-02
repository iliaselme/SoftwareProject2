package com.model;

/**
 * Created by Vik Mortier on 4/11/2016.
 */
public class Werknemer {

    private String naam;
    private String voornaam;
    private boolean actief;
    private Login login;
    private int werknemerId;

    public Werknemer() {
    }

    public Werknemer(String naam, String voornaam, boolean actief, Login login, int werknemerId) {
        this.naam = naam;
        this.voornaam = voornaam;
        this.actief = actief;
        this.login = login;
        this.werknemerId = werknemerId;
    }

    public boolean isActief() {
        return actief;
    }

    public void setActief(boolean actief) {
        this.actief = actief;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public int getWerknemerId() {
        return werknemerId;
    }

    public void setWerknemerId(int persoonId) {
        this.werknemerId = persoonId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    @Override
    public String toString() {
        return "Werknemer{" +
                "actief=" + actief +
                ", login=" + login +
                ", persoonId=" + werknemerId +
                '}';
    }
}
