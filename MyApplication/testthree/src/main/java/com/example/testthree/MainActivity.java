package com.example.testthree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.example.testthree.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static ActivityMainBinding binding;
    static List<String> info = new ArrayList<>();
    public static String TAG = "aaa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initEvent();
    }


    //回调
    static Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                Log.e(TAG, "服务器创建成功");
            }else if (msg.what == 1){
                Log.e(TAG, "服务器创建失败");
            }else if (msg.what == 2){
                info = new ArrayList<>();
                info = (List<String>) msg.obj;
                Log.e(TAG, "handleMessage: "+info);
                binding.text.setText(info.get(0)+" "+info.get(1));
                binding.sendtext.setText("");
            }
        }
    };

    private void initEvent() {
        binding.createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}