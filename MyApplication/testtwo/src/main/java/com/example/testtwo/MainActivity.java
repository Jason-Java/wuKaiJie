package com.example.testtwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.testtwo.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    static String TAG = "Connect";
    private  List<String> info ;
    private OutputStream clientOutput;
    Client client;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ininEvent();
    }

    Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                Log.e(TAG, "服务器创建成功");
            }else if (msg.what == 1){
                Log.e(TAG, "服务器创建失败");
            }else if (msg.what == 2){
                info = new ArrayList<>();
                info = (List<String>) msg.obj;
                Log.e(TAG, "handleMessage: "+info);
                binding.text.setText(info.get(0)+" "+info.get(1));
                binding.sendtext.setText("");
            }
        }
    };

    private void ininEvent() {
        //创建按服务器
        binding.createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Server server = new Server();
                server.setActivityHandler(handler);
                server.start();
            }
        });

        //创建客户端
        binding.connectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (client == null){
                    client = new Client();
                    client.setActivityHandler(handler);
                    client.start();

                }else {
                    Toast.makeText(MainActivity.this, "已建立", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //发送消息
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                client.sendServer(binding.sendtext.getText().toString());
            }
        });
    }



}
