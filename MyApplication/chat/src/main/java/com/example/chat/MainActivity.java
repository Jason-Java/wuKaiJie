package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.chat.TCP.Client;
import com.example.chat.TCP.Server;
import com.example.chat.adapter.AddChatAdapter;
import com.example.chat.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public static List<Chat> chats = new ArrayList<>();
    Chat chat;
    AddChatAdapter addChatAdapter;
    String name;
    String text;
    Server server;
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();

        initData();

        initEvent();

    }

    private void initView() {

        addChatAdapter = new AddChatAdapter(MainActivity.this,name);
        binding.recclerView.setAdapter(addChatAdapter);
        //这两不加不显示
        binding.recclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.VERTICAL,false));

        server = new Server(8888);
        client = new Client("192.168.2.204",8888);


    }

    private void initData() {

        addChatAdapter.setData(chats);

    }

    private void initEvent() {


        //点击发送
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = binding.text.getText().toString();
                chat = new Chat();
                chat.setName(name);
                chat.setText(text);

                binding.text.setText("");
            }
        });

    }
}