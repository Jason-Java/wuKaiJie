package com.example.projectone.manage;

public interface Constant {
    String personNameCheck = "^[\\u4e00-\\u9fa5]{2,4}$";
    String personCardCheck = "^[1-9][0-9]{5}(18|19|20)[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])[0-9]{3}([0-9]|X)$";
    String personAgeCheck = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";

    String competitionStateOne = "未开始报名";
    String competitionStateTwo = "报名进行中";
    String competitionStateThree = "报名已结束"+"\n"+"比赛准备中";
    String competitionStateFour = "正在比赛中";
    String competitionStateFive = "比赛已结束";
}
