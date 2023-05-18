package com.example.countdown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;

public class Stopwatch extends AppCompatActivity {
    private Button ClockBtn, StartBtn, ResetBtn;
    private ImageView image_info;
    private TextView Time_txtView;
    private int seconds;
    private boolean Running, wasRunning;

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

        setContentView(R.layout.activity_stopwatch);



        ClockBtn = (Button) findViewById(R.id.ClockBtn);
        StartBtn = (Button) findViewById(R.id.StartBtn);
        ResetBtn = (Button) findViewById(R.id.ResetBtn);
        Time_txtView = (TextView) findViewById(R.id.Time_txtView);
        image_info = (ImageView) findViewById(R.id.image_info);



        ClockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Stopwatch.this, MainActivity.class));

            }
        });
        if (savedInstanceState != null){
            savedInstanceState.getInt("seconds");
            savedInstanceState.getBoolean("Running");
            savedInstanceState.getBoolean("wasRunning");
        }

        setTimer();

        StartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Running == false){
                    Running = true;
                    StartBtn.setText("Stop");
                }else {
                    Running = false;
                    StartBtn.setText("Start");
                }
            }
        });
        ResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Running = false;
                seconds = 0;
                StartBtn.setText("Start");
            }
        });
        image_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Stopwatch.this,about.class));
            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds",seconds);
        outState.putBoolean("Running",Running);
        outState.putBoolean("wasRunning",wasRunning);
    }

    public void setTimer() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hour = seconds / 3600;
                int minute = (seconds % 3600) / 60;
                int sec = seconds % 60;

                String time = String.format(Locale.getDefault(),
                        "%02d : %02d : %02d",
                        hour,minute,sec);
                Time_txtView.setText(time);

                if (Running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}