package com.example.projectone.manage;

import android.util.Log;

import com.example.projectone.table.CompetitionPerson;

import org.litepal.LitePal;

import java.util.List;

public class CompetitionPersonManage {
    private static CompetitionPerson competitionPerson;
    private static List<CompetitionPerson> competitionPeople;

    public static List<CompetitionPerson> FindPerson(String personName){
        return LitePal.where("personName = ?",personName).find(CompetitionPerson.class);
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

}
