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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private ServerSocket serverSocket;
    private Message message;
    private Socket connectSocket;
    private static List<Socket> onLineSocket = new ArrayList<>();
    String TAG = "Connect";
    private PrintWriter connectOutput;
    private static List<PrintWriter> serverOutput =new ArrayList<>();
    private String name;
    private String text;
    private BufferedReader connectIntput;
    private static Socket clientSocket;
    private static PrintWriter clientOutput;
    private static BufferedReader clientIntput;
    private List<String> news ;


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
                Toast.makeText(MainActivity.this, "服务器创建成功", Toast.LENGTH_SHORT).show();
            }else if (msg.what == 1){
                Toast.makeText(MainActivity.this, "服务器创建失败", Toast.LENGTH_SHORT).show();
            }else if (msg.what == 2){
                news = new ArrayList<>();
                news = (List<String>) msg.obj;
                Log.e(TAG, "handleMessage: "+news);
                binding.text.setText(news.get(0)+" "+news.get(1));
                binding.sendtext.setText("");
            }
        }
    };

    private void ininEvent() {
        //创建按服务器
        binding.createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            serverSocket = new ServerSocket(12345,50,
                                    Inet4Address.getByName("192.168.2.149"));

                            message = new Message();
                            message.what = 0;
                            handler.sendMessage(message);

                            while (true){
                                connectSocket = serverSocket.accept();

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {

                                        try {
                                            onLineSocket.add(connectSocket);
                                            Log.e(TAG, "服务器同意" + connectSocket.getRemoteSocketAddress().toString());

                                            //输出流
                                            connectOutput = new PrintWriter(connectSocket.getOutputStream());
                                            serverOutput.add(connectOutput);


                                            name = connectSocket.getRemoteSocketAddress().toString();
                                            text = "上线了";
                                            SendAll(name,text);

                                            connectIntput = new BufferedReader(new InputStreamReader(connectSocket.getInputStream()));

                                            //连接不断，接收信息
                                            while (true) {
                                                try {
                                                    name = connectIntput.readLine();
                                                    text = connectIntput.readLine();

                                                    Log.e(TAG, "serverIn: " + name+text);
                                                    SendAll(name,text);
                                                } catch (IOException e) {
                                                    Log.e(TAG, connectSocket.getRemoteSocketAddress().toString() + "断开连接");
                                                    name = connectSocket.getRemoteSocketAddress().toString();
                                                    text = "下线了";
                                                    SendAll(name,text);
                                                    //清除占用资源
                                                    int delete = onLineSocket.indexOf(connectSocket);
                                                    serverOutput.remove(delete);
                                                    onLineSocket.remove(connectSocket);
                                                    connectIntput.close();
                                                    connectSocket.close();
                                                    break;
                                                }
                                            }
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();
                            }

                        } catch (IOException e) {

                            message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                            e.printStackTrace();

                        }
                    }
                }).start();
            }
        });

        //创建客户端
        binding.connectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clientSocket == null){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                clientSocket = new Socket("192.168.2.149",12345);

                                Log.e(TAG, clientSocket.getLocalSocketAddress().toString()+"连接成功");
                                clientOutput = new PrintWriter(clientSocket.getOutputStream());
                                //接收服务器发来的信息
                                receive();

                            } catch (IOException e) {
                                Log.e(TAG, clientSocket.getLocalSocketAddress().toString()+"连接失败");
                            }
                        }
                    }).start();
                }else {
                    Toast.makeText(MainActivity.this, "已建立", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //发送消息
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            name = clientSocket.getLocalSocketAddress().toString();
                            text = binding.sendtext.getText().toString();
                            clientOutput.print(name);
                            clientOutput.flush();
                            clientOutput.print(text);
                            clientOutput.flush();
                            Log.e(TAG, "发送"+name+text);
                        } catch (Exception e) {
                            Log.e(TAG, "消息发送给服务器失败");

                        }
                    }
                }).start();
            }
        });
    }

    //发给所有客户端
    public void SendAll(String name,String text) {
        for (int i = 0; i < onLineSocket.size(); i++) {
            connectOutput = serverOutput.get(i);
            connectOutput.print(name);
            connectOutput.print(text);
            connectOutput.flush();
            Log.e(TAG, "SendAll: "+name+text+onLineSocket.size() );
        }
    }


    //接收服务器发来的信息
    public void receive(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clientIntput = new BufferedReader(new InputStreamReader(MainActivity.clientSocket.getInputStream()));
                    Log.e(TAG, "客户端输入流创建成功 "+clientSocket.getLocalSocketAddress().toString());


                    message = new Message();
                    message.what = 2;
                    news = new ArrayList<>();
                    news.add(name);
                    news.add(text);
                    message.obj = news;
                    handler.sendMessage(message);




                } catch (IOException e) {
                    Log.e(TAG, "接收消息失败" );
                }
            }
        }).start();

    }
}
