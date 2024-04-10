package com.example.projectone.manage;

import android.util.Log;

import com.example.projectone.table.CompetitionIntel;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompetitionIntelManage {
    private static CompetitionIntel competitionIntel;
    private static Date competitionStartTimeDate;
    private static Date competitionEndTimeDate;
    private static Date applicationStartTimeDate;
    private static Date applicationEndTimeDate;
    private static SimpleDateFormat format;

    List<CompetitionIntel> competitionIntels = new ArrayList<>();

    //查找所有赛事
    public static List<CompetitionIntel> FindAll(){
        return LitePal.order("competitionStartTime desc").find(CompetitionIntel.class);
    }

    public static CompetitionIntel FindCompetition(String competitionName){
        return LitePal.where("competitionName = ?",competitionName).find(CompetitionIntel.class).get(0);
    }

    public static void Add(int competitionImage,String competitionName,String competitionAddress,
                           String competitionStartTime,String competitionEndTime,
                           String applicationStartTime,String applicationEndTime,int competitionMaxNum){
        format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            competitionStartTimeDate = format.parse(competitionStartTime);
            competitionEndTimeDate = format.parse(competitionEndTime);
            applicationStartTimeDate = format.parse(applicationStartTime);
            applicationEndTimeDate = format.parse(applicationEndTime);
        }catch (Exception e){
            Log.e("SQLComment", "String转换成时间错误"+"  "+e.getMessage() );
        }

        competitionIntel = new CompetitionIntel();
        competitionIntel.setCompetitionImage(competitionImage);
        competitionIntel.setCompetitionName(competitionName);
        competitionIntel.setCompetitionAddress(competitionAddress);
        competitionIntel.setCompetitionStartTime(competitionStartTimeDate);
        competitionIntel.setCompetitionEndTime(competitionEndTimeDate);
        competitionIntel.setApplicationStartTime(applicationStartTimeDate);
        competitionIntel.setApplicationEndTime(applicationEndTimeDate);
        competitionIntel.setCompetitionMaxNum(competitionMaxNum);

        try {
            competitionIntel.saveThrows();
        }catch (Exception e){
            Log.e("SQLComment", "CompetitionIntel异常数据保存到数据库失败"+"  "+e.getMessage() );
        }
    }
}
