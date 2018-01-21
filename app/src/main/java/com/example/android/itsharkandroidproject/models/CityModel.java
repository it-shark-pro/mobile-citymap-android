package com.example.android.itsharkandroidproject.models;

import java.io.Serializable;

public class CityModel implements Serializable{
    private int id;
    private String title;
    private String description;
    private double latitude;
    private double longitude;
    private String url;

    public int getId() { return id;}

    public String getTitle() { return title;}

    public String getDescription() { return description;}

    public String getUrl() { return url;}

    public double getLatitude() { return latitude;}

    public double getLongitude() { return longitude;}

}
