package com.example.myapplication.rabbit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.myapplication.R;

public class RabbitActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rabbit);

        FrameLayout frameLayout = findViewById(R.id.rabbitLayout);
        RabbitView rabbitView = new RabbitView(this);

        rabbitView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rabbitView.bitmapX = motionEvent.getX();
                rabbitView.bitmapY = motionEvent.getY();
                //刷新View的，必须是在UI线程中进行工作
                rabbitView.invalidate();
                return true;
            }
        });

        frameLayout.addView(rabbitView);

    }
}