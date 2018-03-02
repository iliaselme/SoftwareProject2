package com.model;

import java.time.LocalDateTime;

/**
 * Created by jorda on 26/10/2016.
 */
public class TrainInfo {
    private int stopID;
    private String station;
    private LocalDateTime time;
    private String platform;

    public TrainInfo() {}
    
    public TrainInfo(int stopID, String station, LocalDateTime time, String platform) {
        this.stopID = stopID;
        this.station = station;
        this.time = time;
        this.platform = platform;
    }


    public int getStopID() {
        return stopID;
    }

    public void setStopID(int stopID) {
        this.stopID = stopID;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "TrainInfo{" +
                "stopID=" + stopID +
                ", station='" + station + '\'' +
                ", time=" + time +
                ", platform='" + platform + '\'' +
                '}';
    }
}
