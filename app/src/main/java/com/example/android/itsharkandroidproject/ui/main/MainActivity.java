package com.example.android.itsharkandroidproject.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.itsharkandroidproject.listeners.ItemClickListener;
import com.example.android.itsharkandroidproject.models.CityModel;
import com.example.android.itsharkandroidproject.R;
import com.example.android.itsharkandroidproject.ui.map.MapActivity;
import com.example.android.itsharkandroidproject.ui.details.CityDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener, IMainActivityView {

    public static String EXTRA_CITY_TITLE = "EXTRA_CITY_TITLE";
    public static String DEFAULT_CITY_TITLE = "";
    public static String EXTRA_CITY_URL = "EXTRA_CITY_URL";
    public static String DEFAULT_CITY_URL = "";
    public static String EXTRA_CITIES = "EXTRA_CITIES";
    private final String REQUEST_ERROR_MESSAGE = "An error occurred while retrieving data from server";
    private List<CityModel> cities;

    private MainPresenter presenter;
    private Context appContext;
    private CityAdapter cityAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appContext = getApplicationContext();
        initRecyclerView();

        presenter = new MainPresenter(appContext, this);
        presenter.loadCities();
    }

    private void initRecyclerView() {
        final RecyclerView citiesRecyclerView = findViewById(R.id.recycler_view_main_cities_list);

        final LinearLayoutManager citiesLayoutManager = new GridLayoutManager(appContext, 3);
        citiesRecyclerView.setLayoutManager(citiesLayoutManager);

        cityAdapter = new CityAdapter(appContext);
        citiesRecyclerView.setAdapter(cityAdapter);
        cityAdapter.setItemClickListener(this);
    }

    public void showCities(final List<CityModel> cities) {
        this.cities = cities;
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

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_on_map:
                final Intent intent = new Intent(this, MapActivity.class);
                intent.putExtra(EXTRA_CITIES, (ArrayList)cities);
                startActivity(intent);
            break;
        }

        return true;
    }

    public void showServerRequestFailed() {
        Toast.makeText(appContext, REQUEST_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
    }
}
