package com.example.demo01;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.example.demo01.domain.Session;
import com.example.demo01.util.PreferencesUtil;
import com.example.demo01.util.ToastUtil;
import com.facebook.stetho.Stetho;

import es.dmoral.toasty.Toasty;

/**
 *全局Application
 */
public class AppContext extends Application {
    /**
     * 上下文
     */
    private static AppContext context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        //初始化toast工具类,1.5.2加这句话好像不需要
        Toasty.Config.getInstance().apply();

        //初始化toast工具类
        ToastUtil.init(getApplicationContext());

        //初始化stetho抓包
        //使用默认参数初始化
        Stetho.initializeWithDefaults(this);
    }

    //Dex,初始化MultiDex
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static AppContext getInstance(){
        return context;
    }

    /**
     * 用户登录了
     * @param sp
     * @param data
     */
    public void login(PreferencesUtil sp, Session data){
        //保存登录后的session
        sp.setSession(data.getSession());

        //保存用户id
        sp.setUserId(data.getUser());

        //初始化其他登录后才需要初始化的内容
        onLogin();

    }

    private void onLogin() {

    }
}
