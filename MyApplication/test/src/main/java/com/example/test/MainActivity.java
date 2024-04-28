package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.test.databinding.ActivityMainBinding;

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

    private ActivityMainBinding binding;
    private String TAG = "MainActivity";
    private Socket socket;
    private static Socket clientSocket;
    String serverAddress;
    private ServerSocket serverSocket;
    private Message message;
    private BufferedReader input;
    private PrintWriter output;
    private static BufferedReader inputClient;
    private static PrintWriter outputClient;
    Chat chat;
    static List<Socket> onLineSocket = new ArrayList<>();


    public Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0){
                serverAddress = (String)msg.obj;
                binding.serverAddress.setText(serverAddress);
                Toast.makeText(MainActivity.this, "服务器创建成功", Toast.LENGTH_SHORT).show();
            }else if (msg.what == 1){
                Toast.makeText(MainActivity.this, "服务器创建失败", Toast.LENGTH_SHORT).show();
            }else if (msg.what == 2){
                //数据的显示
                chat = (Chat) msg.obj;
                binding.text.setText(chat.getName()+"  "+chat.getText());
            }else if(msg.what ==3){
                clientSocket = (Socket) msg.obj;
                Toast.makeText(MainActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
            }
        }
    };


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



    }

    private void initData() {

    }

    private void initEvent() {
        //点击创建服务器按钮
        binding.createServerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createServer();
            }
        });

        //连接服务器
        binding.connectBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            clientSocket= new Socket("192.168.2.45",12345);
                            message = new Message();
                            message.what = 3;
                            message.obj = clientSocket;
                            handler.sendMessage(message);
                            outputClient = new PrintWriter(clientSocket.getOutputStream(),true);
                            //接受数据，并显示
                            ClientReaderThread(clientSocket);

                        } catch (IOException e) {
                            Log.e(TAG, "客户端连接客户端失败");
                        }
                    }
                }).start();
            }
        });
        //发送内容
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clientSocket != null){
                    chat = new Chat();
                    chat.setName(clientSocket.getRemoteSocketAddress().toString());
                    chat.setText(binding.sendText.getText().toString());
                    sendServer(chat);
                }else {
                    Toast.makeText(MainActivity.this, "未创建连接", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void sendServer(Chat chat) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //发出消息
                    outputClient.println(chat.getName());
                    outputClient.println(chat.getText());
                    if (chat.getText().equals("exit")){
                        clientSocket.close();
                    }

                } catch (Exception e) {
                    Log.e(TAG, chat.getName()+chat.getText()+"传出消息失败");
                }
            }
        }).start();


    }



    //接受聊天信息
    public void ClientReaderThread(Socket socket){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while (true){
                        try {
                            chat = new Chat();
                            chat.setName(input.readLine());
                            chat.setText(input.readLine());
                            message = new Message();
                            message.what = 2;
                            message.obj = chat;
                            handler.sendMessage(message);
                        } catch (IOException e) {
                            Log.e(TAG, "客户端关闭" );
                            input.close();
                            socket.close();
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public  void createServer(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    //创建服务器
                    //第二参数是连接数量限制
                    serverSocket = new ServerSocket(12345,50,
                            Inet4Address.getByName("192.168.2.45"));
                    //传递信息
                    message = new Message();
                    message.what = 0;
                    message.obj = serverSocket.getLocalSocketAddress().toString();
                    handler.sendMessage(message);

                    while (true){
                        //等待客户端连接
                        socket = serverSocket.accept();
                        //加入在线的ip地址
                        onLineSocket.add(socket);
                        Log.d(TAG, socket.getRemoteSocketAddress().toString()+"已连接");
                        chat = new Chat();
                        chat.setName(socket.getRemoteSocketAddress().toString());
                        chat.setText("已上线");
                        sendMsgToAll(chat);

                        //数据的处理
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    //数据的传入
                                    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                                    //数次数据的传出
                                    while (true){
                                        try {
                                            //打包传入的数据
                                            chat = new Chat();
                                            chat.setName(input.readLine());
                                            chat.setText(input.readLine());
                                            sendMsgToAll(chat);
                                        } catch (IOException e) {
                                            Log.e(TAG, "连接断开" );
                                            onLineSocket.remove(socket);
                                            //退出，及断开连接
                                            chat = new Chat();
                                            chat.setName(socket.getRemoteSocketAddress().toString());
                                            chat.setText("已下线");
                                            sendServer(chat);
                                            input.close();
                                            socket.close();
                                            break;
                                        }
                                    }

                                } catch (IOException e) {
                                    Log.e(TAG, "数据传入服务器失败"+socket.getRemoteSocketAddress().toString());
                                }

                            }
                        }).start();

                    }
                } catch (IOException e) {//创建服务器失败
                    message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            }

        }).start();

    }

//    服务器接收的数据传入到每个连接的客户端中
    private void sendMsgToAll(Chat chat) throws IOException {
        Log.e(TAG, "sendMsgToAll: "+onLineSocket.size() );
        for (Socket socketClient : onLineSocket) {
            //数据的传出
            output = new PrintWriter(socketClient.getOutputStream(),true);
            output.println(chat.getName());
            output.println(chat.getText());
        }
    }
}