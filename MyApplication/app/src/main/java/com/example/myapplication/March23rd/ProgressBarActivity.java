package com.example.myapplication.March23rd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;

public class ProgressBarActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int mProgress = 0;
    private Handler mHandler;//消息处理对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,
//                WindowManager.LayoutParams.FLAGS_CHANGED);//设置全屏显示,没啥用

        progressBar = findViewById(R.id.progressBar);
        //Android不支持在主线程中更新UI组件
        //Handler是一套 Android 消息传递机制,主要用于线程间通信。
        //handler其实就是主线程在起了一个子线程，子线程运行并生成Message，Looper获取message并传递给Handler，Handler逐个获取子线程中的Message.
        mHandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 0x111){
                    progressBar.setProgress(mProgress);
                }else {
                    Toast.makeText(ProgressBarActivity.this, "耗时操作已完成", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);//进度条不显示
                }
            }
        };
        //模拟耗时操作，创建一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    mProgress = doWork();
                    Message message = new Message();
                    if (mProgress < 100 ){
                        message.what = 0x111;//自定义消息，用于区分
                        mHandler.sendMessage(message);
                    }else {
                        message.what = 0x110;
                        mHandler.sendMessage(message);
                        break;
                    }
                }
            }
            private int doWork(){
                mProgress += Math.random()*10;
                try {
                    Thread.sleep(200);//线程休眠200毫秒
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                return mProgress;
            }
        }).start();//开启线程
    }
}