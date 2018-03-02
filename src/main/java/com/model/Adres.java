package com.model;

/**
 * @author Jordan Vander Elst
 */
public class Adres {
    private int adres_id;
    private String plaatsnaam;
    private String straat;
    private int huisnr;
    private String brievenbus;
    private int postcode;


    public Adres() {}

    public Adres(int adres_id, String plaatsnaam, String straat, int huisnr, String brievenbus, int postcode) {
        this.adres_id = adres_id;
        this.plaatsnaam = plaatsnaam;
        this.straat = straat;
        this.huisnr = huisnr;
        this.brievenbus = brievenbus;
        this.postcode = postcode;
    }

    public int getAdres_id() {
        return adres_id;
    }

    public void setAdres_id(int adres_id) {
        this.adres_id = adres_id;
    }

    public String getPlaatsnaam() {
        return plaatsnaam;
    }

    public void setPlaatsnaam(String plaatsnaam) {
        this.plaatsnaam = plaatsnaam;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public int getHuisnr() {
        return huisnr;
    }

    public void setHuisnr(int huisnr) {
        this.huisnr = huisnr;
    }

    public String getBrievenbus() {
        return brievenbus;
    }

    public void setBrievenbus(String brievenbus) {
        this.brievenbus = brievenbus;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "Adres{" +
                "adres_id=" + adres_id +
                ", plaatsnaam='" + plaatsnaam + '\'' +
                ", straat='" + straat + '\'' +
                ", huisnr=" + huisnr +
                ", brievenbus='" + brievenbus + '\'' +
                ", postcode=" + postcode +
                '}';
    }
}
