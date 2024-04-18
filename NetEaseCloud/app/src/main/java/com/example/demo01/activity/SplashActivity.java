package com.example.demo01.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.example.demo01.R;

/**
 * 启动界面
 */
public class SplashActivity extends BaseCommonActivity {

    /**
     * 下一步常量
     */
    private static final int MESSAGE_NEXT = 100;
    private static final String TAG = "SplashActivity";
    /**
     * 默认延时时间
     */
    private static final long DEAFULT_DELAY_TIME = 3000;
    /**
     * 创建Handler
     * 这样创建有内存泄漏
     */
    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case MESSAGE_NEXT:
                    next();
                    break;
            }
        }
    };

    /**
     * 下一步
     */
    private void next() {
        Log.d(TAG, "next");

//        Intent intent = new Intent(this,GuideActivity.class);
//
//        startActivity(intent);
//        //关闭当前界面
//        finish();

//        if (sp.isShowGuide()){
//            //使用重构后的方法
        startActivityAfterFinishThis(GuideActivity.class);
//        }else {
//            //跳转到登陆注册界面
//            startActivityAfterFinishThis(LoginOrRegisterActivity.class);
//        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        float xdpi = getResources().getDisplayMetrics().xdpi;
//        float ydpi = getResources().getDisplayMetrics().ydpi;
//        Log.e("tag", "-----xdpi=" + xdpi);
//        Log.e("tag", "-----ydpi=" + ydpi);
//        //工控机xdpi=213，ydpi=213，hdpi,手机391xxhdpi

        //设置界面全屏
        fullScreen();

        //延时3秒
        //在企业通常有很多逻辑处理
        //所以延时时间最好是用3-消耗的时间,消耗时间的操作比如检查版本是否需要更新，这个检查放在这个handler前面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(MESSAGE_NEXT);
            }
        }, DEAFULT_DELAY_TIME);
    }

    @Override
    protected void initDatum() {
        super.initDatum();
//        SharedPreferences preferences = getSharedPreferences("ixuea",MODE_PRIVATE);
//
//        preferences.edit().putString("username","aaaaa").commit();
//
//        String username = preferences.getString("username",null);
//
//        Log.d(TAG, "initDatum: "+username);
//
//        preferences.edit().remove("username").commit();
//
//        username = preferences.getString("username",null);
//
//        Log.d(TAG, "initDatum: "+username);
    }

}