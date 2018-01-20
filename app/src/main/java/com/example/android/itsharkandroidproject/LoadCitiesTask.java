package com.example.android.itsharkandroidproject;

import android.os.AsyncTask;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoadCitiesTask extends AsyncTask<Void, Void, List<ListItemModel>>{
    private final String API_URL = "https://api.myjson.com/bins/upt7z";

    private OkHttpClient client;
    private Request request;
    private Gson gson;
    private IMainActivityView target;

    LoadCitiesTask(final IMainActivityView target) {
        this.target = target;

        gson = new Gson();
        client = new OkHttpClient();
        request = new Request.Builder()
                .url(API_URL)
                .build();
    }

    @Override
    protected List<ListItemModel> doInBackground(final Void... voids) {
        List<ListItemModel> listItemModelsResponse = Collections.emptyList();

        try {
            final Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            final String responseBodyString = response.body().string();
            listItemModelsResponse = gson.fromJson(responseBodyString, ListItemModelsResponse.class).getPhotos();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listItemModelsResponse;
    }

    @Override
    protected void onPostExecute(final List<ListItemModel> listItemModelsResponse) {
        target.showCities(listItemModelsResponse);
    }
}
