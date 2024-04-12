package com.example.projectone.my.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.projectone.Myapplication;
import com.example.projectone.R;
import com.example.projectone.databinding.ActivityMyprojectBinding;
import com.example.projectone.manage.CompetitionPersonManage;
import com.example.projectone.my.adapter.MyProjectAdapter;
import com.example.projectone.table.CompetitionPerson;

import java.util.List;

public class MyProjectActivity extends AppCompatActivity {

    private ActivityMyprojectBinding binding;

    Intent intent;
    String userName;
    MyProjectAdapter myProjectAdapter;
    List<CompetitionPerson> competitionPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyprojectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();

        initData();

        initEvent();
        ;
    }

    private void initView() {

        intent = getIntent();
        userName = intent.getStringExtra("name");

        myProjectAdapter = new MyProjectAdapter(R.layout.myprojectitem,MyProjectActivity.this,userName);
        binding.myProjectRecyclerView.setAdapter(myProjectAdapter);

        //设置布局
        binding.myProjectRecyclerView.setLayoutManager(new LinearLayoutManager(MyProjectActivity.this,
                LinearLayoutManager.VERTICAL,false));
        //色湖之分割线
        binding.myProjectRecyclerView.addItemDecoration(new DividerItemDecoration(MyProjectActivity.this,
                DividerItemDecoration.VERTICAL));


    }

    private void initData() {

        competitionPeople = CompetitionPersonManage.FindUserName(userName);
        myProjectAdapter.setData(competitionPeople);

    }

    private void initEvent() {
        //退出
        binding.myProjectToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}