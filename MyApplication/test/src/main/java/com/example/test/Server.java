package com.example.test;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    private static Boolean endFlag;
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static BufferedReader input;
    private static PrintWriter output;
    private static String TAG = "TCPaaaaaaa";

    @Override
    public void run() {
        try {
            endFlag = false;
            //设置服务器端口
            serverSocket = new ServerSocket(12345);

            while (!endFlag){
                //等待客户端连接
                socket = serverSocket.accept();
                Log.d(TAG, socket.getRemoteSocketAddress().toString());
                //InputStream 是字节输入流的所有类的超类
                //InputStreamReader 是字节流通向字符流的桥梁,
                //BufferedReader 由Reader类扩展而来，提供通用的缓冲方式文本读取，readLine读取一个文本行
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //注意第二个参数据为true将会自动flush，否则需要需要手动操作output.flush()
                //PrintWriter是BufferedWriter的优化
                output = new PrintWriter(socket.getOutputStream(),true);
                String msg = input.readLine();
                Log.d(TAG, "msg from CLient"+msg);
                output.println("msg received");//输出的内容
                if ("exit".equals(msg)){
                    endFlag = true;
                }
                input.close();
                output.println();
                socket.close();
            }
            serverSocket.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
