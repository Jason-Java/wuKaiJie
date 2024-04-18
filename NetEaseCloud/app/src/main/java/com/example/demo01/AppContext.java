package com.example.demo01;

import android.app.Application;

import es.dmoral.toasty.Toasty;

/**
 *全局Application
 */
public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化toast工具类,1.5.2加这句话好像不需要
        Toasty.Config.getInstance().apply();
    }
}
