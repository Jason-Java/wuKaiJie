package com.example.projectone.manage;

import android.util.Log;

import com.example.projectone.table.CompetitionProject;
import com.example.projectone.table.Person;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CompetitionProjectManage {
    private static List<CompetitionProject> competitionProjects;
    private static CompetitionProject competitionProject;
    private static SimpleDateFormat format;
    private static Date itemTimeDate;
    private static List<Person> people;
    private static Person person;

    //判断是否达到年龄和性别要求
    public static Boolean CheckAge(String username,String competitionName){
        people = PersonManage.FindUserName(username);
        person = people.get(0);
        int age = person.getPersonAge();
        String personSex = person.getPersonSex();
        competitionProjects = FindAgeSex(competitionName,age,personSex);
        if (competitionProjects.size() == 0){
            return true;//年龄不符合
        }else {
            return false;
        }

    }

    //找这个赛事符合年龄和性别的项目
    public static List<CompetitionProject> FindAgeSex(String competitionName,int personAge,String personSex){
        return LitePal.where("itemMinAge <= ? and itemMaxAge >= ? and competitionName = ? and itemSex = ?",
                        personAge+"",personAge+"",competitionName,personSex).order("itemTime asc").
                find(CompetitionProject.class);
    }


    public static void Add(String competitionName,String competitionItem,String itemTime,
                           String itemSex,int itemMinAge,int itemMaxAge){
        competitionProject = new CompetitionProject();
        competitionProject.setCompetitionName(competitionName);
        competitionProject.setCompetitionItem(competitionItem);
        format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            itemTimeDate = format.parse(itemTime);
        }catch (Exception e){
            Log.e("SQLComment", "String转换成时间错误"+"  "+e.getMessage() );
        }
        competitionProject.setItemTime(itemTimeDate);
        competitionProject.setItemSex(itemSex);
        competitionProject.setItemMinAge(itemMinAge);
        competitionProject.setItemMaxAge(itemMaxAge);

        try {
            competitionProject.saveThrows();
        }catch (Exception e){
            Log.e("SQLComment", "CompetitionProject异常数据保存到数据库失败"+"  "+e.getMessage() );
        }
    }

}
