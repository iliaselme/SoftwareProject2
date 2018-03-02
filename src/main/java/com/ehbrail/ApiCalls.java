package com.ehbrail;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by jorda on 3/11/2016.
 */
public  class ApiCalls {


    //IRail: Krijgt een treinID mee bv: IC545. Geeft Response terug. XML
    public static Response getTrainInfoByID(String treinID) throws IOException {
        String url = "https://api.irail.be/vehicle/?id=BE.NMBS."+treinID+"&format=xml";
        long startTime = System.currentTimeMillis();
        Response response = doGetRequest(url);
        long stopTime = System.currentTimeMillis();
        long result = stopTime - startTime;
        System.out.println("Tijd nodig voor getTrainInfoByID = " + result);
        return response;
    }

    //IRail: Haalt alle stations op als XML  (Gebruikt om list te vullen en autocomplete toe te staan.) geeft Response terug
    public static Response getStationsXML() throws IOException {
        String url = "https://api.irail.be/stations";
        long startTime = System.currentTimeMillis();
        Response response = doGetRequest(url);
        long stopTime = System.currentTimeMillis();
        long result = stopTime - startTime;
        System.out.println("Tijd nodig voor stationsXML = " + result);
        return response;
    }

    //IRail: Haalt route informatie op van vertrek tot aankomst station. Als JSON. geeft Response terug.
    public static Response getIRailRoute(String van, String naar) throws IOException {
        String url = "https://api.irail.be/connections/?to="+naar+"&from="+van+"&format=json";
        long startTime = System.currentTimeMillis();
        Response response = doGetRequest(url);
        long stopTime = System.currentTimeMillis();
        long result = stopTime - startTime;
        System.out.println("Tijd nodig voor getIRailRoute = " + result);
        return response;
    }
    
    public static Response getIRailRouteXML(String van, String naar) throws IOException {
        String url = "https://api.irail.be/connections/?to="+naar+"&from="+van+"&format=xml";
        long startTime = System.currentTimeMillis();
        Response response = doGetRequest(url);
        long stopTime = System.currentTimeMillis();
        long result = stopTime - startTime;
        System.out.println("Tijd nodig voor getIRailRoute = " + result);
        return response;
    }

    //IRail: Haalt route informatie op van vertrek tot aankomst station. Extra opties zoals tijd/datum/aankomst-vertrek. Als JSON. geeft Response terug.
    public static Response getExtendedIRailRoute(String van, String naar, String datedmy, String time, String timeSel) throws IOException {
        String url = "https://api.irail.be/connections/?to="+naar+"&from="+van+"&date="+datedmy +"&time="+time+"&timeSel="+timeSel+"&format=json";
        long startTime = System.currentTimeMillis();
        Response response = doGetRequest(url);
        long stopTime = System.currentTimeMillis();
        long result = stopTime - startTime;
        System.out.println("Tijd nodig voor getExtendedIRailRoute = " + result);
        return response;
    }


    //IRail: haalt Liveboard info op. XML
    public static Response getIRailLiveboard (String station) throws IOException {
        String url = "https://api.irail.be/liveboard/?station="+station+"&fast=true&format=xml";
        long startTime = System.currentTimeMillis();
        Response response = doGetRequest(url);
        long stopTime = System.currentTimeMillis();
        long result = stopTime - startTime;
        System.out.println("Tijd nodig getIRailLiveboard = " + result);
        return response;
    }

    //Geeft Response terug van url als String
    public static Response doGetRequest(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }

}
