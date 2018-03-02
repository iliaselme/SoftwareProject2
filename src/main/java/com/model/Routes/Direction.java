package com.model.Routes;

/**
 * Created by jorda on 3/11/2016.
 */
public class Direction {
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
        return "DirectionPojo [name = "+name+"]";
    }
}
