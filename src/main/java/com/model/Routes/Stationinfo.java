package com.model.Routes;

import org.boon.json.annotations.JsonProperty;

/**
 * Created by jorda on 3/11/2016.
 */
public class Stationinfo {
    private String id;

    private String name;

    private String locationX;

    private String standardname;

    private String locationY;

    @JsonProperty("@id")
    private String url;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getLocationX ()
    {
        return locationX;
    }

    public void setLocationX (String locationX)
    {
        this.locationX = locationX;
    }

    public String getStandardname ()
    {
        return standardname;
    }

    public void setStandardname (String standardname)
    {
        this.standardname = standardname;
    }

    public String getLocationY ()
    {
        return locationY;
    }

    public void setLocationY (String locationY)
    {
        this.locationY = locationY;
    }

    public String getUrl() {return url;    }

    public void setUrl(String url) {        this.url = url;    }

    @Override
    public String toString()
    {
        return "StationinfoPojo [id = "+id+",locationX = "+locationX+", locationY = "+locationY+", @id = "+url+" standardname = "+standardname+", name = "+name+"]";
    }
}
