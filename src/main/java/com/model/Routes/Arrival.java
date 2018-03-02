package com.model.Routes;

/**
 * Created by jorda on 3/11/2016.
 */
public class Arrival {
    private Stationinfo stationinfo;

    private String canceled;

    private String platform;

    private String time;

    private Platforminfo platforminfo;

    private String station;

    private Direction direction;

    private String vehicle;

    private String delay;

    public Stationinfo getStationinfo ()
    {
        return stationinfo;
    }

    public void setStationinfo (Stationinfo stationinfo)
    {
        this.stationinfo = stationinfo;
    }

    public String getCanceled ()
    {
        return canceled;
    }

    public void setCanceled (String canceled)
    {
        this.canceled = canceled;
    }

    public String getPlatform ()
    {
        return platform;
    }

    public void setPlatform (String platform)
    {
        this.platform = platform;
    }

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    public Platforminfo getPlatforminfo ()
    {
        return platforminfo;
    }

    public void setPlatforminfo (Platforminfo platforminfo)
    {
        this.platforminfo = platforminfo;
    }

    public String getStation ()
    {
        return station;
    }

    public void setStation (String station)
    {
        this.station = station;
    }

    public Direction getDirection ()
    {
        return direction;
    }

    public void setDirection (Direction direction)
    {
        this.direction = direction;
    }

    public String getVehicle ()
    {
        return vehicle;
    }

    public void setVehicle (String vehicle)
    {
        this.vehicle = vehicle;
    }

    public String getDelay ()
    {
        return delay;
    }

    public void setDelay (String delay)
    {
        this.delay = delay;
    }

    @Override
    public String toString()
    {
        return "ArrivalPojo [delay = "+delay+", station = "+station+",stationinfo = "+stationinfo+",time = "+time+",  vehicle = "+vehicle+", platform = "+platform+",  platforminfo = "+platforminfo+",canceled = "+canceled+", direction = "+direction+"]";
    }
}
