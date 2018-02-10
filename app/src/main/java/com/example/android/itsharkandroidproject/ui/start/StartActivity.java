package com.example.android.itsharkandroidproject.ui.start;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.android.itsharkandroidproject.R;
import com.example.android.itsharkandroidproject.ui.main.MainActivity;

public class StartActivity extends AppCompatActivity {

    private final static int DELAY = 1000;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent intent = new Intent(StartActivity.this, MainActivity.class);
                StartActivity.this.startActivity(intent);
                StartActivity.this.finish();

            }
        }, DELAY);
    }
}