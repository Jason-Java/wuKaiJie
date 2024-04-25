package com.unite.okhttpdemo.util;

import android.util.DisplayMetrics;
import android.view.View;

import com.unite.okhttpdemo.BuildConfig;

import me.jessyan.autosize.AutoSizeConfig;

/**
 *
 */
public class Constant {

    //屏幕自适应的宽度和高度，dp
    public static final int DesignWidthInDp = AutoSizeConfig.getInstance().getDesignWidthInDp();
    public static final int DesignHeightInDp = 360;

    //login，popupwindow，宽度和高度,四舍五入
    public static final int loginPopupWindowWidth = 141;
    public static final int loginPopupWindowHeight = 97;

    //login，popupwindow 坐标,四舍五入
    public static final int loginPopupWindow_x = 410;
    public static final int loginPopupWindow_y = 167;

    //19及以上版本
    //View.SYSTEM_UI_FLAG_LAYOUT_STABLE：全屏显示时保证尺寸不变
    //SYSTEM_UI_FLAG_HIDE_NAVIGATION:隐藏导航栏
    //SYSTEM_UI_FLAG_IMMERSIVE_STICKY:从状态栏下拉会半透明悬浮显示一会状态栏和导航栏
    //View.SYSTEM_UI_FLAG_FULLSCREEN:全屏
    public static int options = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
            View.SYSTEM_UI_FLAG_FULLSCREEN |
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

    //资源API地址
    public static final String ENDPOINT = BuildConfig.ENDPOINT;

    public static boolean isDebug = BuildConfig.DEBUG;
}
