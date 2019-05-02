package com.example.assignment5;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Create the TimerTask
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //finish the activity
                finish();

                //Go to MainActivity
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        };

        //Timer Object
        Timer timer = new Timer();

        timer.schedule(task, 3000);

    }//End onCreate



}
