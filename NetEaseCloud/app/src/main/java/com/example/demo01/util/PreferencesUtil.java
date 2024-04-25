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
    //用户登录session 的key
    private static final String SESSION = "SESSION";
    //用户登录user_id 的key
    private static final String USER_ID = "USER_ID";

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
        putBoolean(SHOW_GUIDE,value);
    }

    /**
     * 保存的登录的session
     */
    public void setSession(String value) {
        putString(SESSION,value);
    }

    /**
     * 获取登录的session
     */
    public String getSession(){
        return preference.getString(SESSION,null);
    }

    /**
     * 保存登录的用户id
     */
    public void setUserId(String value) {
        putString(USER_ID,value);
    }

    /**
     * 获取登录的用户id
     */
    public String getUserId(){
        return preference.getString(USER_ID,null);
    }

    //辅助方法
    private void putBoolean(String key, boolean value) {
        preference.edit().putBoolean(key,value).commit();
    }

    private void putString(String key, String value) {
        preference.edit().putString(key,value).commit();
    }

}
