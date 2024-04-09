package com.example.projectone.Manage;

import android.util.Log;

import com.example.projectone.table.Person;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class PersonManage {
    private static List<Person> people = new ArrayList<>();
    private static Person person;

    public static List<Person> FindUserName(String userName){
        return LitePal.where("userName = ?",userName).find(Person.class);
    }

    public static List<Person> FindPersonName(String personName){
        return LitePal.where("personName = ?",personName).find(Person.class);
    }

    public static List<Person> FindPersonCard(String personCard){
        return LitePal.where("personCard = ?",personCard).find(Person.class);
    }

    public static void Delete(String userName,String personName,String personCard,String personSex,int personAge){
        LitePal.deleteAll(Person.class,"userName = ? and personName = ? and personCard = ? and personSex = ? and personAge = ?",userName,personName,personCard,personSex,personAge+"");
    }

    public static void Add(String userName,String personName,String personCard,String personSex,int personAge){
        person = new Person();
        person.setUserName(userName);
        person.setPersonName(personName);
        person.setPersonCard(personCard);
        person.setPersonSex(personSex);
        person.setPersonAge(personAge);

        try {
            person.saveThrows();
        }catch (Exception e){
            Log.e("SQLComment", "Person异常数据保存到数据库失败"+"  "+e.getMessage() );
        }
    }
}
