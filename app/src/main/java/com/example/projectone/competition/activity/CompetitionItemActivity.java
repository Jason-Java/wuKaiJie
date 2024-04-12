package com.example.projectone.competition.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.projectone.Myapplication;
import com.example.projectone.R;
import com.example.projectone.competition.adapter.CompetitionItemAdapter;
import com.example.projectone.databinding.ActivityCompetitionitemBinding;
import com.example.projectone.manage.CompetitionIntelManage;
import com.example.projectone.manage.CompetitionProjectManage;
import com.example.projectone.table.CompetitionIntel;

import java.util.List;

public class CompetitionItemActivity extends AppCompatActivity {

    private ActivityCompetitionitemBinding binding;
    Intent intent;
    String competitionName;
    String userName;
    CompetitionItemAdapter competitionItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCompetitionitemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();

        initData();

        initEvent();
    }

    private void initView() {
        intent = getIntent();
        userName = intent.getStringExtra("name");
        competitionName = intent.getStringExtra("competitionName");

        binding.competitionItemTv.setText(competitionName);

        competitionItemAdapter = new CompetitionItemAdapter(R.layout.competitionone,
                CompetitionItemActivity.this,userName);
        binding.comprtitionItemRecyclerView.setAdapter(competitionItemAdapter);

        //设置布局
        binding.comprtitionItemRecyclerView.setLayoutManager(new LinearLayoutManager
                (CompetitionItemActivity.this, LinearLayoutManager.VERTICAL,false));
        //色湖之分割线
        binding.comprtitionItemRecyclerView.addItemDecoration(new DividerItemDecoration
                (CompetitionItemActivity.this, DividerItemDecoration.VERTICAL));

    }

    private void initData() {

        competitionItemAdapter.setData(CompetitionProjectManage.FindAllProject(competitionName));

    }

    private void initEvent() {
        binding.competitionItemToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}