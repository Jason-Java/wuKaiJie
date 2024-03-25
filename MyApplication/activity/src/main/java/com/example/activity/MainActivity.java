package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    //运行状态
    //oncreat→onstart→onresume
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Activivty生命周期", "onCreate方法被调用");
    }

    @Override
    protected void onStart() {
        Log.i("Activivty生命周期", "onStart方法被调用");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i("Activivty生命周期", "onResume方法被调用");
        super.onResume();
    }

    //暂停状态
    @Override
    protected void onPause() {
        Log.i("Activivty生命周期", "onPause方法被调用");
        super.onPause();
    }

    //停止状态

    @Override
    protected void onStop() {
        Log.i("Activivty生命周期", "onStop方法被调用");
        super.onStop();
    }

    //没关闭处于后台时要调用时onrestart→start→onresume
    @Override
    protected void onRestart() {
        Log.i("Activivty生命周期", "onRestart方法被调用");
        super.onRestart();
    }

    //销毁状态
    @Override
    protected void onDestroy() {
        Log.i("Activivty生命周期", "onDestroy方法被调用");
        super.onDestroy();
    }

    //按返回键时（退出）onpause→onstop→ondestroy
    //oncreate（null）刷新页面
}