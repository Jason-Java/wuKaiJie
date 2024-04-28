package com.example.chat.TCP;

import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;

    //建立服务端
    public Server(int port) {
        try {

            serverSocket = new ServerSocket(port);
            Log.i("TCP", "isBound=" + serverSocket.isBound() + "\nisClosed=" + serverSocket.isClosed());

//            socket = serverSocket.accept();
//            Log.i("TCP", socket.getRemoteSocketAddress().toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
