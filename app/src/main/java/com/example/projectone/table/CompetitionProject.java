package com.example.projectone.table;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class CompetitionProject extends LitePalSupport {

    private String competitionName;

    private String competitionItem;

    private Date itemTime;

    private String itemSex;

    private String itemMinAge;

    private int itemMaxAge;

    public Date getItemTime() {
        return itemTime;
    }

    public void setItemTime(Date itemTime) {
        this.itemTime = itemTime;
    }

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

    public String getItemSex() {
        return itemSex;
    }

    public void setItemSex(String itemSex) {
        this.itemSex = itemSex;
    }

    public String getItemMinAge() {
        return itemMinAge;
    }

    public void setItemMinAge(String itemMinAge) {
        this.itemMinAge = itemMinAge;
    }

    public int getItemMaxAge() {
        return itemMaxAge;
    }

    public void setItemMaxAge(int itemMaxAge) {
        this.itemMaxAge = itemMaxAge;
    }
}
