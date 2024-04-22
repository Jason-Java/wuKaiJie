package com.example.projectone.add.adapter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务器
 */
public class Server extends Thread {

    private ServerSocket serverSocket;
    private Message message;
    private Socket connectSocket;
    List<Socket> onLineSocket = new ArrayList<>();
    String TAG = "Connect";
    List<OutputStream> serverOutput = new ArrayList<>();
    String servername;

    private Handler activityHandler;

    public void setActivityHandler(Handler activityHandler) {
        this.activityHandler = activityHandler;
    }

    public Server(String servername) {
        this.servername = servername;
    }

    @Override
    public void run() {
        Log.e(TAG, "serverrun: "+servername);
        try {
            serverSocket = new ServerSocket(12345,50, Inet4Address.getByName(servername));

            message = new Message();
            message.what = 0;
            activityHandler.sendMessage(message);

            while (true) {
                connectSocket = serverSocket.accept();
                onLineSocket.add(connectSocket);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            Log.e(TAG, "服务器同意" + connectSocket.getRemoteSocketAddress().toString());

                            String name = connectSocket.getRemoteSocketAddress().toString();
                            String text = "上线了";
                            SendAll(name, text);


//                           连接不断，接收信息
                            BufferedReader connectInput= new BufferedReader(new InputStreamReader(connectSocket.getInputStream()));;
                            while (true) {
                                try {
                                    String readLine;
                                    if((readLine = connectInput.readLine()) != null) {
                                        Log.e(TAG, "serverIn: " + readLine);
                                        String[] lines = readLine.split("&&", 2);
                                        name = lines[0];
                                        text = lines[1];
                                        SendAll(name, text);

                                        //判断是否断联
                                        byte[] buffer = new byte[1024];
                                        int bytesRead = connectSocket.getInputStream().read(buffer);
                                        if (bytesRead == -1){
                                            Log.e(TAG, connectSocket.getRemoteSocketAddress().toString() + "断开连接");
                                            name = connectSocket.getRemoteSocketAddress().toString();
                                            text = "下线了";
                                            SendAll(name, text);
                                            connectInput.close();
                                            onLineSocket.remove(connectSocket);
                                            connectSocket.close();
                                        }
                                    }
                                } catch (IOException e) {
//                                    Log.e(TAG, connectSocket.getRemoteSocketAddress().toString() + "断开连接");
//                                    name = connectSocket.getRemoteSocketAddress().toString();
//                                    text = "下线了";
//                                    SendAll(name, text);
//                                    onLineSocket.remove(connectSocket);
//                                    connectInput.close();
//                                    connectSocket.close();
//                                    break;
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
            activityHandler.sendMessage(message);
            e.printStackTrace();

        }
    }

    //发给所有客户端
    public void SendAll(String name, String text) {
        for (int i = 0; i < onLineSocket.size(); i++) {
            try {
                if (!name.equals(onLineSocket.get(i).getRemoteSocketAddress().toString())){
                    OutputStream outputStream = onLineSocket.get(i).getOutputStream();
                    outputStream.write((name + "&&" + text + "\n").getBytes());
                    outputStream.flush();
                    outputStream.close();
                    Log.e(TAG, "SendAll: " + onLineSocket.get(i).getLocalSocketAddress().toString() + name + text + onLineSocket.size());
                }
            } catch (IOException e) {
                Log.e(TAG, "SendAll: 失败");
            }
        }
    }

    //停止服务器
    public void delete(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        interrupt();
    }


}
