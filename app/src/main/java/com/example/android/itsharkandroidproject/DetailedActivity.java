package com.example.android.itsharkandroidproject;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        final Bundle extras = getIntent().getExtras();
        final String itemName = extras.getString(MainActivity.EXTRA_ITEM_NAME, MainActivity.DEFAULT_ITEM_NAME);
        final String itemDescription = extras.getString(MainActivity.EXTRA_ITEM_DESCRIPTION, MainActivity.DEFAULT_ITEM_DESCRIPTION);

        final TextView nameTextView = findViewById(R.id.text_view_detailed_name);
        final TextView descriptionText = findViewById(R.id.text_view_detailed_description);

        nameTextView.setText(itemName);
        descriptionText.setText(itemDescription);

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
