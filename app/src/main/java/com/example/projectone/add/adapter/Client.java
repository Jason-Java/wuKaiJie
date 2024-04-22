package com.example.projectone.add.adapter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.projectone.add.activity.NetworkActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户端
 */
public class Client extends Thread {
    private Socket clientSocket;
    String TAG = "Connect";
    OutputStream clientOutput;
    private Message message;
    private List<String> news;
    BufferedReader input;
    private String connectName;

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private Handler activityHandler;

    public void setActivityHandler(Handler activityHandler) {
        this.activityHandler = activityHandler;
    }

    public Client(String connectName) {
        this.connectName = connectName;
    }

    @Override
    public void run() {
        Log.e(TAG, "clientrun: "+connectName);
        try {
            clientSocket = new Socket(connectName, 12345);

            Log.e(TAG, clientSocket.getLocalSocketAddress().toString() + "连接成功");

            clientOutput = clientSocket.getOutputStream();
            message = new Message();
            message.what = 4;
            news = new ArrayList<>();
            String name = clientSocket.getLocalSocketAddress().toString();
            String text = "上线了";
            news.add(name);
            news.add(text);
            message.obj = news;
            activityHandler.sendMessage(message);
            //接收服务器发来的信息
            receive();

        } catch (IOException e) {
            message = new Message();
            message.what = 3;
            activityHandler.sendMessage(message);
            Log.e(TAG, connectName + "连接失败");
            e.printStackTrace();
        }
    }

    //接收服务器发来的信息
    public void receive() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String readLine;
                    while ((readLine = input.readLine()) != null) {
                        Log.e(TAG, "ClientIn: " + readLine);
                        String[] lines = readLine.split("&&", 2);
                        String name = lines[0];
                        String text = lines[1];
                        Log.e(TAG, "接收服务端信息 " + name + text);

                        message = new Message();
                        message.what = 2;
                        news = new ArrayList<>();
                        news.add(name);
                        news.add(text);
                        message.obj = news;
                        activityHandler.sendMessage(message);
                    }
                } catch (IOException e) {
                    Log.e(TAG, "接收消息失败或者客户端关闭");
                }
            }
        }).start();

    }


    public void sendServer(String sendtext){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clientOutput = clientSocket.getOutputStream();
                    String name = clientSocket.getLocalSocketAddress().toString();
                    String text = sendtext;
                    clientOutput.write((name + "&&" + text+"\n").getBytes());
                    clientOutput.flush();
                    Log.e(TAG, "发送"+name+text);

                    message = new Message();
                    message.what = 7;
                    news = new ArrayList<>();
                    news.add(name);
                    news.add(text);
                    message.obj = news;
                    activityHandler.sendMessage(message);
                } catch (Exception e) {
                    Log.e(TAG, "消息发送给服务器失败");

                }
            }
        }).start();

    }

    //button关闭客户端及连接断开
    public  void back(){

        if (clientSocket != null && !clientSocket.isClosed()) {
            try {

                clientOutput.close();
                input.close();
                clientSocket.close();
                clientSocket = null;
                Log.e(TAG, "back: 关闭客户端成功");
                message = new Message();
                message.what = 5;
                activityHandler.sendMessage(message);
                //中断线程
                interrupt();

                // 可以在这里添加断开连接后的清理工作
            } catch (IOException e) {
                // 处理关闭连接时可能发生的异常
                e.printStackTrace();
                message = new Message();
                message.what = 6;
                activityHandler.sendMessage(message);
                Log.e(TAG, "back: 关闭客户端失败");
            }
        }
    }
}
