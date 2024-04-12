package com.example.projectone.chat.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.projectone.Myapplication;
import com.example.projectone.R;
import com.example.projectone.chat.adapter.ChatItemAdapter;
import com.example.projectone.databinding.ActivityChatrecordBinding;
import com.example.projectone.manage.ChatRecordManage;
import com.example.projectone.table.ChatRecord;

import java.util.Date;

public class ChatRecordActivity extends AppCompatActivity {
    private ActivityChatrecordBinding binding;
    Intent intent;
    String userName;
    String friendName;
    ChatItemAdapter chatItemAdapter;
    String text;
    Long time;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatrecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();

        initData();

        initEvent();

    }

    private void initView() {
        intent = getIntent();
        userName = intent.getStringExtra("userName");
        friendName = intent.getStringExtra("friendName");

        binding.chatRecordToolbar.setTitle(friendName);

        //RecyclerView
        chatItemAdapter = new ChatItemAdapter(ChatRecordActivity.this,userName,friendName);
        binding.chatRecordRecyclerView.setAdapter(chatItemAdapter);
        binding.chatRecordRecyclerView.setLayoutManager(new
                LinearLayoutManager(ChatRecordActivity.this,LinearLayoutManager.VERTICAL,false));

    }

    private void initData() {
        chatItemAdapter.setData(ChatRecordManage.FindAll(userName,friendName));
    }

    private void initEvent() {
        //发送消息
        binding.chatRecordBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = binding.chatRecordText.getText().toString();
                if (text.length() == 0){
                    Toast.makeText(ChatRecordActivity.this, "消息不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    time = System.currentTimeMillis();
                    date = new Date(time);
                    ChatRecordManage.Add(userName,friendName,text,date);
                    Toast.makeText(ChatRecordActivity.this, "发送成功", Toast.LENGTH_SHORT).show();

                    //更新数据
                    chatItemAdapter.setData(ChatRecordManage.FindAll(userName,friendName));
                }
            }
        });
        //返回
        binding.chatRecordToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}