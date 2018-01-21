package com.example.android.itsharkandroidproject.ui.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.android.itsharkandroidproject.Database;
import com.example.android.itsharkandroidproject.IDataSubscriber;
import com.example.android.itsharkandroidproject.models.CitiesResponse;
import com.example.android.itsharkandroidproject.models.CityModel;
import com.example.android.itsharkandroidproject.server_api.LoadCitiesResponseTask;
import com.google.gson.Gson;

import java.util.List;

public class MainPresenter implements IDataSubscriber{
    private final Context context;
    private final IMainActivityView mainActivityView;
    private final Database dataBase;
    private final LoadCitiesResponseTask loadCitiesResponseTask;

    MainPresenter(final Context context, final IMainActivityView mainActivityView) {
        this.context = context;
        this.mainActivityView = mainActivityView;
        this.dataBase = new Database(context);
        this.loadCitiesResponseTask = new LoadCitiesResponseTask(this);
    }

    void loadCities() {
        if (!isNetworkAvailable()) {
            final String citiesResponseString = dataBase.loadCitiesResponse();

            if (citiesResponseString.isEmpty()) {
                mainActivityView.showServerRequestFailed();
                return;
            }

            final List<CityModel> cities = parseCitiesResponse(citiesResponseString);
            mainActivityView.showCities(cities);
        } else {
            loadCitiesResponseTask.execute();
        }
    }

    private boolean isNetworkAvailable() {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {return false;}

        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void onDataLoaded(final String citiesResponseString) {
        dataBase.saveCitiesResponse(citiesResponseString);
        final List<CityModel> cities = parseCitiesResponse(citiesResponseString);
        mainActivityView.showCities(cities);
    }

    @Override
    public void onLoadError() {
        mainActivityView.showServerRequestFailed();
    }

    private List<CityModel> parseCitiesResponse(final String citiesResponseString) {
        final Gson gson = new Gson();
        final CitiesResponse citiesResponse = gson.fromJson(citiesResponseString, CitiesResponse.class);
        return citiesResponse.getPhotos();
    }
}
