package com.example.android.itsharkandroidproject.ui;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.android.itsharkandroidproject.R;
import com.example.android.itsharkandroidproject.models.CityModel;
import com.example.android.itsharkandroidproject.ui.main.MainActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private List<CityModel> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        cities = (ArrayList) getIntent().getSerializableExtra(MainActivity.EXTRA_CITIES);

        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        for (final CityModel city: cities) {
            final LatLng cityCoordinates = new LatLng(city.getLatitude(), city.getLongitude());

            googleMap.addMarker(new MarkerOptions()
                    .position(cityCoordinates)
                    .title(city.getTitle()));

        }
    }
}
