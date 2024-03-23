package com.example.myapplication.March23rd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.example.myapplication.R;

public class TabHostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabhost);

        TabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();//初始化
        //添加标签页
        LayoutInflater layoutInflater =LayoutInflater.from(this);
        layoutInflater.inflate(R.layout.activity_chronometer,tabHost.getTabContentView());
        layoutInflater.inflate(R.layout.activity_datepicker,tabHost.getTabContentView());
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("计时器").setContent(R.id.tab2));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("日历").setContent(R.id.tab1));
    }
}