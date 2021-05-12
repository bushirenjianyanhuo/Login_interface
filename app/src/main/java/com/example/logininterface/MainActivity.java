package com.example.logininterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer timer = new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginInterface.class);
                startActivity(intent);
                MainActivity.this.fileList();
            }
        };
        timer.schedule(timerTask,1000*3);
    }
}