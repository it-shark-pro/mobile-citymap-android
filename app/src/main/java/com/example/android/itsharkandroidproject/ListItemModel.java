package com.example.android.itsharkandroidproject;

public class ListItemModel {
    private int id;
    private String name;
    private String description;

    private final String NAME_PATTERN = "Item ";
    private final String DESCRIPTION = "This is item ";

    ListItemModel(final int id) {
        this.id = id;
        this.name = NAME_PATTERN +id;
        this.description = DESCRIPTION +id;
    }

    public int getId() { return id;}

    public String getName() { return name;}

    public String getDescription() { return description; }
}
