package com.example.android.itsharkandroidproject.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.android.itsharkandroidproject.listeners.ItemClickListener;
import com.example.android.itsharkandroidproject.models.CityModel;
import com.example.android.itsharkandroidproject.server_api.LoadCitiesTask;
import com.example.android.itsharkandroidproject.R;
import com.example.android.itsharkandroidproject.ui.details.CityDetailsActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener, IMainActivityView {

    public static String EXTRA_CITY_TITLE = "EXTRA_CITY_TITLE";
    public static String DEFAULT_CITY_TITLE = "";
    public static String EXTRA_CITY_URL = "EXTRA_CITY_URL";
    public static String DEFAULT_CITY_URL = "";
    private final String NO_INTERNET_MESSAGE = "Please, make sure that mobile internet or WIFI is enabled";

    private CityAdapter cityAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();

        if (isNetworkAvailable()) {
            final LoadCitiesTask loadCitiesTask = new LoadCitiesTask(this);
            loadCitiesTask.execute();
        } else {
            showInternetConnectionErrorMessage();
        }
    }

    private void initRecyclerView() {
        final RecyclerView citiesRecyclerView = findViewById(R.id.recycler_view_main_cities_list);

        final LinearLayoutManager citiesLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        citiesRecyclerView.setLayoutManager(citiesLayoutManager);

        cityAdapter = new CityAdapter(getApplicationContext());
        citiesRecyclerView.setAdapter(cityAdapter);
        cityAdapter.setItemClickListener(this);
    }

    public void showCities(final List<CityModel> cities) {
        cityAdapter.update(cities);
    }

    @Override
    public void onItemClick(final View view, final int position, final RecyclerView.Adapter adapter) {
        final CityModel cityModel = ((CityAdapter) adapter).getItemByPosition(position);

        final Intent detailedActivityIntent = new Intent(this, CityDetailsActivity.class);
        detailedActivityIntent.putExtra(EXTRA_CITY_TITLE, cityModel.getTitle());
        detailedActivityIntent.putExtra(EXTRA_CITY_URL, cityModel.getUrl());

        startActivity(detailedActivityIntent);
    }

    private boolean isNetworkAvailable() {
        final ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {return false;}

        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void showInternetConnectionErrorMessage() {
        Toast.makeText(getApplicationContext(), NO_INTERNET_MESSAGE, Toast.LENGTH_SHORT).show();
    }
}
