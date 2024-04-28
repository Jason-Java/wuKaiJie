package com.example.testthree;

import android.os.Message;
import android.util.Log;

import androidx.appcompat.view.menu.MenuBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 *
 */
public class Server extends Thread{

    private ServerSocket serverSocket;
    private Message message;
    private Socket connectSocket;
    private List<Socket> onLineSocket;
    private String TAG = MainActivity.TAG;
    private OutputStream connectOutput;


    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(12345);

            message = new Message();
            message.what = 0;
            MainActivity.handler.sendMessage(message);

            while (true) {
                connectSocket = serverSocket.accept();
                onLineSocket.add(connectSocket);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            Log.e(TAG, "服务器同意" + connectSocket.getRemoteSocketAddress().toString());

                            //输出流
                            connectOutput = connectSocket.getOutputStream();
                            serverOutput.add(connectOutput);

                            String name = connectSocket.getRemoteSocketAddress().toString();
                            String text = "上线了";
                            SendAll(name, text);


//                            连接不断，接收信息
                            while (true) {
                                try {
                                    BufferedReader input = new BufferedReader(new InputStreamReader(connectSocket.getInputStream()));
                                    String readLine;
                                    while ((readLine = input.readLine()) != null) {
                                        Log.e(TAG, "serverIn: " + readLine);
                                        String[] lines = readLine.split("&&", 2);
                                        name = lines[0];
                                        text = lines[1];
                                        SendAll(name, text);
                                    }



                                    /*connectInput = new DataInputStream(connectSocket.getInputStream());
                                    String readLine;
                                    while (true) {
                                        readLine = connectInput.readUTF();
                                        Log.e(TAG, "serverIn: " + readLine);
                                        String[] lines = readLine.split("&&", 2);
                                        name = lines[0];
                                        text = lines[1];
                                        Log.e(TAG, "serverIn: " + name + text);
                                        SendAll(name, text);


                                    }*/
                                } catch (IOException e) {
                                    Log.e(TAG, connectSocket.getRemoteSocketAddress().toString() + "断开连接");
                                    name = connectSocket.getRemoteSocketAddress().toString();
                                    text = "下线了";
                                    SendAll(name, text);
                                    //清除占用资源
                                    int delete = onLineSocket.indexOf(connectSocket);
                                    serverOutput.remove(delete);
                                    onLineSocket.remove(connectSocket);
                                    connectInput.close();
                                    connectSocket.close();
                                    break;
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).

                        start();
            }

        } catch (IOException e) {

            message = new Message();
            message.what = 1;
            MainActivity.handler.sendMessage(message);
            e.printStackTrace();

        }
    }
}
