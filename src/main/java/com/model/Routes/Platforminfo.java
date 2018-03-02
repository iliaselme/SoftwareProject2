package com.model.Routes;

/**
 * Created by jorda on 3/11/2016.
 */
public class Platforminfo {
    private String normal;

    private String name;

    public String getNormal ()
    {
        return normal;
    }

    public void setNormal (String normal)
    {
        this.normal = normal;
    }

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
        return "PlatforminfoPojo [name = "+name+", normal = "+normal+"]";
    }
}
