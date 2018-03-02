package com.model.Routes;

/**
 * Created by jorda on 3/11/2016.
 */
public class Connection {
    private String id;

    private Vias vias;

    private String duration;

    private Arrival arrival;

    private Departure departure;

    private Occupancy occupancy;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Vias getVias ()
    {
        return vias;
    }

    public void setVias (Vias vias)
    {
        this.vias = vias;
    }

    public String getDuration ()
    {
        return duration;
    }

    public void setDuration (String duration)
    {
        this.duration = duration;
    }

    public Arrival getArrival ()
    {
        return arrival;
    }

    public void setArrival (Arrival arrival)
    {
        this.arrival = arrival;
    }

    public Departure getDeparture ()
    {
        return departure;
    }

    public void setDeparture (Departure departure)
    {
        this.departure = departure;
    }

    public Occupancy getOccupancy ()
    {
        return occupancy;
    }

    public void setOccupancy (Occupancy occupancy)
    {
        this.occupancy = occupancy;
    }

    @Override
    public String toString()
    {
        return "ConnectionPojo [id = "+id+",departure = "+departure+",  arrival = "+arrival+",duration = "+duration+", vias = "+vias+", occupancy = "+occupancy+"]";
    }
}
