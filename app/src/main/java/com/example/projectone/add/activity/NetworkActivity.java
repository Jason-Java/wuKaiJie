package com.example.projectone.add.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.projectone.add.adapter.AddChatAdapter;
import com.example.projectone.add.adapter.Client;
import com.example.projectone.add.adapter.Server;
import com.example.projectone.chat.activity.ChatRecordActivity;
import com.example.projectone.chat.adapter.ChatItemAdapter;
import com.example.projectone.databinding.ActivityNetworkBinding;
import com.example.projectone.table.Network;

import org.litepal.LitePal;

import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NetworkActivity extends AppCompatActivity {
    ActivityNetworkBinding binding;
    static String TAG = "Connect";

    private Network network;
    private OutputStream clientOutput;
    static Client client;
    AddChatAdapter adapter;
    Intent intent;
    String address;
    String name;

    Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                Log.e(TAG, "服务器创建成功");
                Toast.makeText(NetworkActivity.this, "服务器创建成功", Toast.LENGTH_SHORT).show();
            }else if (msg.what == 1){
                Log.e(TAG, "服务器创建失败");
                Toast.makeText(NetworkActivity.this, "服务器创建失败", Toast.LENGTH_SHORT).show();
            }else if (msg.what == 2){
                @SuppressWarnings("unchecked")
                List<String> info = (List<String>) msg.obj;

                Log.e(TAG, "handleMessage: "+info);
                network = new Network();
                network.setNews(0);
                network.setHost(info.get(0));
                network.setText(info.get(1));
                network.setName(name);

                long time = System.currentTimeMillis();
                Date date = new Date(time);
                network.setDate(date);

                try{
                    network.saveThrows();
                }catch (Exception e){
                    Log.e("SQLComment", "network异常数据保存到数据库失败"+"  "+e.getMessage() );
                }

                adapter.setData(LitePal.where("name = ?",name).order("date asc").find(Network.class));

//                binding.text.setText(info.get(0)+" "+info.get(1));
                binding.sendtext.setText("");
            }else if (msg.what == 3){
                client = null;
                Toast.makeText(NetworkActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
            }else if (msg.what == 4){
                @SuppressWarnings("unchecked")
                List<String> info = (List<String>) msg.obj;

                Log.e(TAG, "handleMessage: "+info);
                network = new Network();
                network.setNews(1);
                network.setHost(info.get(0));
                network.setText(info.get(1));
                network.setName(name);

                long time = System.currentTimeMillis();
                Date date = new Date(time);
                network.setDate(date);

                try{
                    network.saveThrows();
                }catch (Exception e){
                    Log.e("SQLComment", "network异常数据保存到数据库失败"+"  "+e.getMessage() );
                }
                adapter.setData(LitePal.where("name = ?",name).order("date asc").find(Network.class));

                Toast.makeText(NetworkActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
            }else if (msg.what == 5){
                client = null;
                Toast.makeText(NetworkActivity.this, "关闭客户端成功", Toast.LENGTH_SHORT).show();
            }else if (msg.what == 6){
                Toast.makeText(NetworkActivity.this, "关闭客户端失败", Toast.LENGTH_SHORT).show();
            }else if (msg.what == 7){
                @SuppressWarnings("unchecked")
                List<String> info = (List<String>) msg.obj;

                Log.e(TAG, "handleMessage: "+info);
                network = new Network();
                network.setNews(1);
                network.setHost(info.get(0));
                network.setText(info.get(1));
                network.setName(name);

                Date date = new Date();
                network.setDate(date);

                try{
                    network.saveThrows();
                }catch (Exception e){
                    Log.e("SQLComment", "network异常数据保存到数据库失败"+"  "+e.getMessage() );
                }
                adapter.setData(LitePal.where("name = ?",name).order("date asc").find(Network.class));

//                binding.text.setText(info.get(0)+" "+info.get(1));
                binding.sendtext.setText("");
            }
        }
    };
    private Server server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNetworkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();

        initData();

        initEvent();
    }

    private void initView() {

        intent = getIntent();
        address = intent.getStringExtra("address");
        name = intent.getStringExtra("name");

        //RecyclerView
        adapter = new AddChatAdapter(NetworkActivity.this);
        binding.networkBackRecyclerView.setAdapter(adapter);
        binding.networkBackRecyclerView.setLayoutManager(new
                LinearLayoutManager(NetworkActivity.this,LinearLayoutManager.VERTICAL,false));

    }

    private void initData() {
        adapter.setData(LitePal.where("name = ?",name).find(Network.class));
    }

    private void initEvent() {

        //创建按服务器
        binding.createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String servername = binding.servername.getText().toString();
                if (servername.length() == 0){
                    Toast.makeText(NetworkActivity.this, "未输入服务器域名", Toast.LENGTH_SHORT).show();
                }else{
                    server = new Server(servername);
                    server.setActivityHandler(handler);
                    server.start();
                }

            }
        });

        //创建客户端
        binding.connectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String connectname = binding.serverConnectName.getText().toString();
                if (client == null){
                    client = new Client(connectname);
                    client.setActivityHandler(handler);
                    client.start();
                }else {
                    Toast.makeText(NetworkActivity.this, "已建立", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //客户端断开连接
        binding.backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (client != null){
                    client.back();
                }else {
                    Toast.makeText(NetworkActivity.this, "null", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //发送消息
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (client != null){
                    client.sendServer(binding.sendtext.getText().toString());
                }else {
                    Toast.makeText(NetworkActivity.this, "未连接", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //back
        binding.networkBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (client != null){
                    client.back();
                    server.delete();
                }else {
                    Toast.makeText(NetworkActivity.this, "null", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

    }
}