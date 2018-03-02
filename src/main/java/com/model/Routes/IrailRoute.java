package com.model.Routes;

/**
 * Created by jorda on 3/11/2016.
 */
public class IrailRoute {
    private String timestamp;

    private Connection[] connection;

    private String version;

    public String getTimestamp ()
    {
        return timestamp;
    }

    public void setTimestamp (String timestamp)
    {
        this.timestamp = timestamp;
    }

    public Connection[] getConnection ()
    {
        return connection;
    }

    public void setConnection (Connection[] connection)
    {
        this.connection = connection;
    }

    public String getVersion ()
    {
        return version;
    }

    public void setVersion (String version)
    {
        this.version = version;
    }

    @Override
    public String toString()
    {
        return "IrailRoutePojo [version = "+version+", timestamp = "+timestamp+", connection = "+connection+"]";
    }
}
