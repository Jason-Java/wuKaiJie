package com.unite.okhttpdemo.util;

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
    private static final String NAME = "NAME";
    private final SharedPreferences preference;



    //用户名user 的key
    private static final String USER = "user";
    //token_type 的key
    private static final String TOKEN_TYPE= "token_type";
    //token 的key
    private static final String TOKEN= "token";

    //部门id 的key
    private static final String BMID= "BMID";

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

    //设置用户名
    public void setUser(String user) {
        putString(USER,user);
    }
    //获取用户名
    public String getUser() {
        return preference.getString(USER,null);
    }

    //设置token_type
    public void setTokenType(String token_type) {
        putString(TOKEN_TYPE,token_type);
    }
    //获取token_type
    public String getTokenType() {
        return preference.getString(TOKEN_TYPE,null);
    }

    //设置token
    public void setToken(String token) {
        putString(TOKEN,token);
    }
    //获取token
    public String getToken() {
        return preference.getString(TOKEN,null);
    }


    //设置部门id
    public void setBMID(int id) {
        putInt(BMID,id);
    }
    //获取部门id
    public int getBMID() {
        return preference.getInt(BMID,0);
    }

    //辅助方法
    private void putBoolean(String key, boolean value) {
        preference.edit().putBoolean(key,value).commit();
    }

    private void putString(String key, String value) {
        preference.edit().putString(key,value).commit();
    }

    private void putInt(String key, int value) {
        preference.edit().putInt(key,value).commit();
    }



}
