package com.example.android.itsharkandroidproject.server_api;

import android.os.AsyncTask;
import com.example.android.itsharkandroidproject.IDataSubscriber;
import com.example.android.itsharkandroidproject.utils.StringUtils;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoadCitiesResponseTask extends AsyncTask<Void, Void, String>{
    private final String API_URL = "https://api.myjson.com/bins/upt7z";

    private OkHttpClient client;
    private Request request;
    private IDataSubscriber dataSubscriber;

    public LoadCitiesResponseTask(final IDataSubscriber citiesResponseSubscriber) {
        this.dataSubscriber = citiesResponseSubscriber;

        client = new OkHttpClient();
        request = new Request.Builder()
                .url(API_URL)
                .build();
    }

    @Override
    protected String doInBackground(final Void... voids) {
        String citiesResponseString = StringUtils.EMPTY_STRING;

        try {
            final Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                dataSubscriber.onLoadError();
            }

            citiesResponseString = response.body().string();
        } catch (final IOException exception) {
            exception.printStackTrace();
            dataSubscriber.onLoadError();
        }

        return citiesResponseString;
    }

    @Override
    protected void onPostExecute(final String cityModelsResponseString) {
        dataSubscriber.onDataLoaded(cityModelsResponseString);
    }
}
