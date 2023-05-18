package com.example.countdown;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;

public class MainActivity extends AppCompatActivity {
    private Button ClockBtn,StopwatchBtn;
    private ImageView image_info;
    private TextClock Clock,Clock_AmPm,Clock_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Action bar........................
        // Determine the current theme
        int currentTheme = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        // Set the appropriate theme based on the current configuration
        if (currentTheme == Configuration.UI_MODE_NIGHT_YES) {
            setTheme(R.style.AppTheme_Dark);
        } else {
            setTheme(R.style.AppTheme_Light);
        }

        // Hide the ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //.....................................

        setContentView(R.layout.activity_main);



        ClockBtn = (Button) findViewById(R.id.ClockBtn);
        StopwatchBtn = (Button) findViewById(R.id.StopwatchBtn);
        Clock = (TextClock) findViewById(R.id.Clock);
        Clock_AmPm = (TextClock) findViewById(R.id.Clock_AmPm);
        Clock_date = (TextClock) findViewById(R.id.Clock_date);
        image_info = (ImageView) findViewById(R.id.image_info);

        ClockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        StopwatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Stopwatch.class));

            }
        });
        image_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,about.class));
            }
        });
    }
}