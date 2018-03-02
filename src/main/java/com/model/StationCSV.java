package com.model;


/**
 * Created by jorda on 7/11/2016.
 */
public class StationCSV {

    private String Uri;
    private String name;
    //alternative-fr
    private String nameFR;
    //alternative-nl
    private String nameNL;
    //alternative-de
    private String nameDE;
    //alternative-en
    private String nameENG;
    //country-code
    private String countryCode;
    private double longitude;
    private double latitude;
    //private double avg_stop_times;

    public String getUri() {
        return Uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameFR() {
        return nameFR;
    }

    public void setNameFR(String nameFR) {
        this.nameFR = nameFR;
    }

    public String getNameNL() {
        return nameNL;
    }

    public void setNameNL(String nameNL) {
        this.nameNL = nameNL;
    }

    public String getNameDE() {
        return nameDE;
    }

    public void setNameDE(String nameDE) {
        this.nameDE = nameDE;
    }

    public String getNameENG() {
        return nameENG;
    }

    public void setNameENG(String nameENG) {
        this.nameENG = nameENG;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "StationCSV{" +
                "Uri='" + Uri + '\'' +
                ", name='" + name + '\'' +
                ", nameFR='" + nameFR + '\'' +
                ", nameNL='" + nameNL + '\'' +
                ", nameDE='" + nameDE + '\'' +
                ", nameENG='" + nameENG + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
