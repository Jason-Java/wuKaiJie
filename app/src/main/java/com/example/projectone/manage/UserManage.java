package com.example.projectone.manage;

import android.util.Log;

import com.example.projectone.table.User;

import org.litepal.LitePal;
import org.litepal.annotation.Column;

import java.util.List;

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

    public static String FindHeadPicture(String userName){
        return LitePal.where("username = ?",userName).find(User.class).get(0).getHeadpicture();
    }

    public static List<User> FindUser(String username, String telephone){
        return LitePal.where("username = ? and telephone = ?",username,telephone).find(User.class);
    }


}
