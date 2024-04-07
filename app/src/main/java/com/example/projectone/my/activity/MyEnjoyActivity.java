package com.example.projectone.my.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.projectone.R;
import com.example.projectone.my.adapter.MyEnjoyAdapter;
import com.example.projectone.table.Enjoy;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MyEnjoyActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;

    Intent intent;
    String username;

    MyEnjoyAdapter myEnjoyAdapter;
    List<Enjoy> enjoys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_enjoy);

        initView();

        initData();

        initEvent();
    }

    private void initView() {
        toolbar = findViewById(R.id.myEnjoyToolbar);
        recyclerView = findViewById(R.id.myEnjoyll);

        intent = getIntent();
        username = intent.getStringExtra("name");

        myEnjoyAdapter = new MyEnjoyAdapter(MyEnjoyActivity.this,username);
        recyclerView.setAdapter(myEnjoyAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(MyEnjoyActivity.this,LinearLayoutManager.VERTICAL,false));

        //设置item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(MyEnjoyActivity.this,DividerItemDecoration.VERTICAL));

    }

    private void initData() {
        Connector.getDatabase();
        enjoys = LitePal.where("username = ?",username).order("date desc").find(Enjoy.class);
        myEnjoyAdapter.setData(enjoys);



    }

    private void initEvent() {
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}