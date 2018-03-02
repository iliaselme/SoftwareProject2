package com.model.Routes;

/**
 * Created by jorda on 3/11/2016.
 */
public class Vias {
    private Via[] via;

    private String number;

    public Via[] getVia ()
    {
        return via;
    }

    public void setVia (Via[] via)
    {
        this.via = via;
    }

    public String getNumber ()
    {
        return number;
    }

    public void setNumber (String number)
    {
        this.number = number;
    }

    @Override
    public String toString()
    {
        return "ViasPojo [number = "+number+", via = "+via+"]";
    }
}
