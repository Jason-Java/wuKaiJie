package com.example.demo01;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.example.demo01.activity.BaseCommonActivity;

import es.dmoral.toasty.Toasty;

/**
 *
 */
public class ToastUtil {
    /**
     * 实现短时间错误toast
     * @param context
     * @param id
     */
    public static void errorShortToast(@NonNull Context context, @StringRes int id) {
        Toasty.error(context,id,Toasty.LENGTH_SHORT).show();
    }

    /**
     * 实现长时间错误toast
     * @param context
     * @param id
     */
    public static void errorLongToast(@NonNull Context context,@StringRes int id) {
        Toasty.error(context,id,Toasty.LENGTH_LONG).show();
    }

    /**
     * 实现短时间成功toast
     * @param context
     * @param id
     */
    public static void successShortToast(@NonNull Context context,@StringRes int id) {
        Toasty.success(context,id,Toasty.LENGTH_SHORT).show();
    }
}
