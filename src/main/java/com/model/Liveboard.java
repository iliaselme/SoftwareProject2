package com.model;

import org.boon.json.annotations.JsonProperty;

import java.time.LocalDateTime;

/**
 * Created by jordan on 7/11/2016.
 */
public class Liveboard {
    private String platform;
    private String station;
    private LocalDateTime departureTime;
    private int delay;
    private String vehicle;

    public Liveboard() {}
    
    public Liveboard(String platform, String station, LocalDateTime departureTime, int delay, String vehicle) {
        this.platform = platform;
        this.station = station;
        this.departureTime = departureTime;
        this.delay = delay;
        this.vehicle = vehicle;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Liveboard{" +
                "platform='" + platform + '\'' +
                ", station='" + station + '\'' +
                ", departureTime=" + departureTime +
                ", delay=" + delay +
                ", vehicle='" + vehicle + '\'' +
                '}';
    }
}
