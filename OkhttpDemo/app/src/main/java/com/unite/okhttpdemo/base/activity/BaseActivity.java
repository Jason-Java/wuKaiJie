package com.unite.okhttpdemo.base.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.unite.okhttpdemo.util.Constant;
import com.unite.okhttpdemo.util.PreferencesUtil;

import me.jessyan.autosize.AutoSizeConfig;

/**
 *所有Activity父类
 */
public class BaseActivity extends AppCompatActivity{


    /**
     * 找控件
     */
    protected PreferencesUtil sp;
    protected void initViews(){

        //设置偏好
        sp = PreferencesUtil.getInstance(getMainActivity());
    }

    /**
     * 设置数据
     */
    protected void initDatum() {


    }

    /**
     * 设置监听器
     */
    protected void initListeners(){

    }

    /**
     * 在onCreate方法后面调用
     */
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initViews();
        initDatum();
        initListeners();
    }

    //用于处理窗口焦点变化的事件。当Activity的窗口获取或失去焦点时，这个方法会被调用。
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        fullScreen();
//    }

    //一直横屏显示
    public void landscape() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * 全屏
     */
    protected void fullScreen(){
        //获取decorView
        View decorView = getWindow().getDecorView();
        //判断版本
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19){
            //11~18
            decorView.setSystemUiVisibility(View.GONE);
        }else if (Build.VERSION.SDK_INT >= 19){
            //设置到控件
            decorView.setSystemUiVisibility(Constant.options);
        }
    }

    /**
     * 启动界面
     */
    protected void startActivity(Class<?> clazz){
        Intent intent = new Intent(getMainActivity(),clazz);
        startActivity(intent);
    }

    /**
     * 启动界面并关闭当前界面
     */
    protected void startActivityAfterFinishThis(Class<?> clazz){
        startActivity(clazz);
        finish();
    }

    /**
     * 获取界面方法
     */
    protected BaseActivity getMainActivity(){
        return this;
    }


    //获取设备的宽高像素值,px
    public int getWindowWidthPx(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public int getWindowHeightPx(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }


    //设置遮罩层
    public void setMask(float f) {
        WindowManager.LayoutParams lp =getWindow().getAttributes();
        lp.alpha = f;
        getWindow().setAttributes(lp);
    }
}
