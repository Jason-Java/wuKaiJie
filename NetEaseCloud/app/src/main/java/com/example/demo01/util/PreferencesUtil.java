package com.example.demo01.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *偏好设置工具类
 * 是否登录，是否显示引导界面，用户id
 */
public class PreferencesUtil {

    /**
     * 实例
     */
    private static PreferencesUtil instance;
    /**
     * 上下文
     */
    private final Context context;
    /**
     * 偏好设置文件名称
     */
    private static final String NAME = "ixuea_my_cloud_music";
    private final SharedPreferences preference;
    /**
     * 是否显示引导界面key
     */
    private static final String SHOW_GUIDE = "SHOW_GUIDE";

    /**
     * 构造方法
     */
    public PreferencesUtil(Context context) {
        //保存上下文
        this.context = context.getApplicationContext();

        //this.context = context
        //这样写会有内存泄漏
        //因为当前工具类不会马上释放
        //如果当前工具类引用了界面实例
        //当界面关闭后，因为界面对应在这里还有引用
        //所以会导致界面对象不会被释放

        //设置偏好设置
        preference = this.context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    /**
     * 获取偏好设置单例
     */
    public static PreferencesUtil getInstance(Context context) {
        if (instance == null){
            instance = new PreferencesUtil(context);
        }
        return instance;
    }

    /**
     * 是否显示引导界面
     */
    public boolean isShowGuide(){
        return preference.getBoolean(SHOW_GUIDE,true);
    }

    /**
     * 设置是否显示引导界面
     */
    public void setShowGuide(boolean value){
        preference.edit().putBoolean(SHOW_GUIDE,value).commit();
    }
}
