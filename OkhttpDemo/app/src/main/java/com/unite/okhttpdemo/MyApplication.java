package com.unite.okhttpdemo;

import android.app.Activity;
import android.app.Application;
import android.view.Display;

import com.unite.okhttpdemo.util.ToastUtil;

import es.dmoral.toasty.Toasty;
import me.jessyan.autosize.AutoSizeConfig;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2024年04月24日
 */
public class MyApplication  extends Application {
    /**
     * 上下文
     */
    private static MyApplication context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        //屏幕自适应
        screenAutoSize();

        //初始化toast工具类,1.5.2加这句话好像不需要
        Toasty.Config.getInstance().apply();

        //初始化toast工具类
        ToastUtil.init(getApplicationContext());


    }

    public static MyApplication getInstance(){
        return context;
    }

    // 屏幕自适应
    private void screenAutoSize() {
//        int widthPixels = DensityUtil.getDisplay((Activity) context).x;
//        int heightPixels = DensityUtil.getDisplay((Activity) context).y;

//        if (widthPixels < heightPixels) {
//            System.out.println("竖屏");
//            AutoSizeConfig.getInstance().setDesignWidthInDp(360);
//        } else {
//            System.out.println("横屏");
            //当应用程序在不同的设备上运行时，这些设置可能会帮助确保用户界面元素保持合适的大小和比例。
            AutoSizeConfig.getInstance().setDesignWidthInDp(640);

//        }
    }




}
