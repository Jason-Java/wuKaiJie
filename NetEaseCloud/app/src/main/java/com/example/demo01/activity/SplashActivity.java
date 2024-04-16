package com.example.demo01.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.demo01.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        float xdpi = getResources().getDisplayMetrics().xdpi;
//        float ydpi = getResources().getDisplayMetrics().ydpi;
//        Log.e("tag", "-----xdpi=" + xdpi);
//        Log.e("tag", "-----ydpi=" + ydpi);
//        //工控机xdpi=213，ydpi=213，hdpi,手机391xxhdpi
    }
}