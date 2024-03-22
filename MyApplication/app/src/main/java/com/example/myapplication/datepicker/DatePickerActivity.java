package com.example.myapplication.datepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.Calendar;

//视频里说APPCompatActivity显示不完全，Activity显示完全但是Activity无法选了其他日期后亮的地方变成你选的，工控机没有这个异常
public class DatePickerActivity extends Activity {
    public int year,month,day;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);

        datePicker = findViewById(R.id.datePicker);
        Calendar calendar = Calendar.getInstance();//获取日历
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        //初始化，可能是函数内部的变动会使外部的值变化
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthofyear, int dayofmonth) {//获取变动的日期
                DatePickerActivity.this.year = year;
                DatePickerActivity.this.month = monthofyear;
                DatePickerActivity.this.day = dayofmonth;
                show(year,monthofyear,dayofmonth);

            }
        });
    }
    public  void show(int year, int month, int day){
        String str = year + "年" + (month + 1) + "月" + day + "日";//java和Android的month从0开始
        Toast.makeText(DatePickerActivity.this, str, Toast.LENGTH_SHORT).show();

    }
}