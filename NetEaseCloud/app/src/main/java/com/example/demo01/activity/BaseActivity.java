package com.example.demo01.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

/**
 *所有Activity父类
 */
public class BaseActivity extends AppCompatActivity{


    /**
     * 找控件
     */
    protected void initViews(){

    }

    /**
     * 设置数据
     */
    protected void initDatum(){

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
}