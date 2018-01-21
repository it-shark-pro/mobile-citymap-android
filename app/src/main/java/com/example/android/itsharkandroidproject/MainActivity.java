package com.example.android.itsharkandroidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener, IMainActivityView{

    public static String EXTRA_CITY_TITLE = "EXTRA_CITY_TITLE";
    public static String DEFAULT_CITY_TITLE = "";
    public static String EXTRA_CITY_DESCRIPTION = "EXTRA_CITY_DESCRIPTION";
    public static String DEFAULT_CITY_DESCRIPTION = "";

    private CityAdapter cityAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();

        final LoadCitiesTask loadCitiesTask = new LoadCitiesTask(this);
        loadCitiesTask.execute();
    }

    private void initRecyclerView() {
        final RecyclerView citiesRecyclerView = findViewById(R.id.recycler_view_main_cities_list);

        final LinearLayoutManager citiesLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        citiesRecyclerView.setLayoutManager(citiesLayoutManager);

        cityAdapter = new CityAdapter();
        citiesRecyclerView.setAdapter(cityAdapter);
        cityAdapter.setItemClickListener(this);

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                citiesRecyclerView.getContext(),
                citiesLayoutManager.getOrientation()
        );
        citiesRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    public void showCities(final List<CityModel> cities) {
        for (final CityModel item : cities) {
            Log.d("MainActivity", "item " +item.getId() + " " +item.getUrl());
        }
    }

    @Override
    public void onItemClick(final View view, final int position, final RecyclerView.Adapter adapter) {
        final CityModel cityModel = ((CityAdapter) adapter).getItemByPosition(position);

        final Intent detailedActivityIntent = new Intent(this, CityDetailsActivity.class);
        detailedActivityIntent.putExtra(EXTRA_CITY_TITLE, cityModel.getTitle());
        detailedActivityIntent.putExtra(EXTRA_CITY_DESCRIPTION, cityModel.getDescription());

        startActivity(detailedActivityIntent);
    }
}
