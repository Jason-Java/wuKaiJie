package com.unite.okhttpdemo;

import android.app.Activity;
import android.app.Application;
import android.view.Display;

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
    @Override
    public void onCreate() {
        super.onCreate();
        screenAutoSize();
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
            AutoSizeConfig.getInstance().setDesignWidthInDp(640);
//        }
    }
}
