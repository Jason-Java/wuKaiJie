package com.example.projectone.table;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class CompetitionIntel extends LitePalSupport {
    private  int competitionImage;

    private String competitionName;

    private String competitionAddress;

    private int competitionMaxNum;

    private Date competitionStartTime;

    private Date competitionEndTime;

    private Date applicationStartTime;

    private Date applicationEndTime;

    public int getCompetitionImage() {
        return competitionImage;
    }

    public void setCompetitionImage(int competitionImage) {
        this.competitionImage = competitionImage;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getCompetitionAddress() {
        return competitionAddress;
    }

    public void setCompetitionAddress(String competitionAddress) {
        this.competitionAddress = competitionAddress;
    }

    public int getCompetitionMaxNum() {
        return competitionMaxNum;
    }

    public void setCompetitionMaxNum(int competitionMaxNum) {
        this.competitionMaxNum = competitionMaxNum;
    }

    public Date getCompetitionStartTime() {
        return competitionStartTime;
    }

    public void setCompetitionStartTime(Date competitionStartTime) {
        this.competitionStartTime = competitionStartTime;
    }

    public Date getCompetitionEndTime() {
        return competitionEndTime;
    }

    public void setCompetitionEndTime(Date competitionEndTime) {
        this.competitionEndTime = competitionEndTime;
    }

    public Date getApplicationStartTime() {
        return applicationStartTime;
    }

    public void setApplicationStartTime(Date applicationStartTime) {
        this.applicationStartTime = applicationStartTime;
    }

    public Date getApplicationEndTime() {
        return applicationEndTime;
    }

    public void setApplicationEndTime(Date applicationEndTime) {
        this.applicationEndTime = applicationEndTime;
    }
}
