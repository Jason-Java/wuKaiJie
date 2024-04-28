package com.example.chat.TCP;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;

    private OutputStream os;

    private DataOutputStream dos;

    //发送请求

    public Client(String ip ,int port) {

        try {

            socket = new Socket(ip,port);
            os = socket.getOutputStream();
            dos = new DataOutputStream(os);
            Log.i("TCP", "isBound" + socket.isBound() + "\nisConnected" + socket.isConnected());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
