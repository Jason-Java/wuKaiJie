package com.example.myapplication.chronometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;

import com.example.myapplication.R;

public class ChronometerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer);

        Chronometer ch = findViewById(R.id.ch);
        ch.setBase(SystemClock.elapsedRealtime());//设置计时器起始时间,SystemClock.elapsedRealtime()获取当前的时间
        ch.setFormat("%s");//设置显示时间格式，以后看https://blog.csdn.net/ligen52/article/details/54135915

        ch.start();

        ch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (SystemClock.elapsedRealtime() - ch.getBase() >= 6000){
                    ch.stop();
                }
            }
        });

    }
}