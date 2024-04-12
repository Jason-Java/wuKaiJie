package com.example.projectone.manage;

import android.util.Log;

import com.example.projectone.table.CompetitionPerson;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompetitionPersonManage {
    private static CompetitionPerson competitionPerson;
    private static List<CompetitionPerson> competitionPeople = new ArrayList<>();
    private static SimpleDateFormat format;
    private static Date addTimeDate;

    public static List<CompetitionPerson> FindCompetitionNameItemSexAge(String competitionName,String competitionItem,
    String personSex,int minAge,int maxAge){
        if (LitePal.where("competitionName = ? and competitionItem = ? and personSex = ? and personAge >= ?" +
                "and personAge <= ?",competitionName,competitionItem,personSex,minAge+"",maxAge+"").find(CompetitionPerson.class)
                .size() == 0){
            return competitionPeople = new ArrayList<>();
        }else {
            return LitePal.where("competitionName = ? and competitionItem = ? and personSex = ? and personAge >= ?" +
                    "and personAge <= ?",competitionName,competitionItem,personSex,minAge+"",maxAge+"").find(CompetitionPerson.class);
        }
    }

    public static List<CompetitionPerson> FindPersonCard(String personCard){
        return LitePal.where("personCard = ?",personCard).find(CompetitionPerson.class);
    }

    public static List<CompetitionPerson> FindUserName(String userName){
        //addtime为null比有时间大
        return LitePal.where("userName = ?",userName).order("addTime asc").find(CompetitionPerson.class);
    }

    public static void Add(String competitionName,String competitionItem,String userName,
                           String personName,String personCard,String personSex,int personAge){
        competitionPerson = new CompetitionPerson();
        competitionPerson.setCompetitionName(competitionName);
        competitionPerson.setCompetitionItem(competitionItem);
        competitionPerson.setUserName(userName);
        competitionPerson.setPersonName(personName);
        competitionPerson.setPersonCard(personCard);
        competitionPerson.setPersonSex(personSex);
        competitionPerson.setPersonAge(personAge);

        try {
            competitionPerson.saveThrows();
        }catch (Exception e){
            Log.e("SQLComment", "CompetitionPerson异常数据保存到数据库失败"+"  "+e.getMessage() );
        }
    }


    public static void Add(String competitionName,String competitionItem,String userName,
                           String personName,String personCard,String personSex,int personAge,
                           String time,int ranking,String addTime){
        competitionPerson = new CompetitionPerson();
        competitionPerson.setCompetitionName(competitionName);
        competitionPerson.setCompetitionItem(competitionItem);
        competitionPerson.setUserName(userName);
        competitionPerson.setPersonName(personName);
        competitionPerson.setPersonCard(personCard);
        competitionPerson.setPersonSex(personSex);
        competitionPerson.setPersonAge(personAge);
        competitionPerson.setTime(time);
        competitionPerson.setRanking(ranking);
        format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            addTimeDate = format.parse(addTime);
        }catch (Exception e){
            Log.e("SQLComment", "String转换成时间错误"+"  "+e.getMessage() );
        }
        competitionPerson.setAddTime(addTimeDate);

        try {
            competitionPerson.saveThrows();
        }catch (Exception e){
            Log.e("SQLComment", "CompetitionPerson异常数据保存到数据库失败"+"  "+e.getMessage() );
        }
    }

}
