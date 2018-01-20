package com.example.android.itsharkandroidproject;

public class ListItemModel {
    private int id;
    private String title;
    private String description;
    private double latitude;
    private double longitude;
    private String url;

    private final String NAME_PATTERN = "Item ";
    private final String DESCRIPTION = "This is item ";

    ListItemModel(final int id) {
        this.id = id;
        this.title = NAME_PATTERN +id;
        this.description = DESCRIPTION +id;
    }

    public int getId() { return id;}

    public String getTitle() { return title;}

    public String getDescription() { return description;}

    public String getUrl() { return url;}

    public double getLatitude() { return latitude;}

    public double getLongitude() { return longitude;}

}
