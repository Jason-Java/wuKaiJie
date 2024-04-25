package com.unite.okhttpdemo.util;

import android.content.Context;

import androidx.annotation.StringRes;

import es.dmoral.toasty.Toasty;

/**
 *
 */
public class ToastUtil {
    /**
     * 上下文
     */
    private static Context context;
    //初始化方法
    public static void init(Context context) {
        ToastUtil.context = context;
    }
    /**
     * 实现短时间错误toast
     * @param id
     */
    public static void errorShortToast( @StringRes int id) {
        Toasty.error(context,id,Toasty.LENGTH_SHORT).show();
    }

    public static void errorShortToast(String message) {
        Toasty.error(context,message,Toasty.LENGTH_SHORT).show();
    }

    /**
     * 实现长时间错误toast
     * @param id
     */
    public static void errorLongToast(@StringRes int id) {
        Toasty.error(context,id,Toasty.LENGTH_LONG).show();
    }

    /**
     * 实现短时间成功toast
     * @param id
     */
    public static void successShortToast(@StringRes int id) {
        Toasty.success(context,id,Toasty.LENGTH_SHORT).show();
    }

}
