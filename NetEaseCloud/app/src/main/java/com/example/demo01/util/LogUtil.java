package com.example.demo01.util;

import android.os.Build;
import android.util.Log;

import com.example.demo01.BuildConfig;

/**
 *日志工具类
 * 对Android日志API做一个简答的封装
 */
public class LogUtil {
    /**
     * 是否是调试模式
     */
    public static boolean isDebug = BuildConfig.DEBUG;

    /**
     * 调试级别日志
     */
    public static void d(String tag,String value){
        if(isDebug){
            Log.d(tag, value);
        }
    }

    public static void w(String tag, String value) {
        if(isDebug){
            Log.w(tag, value);
        }
    }
}
