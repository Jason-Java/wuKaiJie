package com.example.demo01.util;

import static com.example.demo01.util.Constant.REGEX_EMAIL;
import static com.example.demo01.util.Constant.REGEX_PHONE;

/**
 *字符串工具类
 */
public class StringUtil {
    /**
     * 是否符合手机号格式
     */
    public static boolean isPhone(String value){
        return value.matches(REGEX_PHONE);
    }

    /**
     * 是否是邮箱
     */
    public static boolean isEmail(String value){
        return value.matches(REGEX_EMAIL);
    }

    /**
     * 是否符合密码格式
     */
    public static boolean isPassword(String value){
        return value.length() >= 6 && value.length() <= 15;
    }
}
