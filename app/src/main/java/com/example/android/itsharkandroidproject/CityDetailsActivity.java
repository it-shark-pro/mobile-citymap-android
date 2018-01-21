package com.example.android.itsharkandroidproject;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CityDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);

        final Bundle extras = getIntent().getExtras();
        final String cityTitle = extras.getString(MainActivity.EXTRA_CITY_TITLE, MainActivity.DEFAULT_CITY_TITLE);
        final String cityDescription = extras.getString(MainActivity.EXTRA_CITY_DESCRIPTION, MainActivity.DEFAULT_CITY_DESCRIPTION);

        final TextView titleTextView = findViewById(R.id.text_view_city_details_title);
        final TextView descriptionText = findViewById(R.id.text_view_city_details_description);

        titleTextView.setText(cityTitle);
        descriptionText.setText(cityDescription);

        //Initializing back arrow in toolbar
        final ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
