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

    /**
     * 试剂柜权限
     */
    //试剂柜
    public static final String REGEANBOKX = "regeantbox";
    //入库
    public static final String ACTION_RK = "action_rk";
    //查询
    public static final String ACTION_CX = "action_cx";
    //领用
    public static final String ACTION_LY = "action_ly";
    //归还
    public static final String ACTION_GH = "action_gh";
    //统计
    public static final String ACTION_TJ = "action_tj";
    //设置
    public static final String ACTION_SZ = "action_sz";
    //废弃/空瓶标记
    public static final String ACTION_BJ = "action_bj";
    //送处
    public static final String ACTION_SC = "action_sc";
    //用户管理
    public static final String ACTION_USER_MANAGE = "action_user_manage";
    //快速开锁
    public static final String ACTION_OPEN_LOCK = "action_open_lock";
    //新增入库
    public static final String ACTION_NEW_RK = "action_new_rk";
    //查看日志
    public static final String ACTION_LOOK_LOG = "action_look_log";
    //退货
    public static final String ACTION_TH = "action_th";
    //调剂试剂
    public static final String ACTION_ADJUST = "action_adjust";
}
