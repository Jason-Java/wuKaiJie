package com.example.test;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread{

    private static Socket socket;
    private static OutputStream os;
    private static PrintWriter output;
    private static BufferedReader input;
    static String TAG = "TCPaaaaaaa";

    @Override
    public void run() {
        try {
            socket = new Socket("192.168.2.204",12345);
            //输出流
            os = socket.getOutputStream();
            output = new PrintWriter(os,true);
            output.println("exit");
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = input.readLine();
            Log.d(TAG, "msg from Server"+msg);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
