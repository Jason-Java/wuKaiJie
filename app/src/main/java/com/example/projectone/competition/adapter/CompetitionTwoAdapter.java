package com.example.projectone.competition.adapter;

import android.content.Context;

import com.example.projectone.R;
import com.example.projectone.my.adapter.BaseRecyclerViewAdapter;
import com.example.projectone.my.adapter.BaseRecyclerViewHolder;
import com.example.projectone.table.CompetitionPerson;

public class CompetitionTwoAdapter extends BaseRecyclerViewAdapter<CompetitionPerson> {

    CompetitionPerson competitionPerson;

    public CompetitionTwoAdapter(int mLayoutID, Context mContext) {
        super(mLayoutID, mContext);
    }

    @Override
    protected void convert(BaseRecyclerViewHolder holder, CompetitionPerson bean, int position) {
        competitionPerson = bean;

        holder.setText(R.id.competitionTwoName,competitionPerson.getPersonName());
        if (competitionPerson.getTime() == null){
            holder.setText(R.id.competitionTwoTime,"暂无");
        }else {
            holder.setText(R.id.competitionTwoTime,competitionPerson.getTime());
        }

        if (competitionPerson.getRanking() == 0){
            holder.setText(R.id.competitionTwoRanking,"暂无");
        }else {
            holder.setText(R.id.competitionTwoRanking,competitionPerson.getRanking()+"");
        }

    }
}
