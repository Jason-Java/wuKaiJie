package com.example.projectone.my.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.projectone.DetailActivity;
import com.example.projectone.manage.BuyCollectionManage;
import com.example.projectone.manage.BuyManage;
import com.example.projectone.databinding.ActivityMybuycollectionBinding;
import com.example.projectone.my.adapter.BaseRecyclerViewAdapter;
import com.example.projectone.my.adapter.MyBuyCollectionAdapter;
import com.example.projectone.table.Buy;
import com.example.projectone.table.BuyCollection;

import java.util.List;

public class MyBuyCollectionActivity extends AppCompatActivity {

    private ActivityMybuycollectionBinding binding;

    Intent intent;
    String username;

    List<BuyCollection> buyCollections;
    BuyCollection buyCollection;
    List<Buy> buys;
    Buy buy;
    MyBuyCollectionAdapter myBuyCollectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMybuycollectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();

        initData();

        initEvent();
    }

    private void initView() {
        intent = getIntent();
        username = intent.getStringExtra("name");

        //设置布局
        binding.myBuyCollectionRecyclerView.setLayoutManager(new LinearLayoutManager(MyBuyCollectionActivity.this,LinearLayoutManager.VERTICAL,false));
        //色湖之分割线
        binding.myBuyCollectionRecyclerView.addItemDecoration(new DividerItemDecoration(MyBuyCollectionActivity.this,DividerItemDecoration.VERTICAL));

        myBuyCollectionAdapter = new MyBuyCollectionAdapter(this,username);
        binding.myBuyCollectionRecyclerView.setAdapter(myBuyCollectionAdapter);



    }

    private void initData() {

        buyCollections = BuyCollectionManage.FindCollection(username);
        myBuyCollectionAdapter.setData(buyCollections);

    }

    private void initEvent() {
        binding.myBuyCollectionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //点击item进入详情页
        myBuyCollectionAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                buyCollection = buyCollections.get(position);
                buys = BuyManage.FindBuyCollection(buyCollection.getBuyName(),buyCollection.getBuyShop());
                buy = buys.get(0);
                intent = new Intent(MyBuyCollectionActivity.this, DetailActivity.class);
                intent.putExtra("url",buy.getBuyUrl());
                startActivity(intent);
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        });

    }
}