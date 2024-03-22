package com.example.myapplication.timepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.R;

public class TimePickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timepicker);

        TimePicker timePicker = findViewById(R.id.timePicker);
        timePicker.is24HourView();//设置24小时制，默认是12小时,没变化、、、
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                Toast.makeText(TimePickerActivity.this, hour+"点"+minute+"分", Toast.LENGTH_SHORT).show();
            }
        });
    }
}