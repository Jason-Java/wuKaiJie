package com.unite.okhttpdemo.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.unite.okhttpdemo.MyApplication;
import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.base.activity.BaseActivity;

/**
 *
 */
public class PopupWindowUtil {

    //悬浮窗的模板
    //View popupView = LayoutInflater.from(getMainActivity()).inflate(R.layout.popupwindow_login, null);


    public static PopupWindow getPopupWindow(Activity activity, View popupView, View rootView,
                                             int width, int height, int x, int y) {

        PopupWindow popupWindow = new PopupWindow(popupView);

        popupWindow.setWidth((int) getWindowWidthPx(activity) / Constant.DesignWidthInDp * width);
        popupWindow.setHeight((int) getWindowHeightPx(activity) / Constant.DesignHeightInDp * height);

        //设置是否获取焦点
        popupWindow.setFocusable(true);
        //设置可以触摸弹出框以外的区域
        popupWindow.setOutsideTouchable(true);

        //popupwidow放在rootview里
        popupWindow.showAtLocation(rootView, Gravity.LEFT | Gravity.TOP
                , ((int) getWindowWidthPx(activity) / Constant.DesignWidthInDp * x)
                , ((int) getWindowHeightPx(activity) / Constant.DesignHeightInDp * y));

        return popupWindow;


    }


    //获取设备的宽高像素值,px
    public static int getWindowWidthPx(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getWindowHeightPx(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    //设置遮罩层
    public static void setMask(Activity activity, float f) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = f;
        activity.getWindow().setAttributes(lp);
    }


}
