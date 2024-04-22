package com.example.projectone.my.adapter;

import android.content.Context;

import com.example.projectone.R;
import com.example.projectone.table.CompetitionPerson;

public class MyProjectAdapter extends BaseRecyclerViewAdapter<CompetitionPerson>{
    String username;
    CompetitionPerson competitionPerson;

    public MyProjectAdapter(int mLayoutID, Context mContext, String username) {
        super(mLayoutID, mContext, username);
        this.username = username;
    }

    @Override
    protected void convert(BaseRecyclerViewHolder holder, CompetitionPerson bean, int position) {
        competitionPerson = bean;

        holder.setText(R.id.projectItemPerson,competitionPerson.getPersonName());
        holder.setText(R.id.projectItemName,competitionPerson.getPersonSex()+competitionPerson.getCompetitionItem());
        if (competitionPerson.getTime() != null){
            holder.setText(R.id.projectItemTime,competitionPerson.getTime());
        }else {
            holder.setText(R.id.projectItemTime,"暂无");
        }

        if (competitionPerson.getRanking() != 0){
            holder.setText(R.id.projectItemRanking,competitionPerson.getRanking()+"");
        }else {
            holder.setText(R.id.projectItemRanking,"暂无");
        }
        holder.setText(R.id.projectItemCompetitionName,competitionPerson.getCompetitionName());

    }
}
