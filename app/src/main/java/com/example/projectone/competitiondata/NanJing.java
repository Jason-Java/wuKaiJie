package com.example.projectone.competitiondata;

import com.example.projectone.manage.CompetitionPersonManage;
import com.example.projectone.manage.CompetitionProjectManage;

import java.util.Date;

public class NanJing {
    private static String competitionName;
    private static String competitionItem;
    private static String itemTime;
    private static String itemSex;
    private static int itemMinAge;
    private static int itemMaxAge;
    private static String userName;
    private static String personName;
    private static String personCard;
    private static String personSex;
    private static int personAge;
    private static String time;
    private static int ranking;
    private static String addTime;

    public static void initNanJingCompetitionProject(){
        competitionName = "'魅力江苏 最美体育' —— '泳往直前'2024江苏省游泳俱乐部联赛";
        competitionItem = "50米蝶泳";
        itemTime = "2024-12-10 08:30:00";
        itemSex = "男";
        itemMinAge = 13;
        itemMaxAge = 15;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "'魅力江苏 最美体育' —— '泳往直前'2024江苏省游泳俱乐部联赛";
        competitionItem = "100米蝶泳";
        itemTime = "2024-12-10 09:00:00";
        itemSex = "男";
        itemMinAge = 13;
        itemMaxAge = 15;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);
    }


    public static void initNanJingCompetitionPerson(){
        competitionName = "'魅力江苏 最美体育' —— '泳往直前'2024江苏省游泳俱乐部联赛";
        competitionItem = "50米蝶泳";
        userName = "wkj";
        personName = "张三";
        personCard = "330411200207150618";
        personSex = "男";
        personAge = 13;
        time = "00:00:50";
        ranking = 1;
        addTime = "2024-04-11 13:34:00";

        CompetitionPersonManage.Add(competitionName,competitionItem,userName,personName,
                personCard,personSex,personAge,time,ranking,addTime);

        competitionName = "'魅力江苏 最美体育' —— '泳往直前'2024江苏省游泳俱乐部联赛";
        competitionItem = "50米蝶泳";
        userName = "wkj";
        personName = "张四";
        personCard = "330411200207150619";
        personSex = "男";
        personAge = 13;
        time = "00:00:51";
        ranking = 2;
        addTime = "2024-04-11 13:34:00";

        CompetitionPersonManage.Add(competitionName,competitionItem,userName,personName,
                personCard,personSex,personAge,time,ranking,addTime);

        competitionName = "'魅力江苏 最美体育' —— '泳往直前'2024江苏省游泳俱乐部联赛";
        competitionItem = "50米蝶泳";
        userName = "woc";
        personName = "李四";
        personCard = "330411200207150631";
        personSex = "男";
        personAge = 13;
        time = "犯规";
        ranking = 0;
        addTime = "2024-04-11 13:34:00";

        CompetitionPersonManage.Add(competitionName,competitionItem,userName,personName,
                personCard,personSex,personAge,time,ranking,addTime);

        competitionName = "'魅力江苏 最美体育' —— '泳往直前'2024江苏省游泳俱乐部联赛";
        competitionItem = "100米蝶泳";
        userName = "wkj";
        personName = "张三";
        personCard = "330411200207150618";
        personSex = "男";
        personAge = 13;

        CompetitionPersonManage.Add(competitionName,competitionItem,userName,personName,
                personCard,personSex,personAge);

        competitionName = "'魅力江苏 最美体育' —— '泳往直前'2024江苏省游泳俱乐部联赛";
        competitionItem = "100米蝶泳";
        userName = "wkj";
        personName = "张四";
        personCard = "330411200207150619";
        personSex = "男";
        personAge = 13;

        CompetitionPersonManage.Add(competitionName,competitionItem,userName,personName,
                personCard,personSex,personAge);

        competitionName = "'魅力江苏 最美体育' —— '泳往直前'2024江苏省游泳俱乐部联赛";
        competitionItem = "100米蝶泳";
        userName = "woc";
        personName = "李四";
        personCard = "330411200207150631";
        personSex = "男";
        personAge = 13;

        CompetitionPersonManage.Add(competitionName,competitionItem,userName,personName,
                personCard,personSex,personAge);
    }

}
