package com.example.testtwo;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

    @Override
    public void run() {
        try {
            clientSocket = new Socket("192.168.2.149", 12345);

            Log.e(TAG, clientSocket.getLocalSocketAddress().toString() + "连接成功");
            clientOutput = clientSocket.getOutputStream();
            //接收服务器发来的信息
            receive();

        } catch (IOException e) {
            Log.e(TAG, clientSocket.getLocalSocketAddress().toString() + "连接失败");
        }
    }

    //接收服务器发来的信息
    public void receive() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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

                        if (text.equals("exit") && name.equals(clientSocket.getLocalSocketAddress().toString())){
                            input.close();
                            clientSocket.close();
                        }
                    }
                } catch (IOException e) {
                    Log.e(TAG, "接收消息失败");
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
                } catch (Exception e) {
                    Log.e(TAG, "消息发送给服务器失败");

                }
            }
        }).start();

    }

    //button关闭客户端及连接断开

}
