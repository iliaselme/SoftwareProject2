package com.model.Routes;

/**
 * Created by jorda on 3/11/2016.
 */
public class Occupancy {
    private String name;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "OccupancyPojo [name = "+name+"]";
    }
    //TODO @URL parameter / Klasse verwijderen?
}
