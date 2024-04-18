package com.example.demo01.fragment;

import android.content.Intent;

import androidx.viewbinding.ViewBinding;

import com.example.demo01.activity.BaseCommonActivity;
import com.example.demo01.util.PreferencesUtil;

/**
 *
 */
public abstract class BaseCommonViewBindingFragment<T extends ViewBinding> extends BaseViewBindingFragment<T>{
    protected PreferencesUtil sp;

    @Override
    protected void initDatum() {
        super.initDatum();

        //初始化偏好设置工具类
        sp = PreferencesUtil.getInstance(getMainActivity());
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
        getMainActivity().finish();
    }

    protected BaseCommonActivity getMainActivity(){
        return (BaseCommonActivity) getActivity();
    }
}
