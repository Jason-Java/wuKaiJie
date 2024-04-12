package com.example.projectone.manage;

import android.util.Log;

import com.example.projectone.table.ChatRecord;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatRecordManage {
    private static List<ChatRecord> chatRecords = new ArrayList<>();
    private static ChatRecord chatRecord;

    public static List<ChatRecord> FindAll(String userName,String friendName){
        return LitePal.where("username = ? and friendName = ? or username = ? and friendName = ?",
                userName,friendName,friendName,userName).order("date asc").find(ChatRecord.class);
    }

    public static void Add(String userName, String friendName, String text, Date date){
        chatRecord = new ChatRecord();
        chatRecord.setUserName(userName);
        chatRecord.setFriendName(friendName);
        chatRecord.setText(text);
        chatRecord.setDate(date);

        try{
            chatRecord.saveThrows();
        }catch (Exception e){
            Log.e("SQLComment", "ChatRecord异常数据保存到数据库失败"+"  "+e.getMessage() );
        }
    }


}
