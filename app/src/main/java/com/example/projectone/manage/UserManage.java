package com.example.projectone.manage;

import android.util.Log;

import com.example.projectone.table.User;

import org.litepal.annotation.Column;

public class UserManage {
    private static User user;
    public static void Add(String username,String password,String telephone,String email){
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setEmail(email);

        try {
            user.saveThrows();
        }catch (Exception e){
            Log.e("SQLComment", "User异常数据保存到数据库失败"+"  "+e.getMessage() );
        }
    }


}
