package com.example.android.itsharkandroidproject.server_api;

import android.os.AsyncTask;

import com.example.android.itsharkandroidproject.models.CitiesResponse;
import com.example.android.itsharkandroidproject.models.CityModel;
import com.example.android.itsharkandroidproject.ui.main.IMainActivityView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoadCitiesTask extends AsyncTask<Void, Void, List<CityModel>>{
    private final String API_URL = "https://api.myjson.com/bins/upt7z";

    private OkHttpClient client;
    private Request request;
    private Gson gson;
    private IMainActivityView target;

    public LoadCitiesTask(final IMainActivityView target) {
        this.target = target;

        gson = new Gson();
        client = new OkHttpClient();
        request = new Request.Builder()
                .url(API_URL)
                .build();
    }

    @Override
    protected List<CityModel> doInBackground(final Void... voids) {
        List<CityModel> cityModelsResponse = Collections.emptyList();

        try {
            final Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            final String responseBodyString = response.body().string();
            cityModelsResponse = gson.fromJson(responseBodyString, CitiesResponse.class).getPhotos();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cityModelsResponse;
    }

    @Override
    protected void onPostExecute(final List<CityModel> cityModelsResponse) {
        target.showCities(cityModelsResponse);
    }
}
