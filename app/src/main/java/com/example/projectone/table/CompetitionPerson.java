package com.example.projectone.table;

import org.litepal.crud.LitePalSupport;

public class CompetitionPerson extends LitePalSupport {

    private String competitionName;

    private String competitionItem;

    private String userName;

    private String personName;

    private String personCard;

    private String personSex;

    private int personAge;


    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getCompetitionItem() {
        return competitionItem;
    }

    public void setCompetitionItem(String competitionItem) {
        this.competitionItem = competitionItem;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonCard() {
        return personCard;
    }

    public void setPersonCard(String personCard) {
        this.personCard = personCard;
    }

    public String getPersonSex() {
        return personSex;
    }

    public void setPersonSex(String personSex) {
        this.personSex = personSex;
    }

    public int getPersonAge() {
        return personAge;
    }

    public void setPersonAge(int personAge) {
        this.personAge = personAge;
    }
}
