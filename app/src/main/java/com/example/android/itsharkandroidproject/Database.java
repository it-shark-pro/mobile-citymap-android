package com.example.android.itsharkandroidproject;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.itsharkandroidproject.utils.StringUtils;

public class Database {
    private final String CITIES_RESPONSE_KEY = "CITIES_RESPONSE_KEY";
    private final String CITIES_DB_NAME = "com.example.itsharkandroidproject.citiesResponse";

    private final SharedPreferences sharedPreferences;

    public Database(final Context context) {
        this.sharedPreferences = context.getSharedPreferences(CITIES_DB_NAME, Context.MODE_PRIVATE);
    }

    public String loadCitiesResponse() {
        return sharedPreferences.getString(CITIES_RESPONSE_KEY, StringUtils.EMPTY_STRING);
    }

    public void saveCitiesResponse(final String citiesResponseString) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CITIES_RESPONSE_KEY, citiesResponseString);
        editor.apply();
    }
}
