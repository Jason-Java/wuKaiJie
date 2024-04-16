package com.example.server;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.server.databinding.ActivityMainBinding;

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
    ServerSocket serverSocket;
    Message message;
    Socket connectSocket;
    static List<Socket> onLineSocket = new ArrayList<>();
    static List<PrintWriter> serverOutput = new ArrayList<>();
    static PrintWriter clientOutput;
    PrintWriter connectOutput;
    Chat chat;
    BufferedReader connectIntput;
    String TAG = "Connect";
    static Socket clientSocket;
    static BufferedReader clientIntput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initEvent();
    }

    //处理数据
    public Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            //服务端是否创建
            if (msg.what == 0){
                Toast.makeText(MainActivity.this, "服务器创建成功", Toast.LENGTH_SHORT).show();
            }else if (msg.what == 1){
                Toast.makeText(MainActivity.this, "服务器创建失败", Toast.LENGTH_SHORT).show();
            }else if (msg.what == 2){
                chat = (Chat) msg.obj;
                Log.e(TAG, "handleMessage: "+chat.getName()+"  "+chat.getText() );
                binding.text.setText(chat.getName()+"  "+chat.getText());
                binding.sendText.setText("");
            }


        }
    };

    public void initEvent(){
//        创建服务器
        binding.createServerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //服务器IP地址确定
                        serverSocket = new ServerSocket(12345,50,
                                Inet4Address.getByName("192.168.2.149"));
                        message = new Message();
                        message.what = 0;
                        handler.sendMessage(message);

                        //服务器接收连接信息
                        while (true){
                            connectSocket = serverSocket.accept();

                            //输入流，接收客户端信息
                            new Thread(new serverIn(connectSocket)).start();//重点
                        }
                    } catch (IOException e) {
                        message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                }
            }).start();
            }
        });

        //创建客户端
        binding.connectBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clientSocket == null){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                clientSocket = new Socket("192.168.2.149",12345);

                                Log.e(TAG, clientSocket.getLocalSocketAddress().toString()+"连接成功");
                                clientOutput = new PrintWriter(clientSocket.getOutputStream(),true);
                                //接收服务器发来的信息
                                receive(clientSocket);

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

        //客户端发送消息
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

//                            clientOutput.println(clientSocket.getLocalSocketAddress().toString());
//                            clientOutput.println(binding.sendText.getText().toString());
                            clientOutput.print("nananan   nananan  nananna  nanan");
                            clientOutput.flush();
                            Log.e(TAG, clientSocket.getLocalSocketAddress().toString()+binding.sendText.getText().toString());
                        } catch (Exception e) {
                            Log.e(TAG, "消息发送给服务器失败");

                        }
                    }
                }).start();
            }
        });
    }

    //接收服务器发来的信息
    public void receive(Socket socket){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clientIntput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    Log.e(TAG, "receive: 成功" );
                    Log.e(TAG, "run: "+clientSocket.getLocalSocketAddress().toString());

                    while (true){
                        try {
                            chat = new Chat();
                            String name = clientIntput.readLine();
                            String text = clientIntput.readLine();
                            chat.setName(name);
                            chat.setText(text);
//                            Log.e(TAG, "receive:"+clientIntput.readLine()+clientIntput.readLine());
                            Log.e(TAG, socket.getLocalSocketAddress().toString()+"接收消息成功"+chat.getText() );
                            message = new Message();
                            message.what = 2;
                            message.obj = chat;
                            handler.sendMessage(message);
                        } catch (IOException e) {
                            Log.e(TAG, socket.getLocalSocketAddress().toString()+"接收消息失败" );
                            break;
                        }
                    }
                } catch (IOException e) {
                    Log.e(TAG, "receive: 失败" );
                }
            }
        }).start();

    }

    //发给所有客户端
    public void SendAll(Chat chat) {
        for (int i = 0; i < onLineSocket.size(); i++) {
            connectOutput = serverOutput.get(i);
            connectOutput.println(chat.getName());
            connectOutput.println(chat.getText());
            Log.e(TAG, "SendAll: "+chat.getName()+chat.getText()+onLineSocket.size() );
        }
    }

//    //接收客户端信息
//    public void serverIn(Socket socket) {
//        try {
//            onLineSocket.add(socket);
//            Log.e(TAG, "服务器同意"+socket.getRemoteSocketAddress().toString());
//
//            //输出流
//            connectOutput = new PrintWriter(socket.getOutputStream(),true);
//            serverOutput.add(connectOutput);
//            message = new Message();
//
//            //连接成功
//            chat = new Chat();
//            chat.setName(socket.getRemoteSocketAddress().toString());
//            chat.setText("上线了");
//            SendAll(chat);
//
//            connectIntput  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            //连接不断，接收信息
//            while (true){
//                try {
//                    chat = new Chat();
//                    chat.setName(connectIntput.readLine());
//                    chat.setText(connectIntput.readLine());
//                    Log.e(TAG, "serverIn: "+chat.getName()+chat.getText() );
//                    SendAll(chat);
//                } catch (IOException e) {
//                    Log.e(TAG, socket.getRemoteSocketAddress().toString()+"断开连接");
//                    chat = new Chat();
//                    chat.setName(socket.getRemoteSocketAddress().toString());
//                    chat.setText("下线了");
//                    SendAll(chat);
//                    //清除占用资源
//                    int delete = onLineSocket.indexOf(socket);
//                    serverOutput.remove(delete);
//                    onLineSocket.remove(socket);
//                    connectIntput.close();
//                    socket.close();
//                    break;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    //接收客户端信息
    class serverIn implements Runnable {
        Socket socket;

        public serverIn(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                onLineSocket.add(socket);
                Log.e(TAG, "服务器同意" + socket.getRemoteSocketAddress().toString());

                //输出流
                connectOutput = new PrintWriter(socket.getOutputStream(), true);
                serverOutput.add(connectOutput);
                message = new Message();

                //连接成功
                chat = new Chat();
                chat.setName(socket.getRemoteSocketAddress().toString());
                chat.setText("上线了");
                SendAll(chat);

                connectIntput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                //连接不断，接收信息
                while (true) {
                    try {
//                        chat = new Chat();
//                        chat.setName(connectIntput.readLine());
//                        chat.setText(connectIntput.readLine());
//                        Log.e(TAG, "serverIn: " + chat.getName() + chat.getText());

                        Log.e(TAG, "serverIn: " + connectIntput.readLine());
                        SendAll(chat);
                    } catch (IOException e) {
                        Log.e(TAG, socket.getRemoteSocketAddress().toString() + "断开连接");
                        chat = new Chat();
                        chat.setName(socket.getRemoteSocketAddress().toString());
                        chat.setText("下线了");
                        SendAll(chat);
                        //清除占用资源
                        int delete = onLineSocket.indexOf(socket);
                        serverOutput.remove(delete);
                        onLineSocket.remove(socket);
                        connectIntput.close();
                        socket.close();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}