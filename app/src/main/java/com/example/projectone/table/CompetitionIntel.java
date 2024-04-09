package com.example.projectone.table;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class CompetitionIntel extends LitePalSupport {

    private String competitionName;

    private String competitionAddress;

    private int competitionMaxnum;

    private Date competitionStartTime;

    private Date competitionEndTime;

    private Date applicationStartTime;

    private Date applicationEndTime;

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

    public int getCompetitionMaxnum() {
        return competitionMaxnum;
    }

    public void setCompetitionMaxnum(int competitionMaxnum) {
        this.competitionMaxnum = competitionMaxnum;
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
