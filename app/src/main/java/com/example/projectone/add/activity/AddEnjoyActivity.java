package com.example.projectone.add.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectone.R;
import com.example.projectone.table.Enjoy;
import com.example.projectone.table.Like;

import org.litepal.tablemanager.Connector;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEnjoyActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText addEnjoyEt;
    Button addEnjoyBt;

    Intent intent;
    String username;

    Enjoy enjoy;
    String text;
    Long time;
    Date date;
    String dateString;
    private SimpleDateFormat format;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_enjoy);

        initView();

        initData();

        initEvent();
    }

    private void initView() {
        toolbar = findViewById(R.id.addEnjoyToolbar);
        addEnjoyEt = findViewById(R.id.addEnjoyEt);
        addEnjoyBt = findViewById(R.id.addEnjoyBt);

    }

    private void initData() {
        intent = getIntent();
        username = intent.getStringExtra("name");

        Connector.getDatabase();


    }

    private void initEvent() {
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addEnjoyBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = addEnjoyEt.getText().toString();
                if (text.length() == 0){
                    Toast.makeText(AddEnjoyActivity.this, "说说为空", Toast.LENGTH_SHORT).show();
                }else {
                    enjoy = new Enjoy();
                    enjoy.setUsername(username);

                    enjoy .setText(text);

                    time = System.currentTimeMillis();
                    date = new Date(time);
                    format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    try {
                        dateString = format.format(date);
                        enjoy.setDate(dateString);
                    }catch (Exception e){
                        Log.e("looknews", "时间格式转换失败"+e);
                        dateString = "无";
                        enjoy.setDate(dateString);
                    }

                    enjoy.setType(1);
                    enjoy.setLikes(0);

                    enjoy.save();


                    Toast.makeText(AddEnjoyActivity.this, "分享成功", Toast.LENGTH_SHORT).show();

                    finish();
                }

            }
        });

    }
}