package com.model.Routes;

/**
 * Created by jorda on 3/11/2016.
 */
public class Via {
    private Stationinfo stationinfo;

    private String id;

    private String station;

    private Direction direction;

    private String vehicle;

    private Departure departure;

    private Arrival arrival;

    private String timeBetween;

    public Stationinfo getStationinfo ()
    {
        return stationinfo;
    }

    public void setStationinfo (Stationinfo stationinfo)
    {
        this.stationinfo = stationinfo;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
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

    public Departure getDeparture ()
    {
        return departure;
    }

    public void setDeparture (Departure departure)
    {
        this.departure = departure;
    }

    public Arrival getArrival ()
    {
        return arrival;
    }

    public void setArrival (Arrival arrival)
    {
        this.arrival = arrival;
    }

    public String getTimeBetween ()
    {
        return timeBetween;
    }

    public void setTimeBetween (String timeBetween)
    {
        this.timeBetween = timeBetween;
    }

    @Override
    public String toString()
    {
        return "ViaPojo [id = "+id+",arrival = "+arrival+",departure = "+departure+",timeBetween = "+timeBetween+",station = "+station+", stationinfo = "+stationinfo+", vehicle = "+vehicle+", direction = "+direction+"]";
    }
}
