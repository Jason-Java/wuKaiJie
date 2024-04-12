package com.example.projectone.add.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.projectone.Myapplication;
import com.example.projectone.R;
import com.example.projectone.add.adapter.NewFriendAdapter;
import com.example.projectone.table.AddFriend;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class NewFriendActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView newfriendRecycleview;
    NewFriendAdapter newFriendAdapter;

    List<AddFriend> data;
    Intent intent;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfriend);

        initData();

        initView();
        //这两不加不显示
        newfriendRecycleview.setLayoutManager(new LinearLayoutManager(NewFriendActivity.this,LinearLayoutManager.VERTICAL,false));

        //设置item的分割线
        newfriendRecycleview.addItemDecoration(new DividerItemDecoration(NewFriendActivity.this,DividerItemDecoration.VERTICAL));

        initEvent();

    }

    private void initView() {
        toolbar = findViewById(R.id.newfriendToolbar);
        newfriendRecycleview = findViewById(R.id.newfriendRecycleview);

        //环境、数据、自己的姓名
        newFriendAdapter = new NewFriendAdapter(NewFriendActivity.this,data,name);
        newfriendRecycleview.setAdapter(newFriendAdapter);

    }

    private void initData() {
        intent = getIntent();
        name = intent.getStringExtra("name");

        Connector.getDatabase();
        data = LitePal.where("username = ? or addname = ?",name,name).order("date desc").find(AddFriend.class);

    }

    private void initEvent() {

        //退出
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}