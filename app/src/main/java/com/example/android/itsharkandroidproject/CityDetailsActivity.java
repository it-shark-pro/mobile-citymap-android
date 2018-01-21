package com.example.android.itsharkandroidproject;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CityDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);

        final Bundle extras = getIntent().getExtras();
        final String cityTitle = extras.getString(MainActivity.EXTRA_CITY_TITLE, MainActivity.DEFAULT_CITY_TITLE);
        final String cityUrl = extras.getString(MainActivity.EXTRA_CITY_URL, MainActivity.DEFAULT_CITY_URL);

        final TextView titleTextView = findViewById(R.id.text_view_city_details_title);
        final ImageView photoImageView = findViewById(R.id.image_view_city_details_photo);

        titleTextView.setText(cityTitle);

        Glide.with(getApplicationContext())
                .load(cityUrl)
                .into(photoImageView);

        //Initializing back arrow in toolbar
        final ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setTitle(cityTitle);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
