package com.example.projectone.competition.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projectone.R;
import com.example.projectone.manage.Constant;
import com.example.projectone.my.adapter.BaseRecyclerViewAdapter;
import com.example.projectone.my.adapter.BaseRecyclerViewHolder;
import com.example.projectone.table.CompetitionIntel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CompetitionAdapter extends BaseRecyclerViewAdapter<CompetitionIntel> {
    CompetitionIntel competitionIntel;
    String username;

    ImageView competitionImage;
    TextView competitionState;
    TextView competitionName;
    TextView competitionAddress;
    TextView competitionTime;

    Date competitionStartTime;
    Date competitionEndTime;
    Date applicationStartTime;
    Date applicationEndTime;

    Drawable drawable;
    String state;
    SimpleDateFormat format;
    Context mContext;


    public CompetitionAdapter(int mLayoutID, Context mContext, String username) {
        super(R.layout.competitionitem, mContext, username);
        this.username = username;
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseRecyclerViewHolder holder, CompetitionIntel bean, int position) {

        competitionIntel = bean;

        competitionImage = holder.get(R.id.competitionImage);
        Glide.with(mContext).load(competitionIntel.getCompetitionImage()).into(competitionImage);

        //设置赛事状态
        competitionState = holder.get(R.id.competitionState);
        //文字显示在图片之上
        drawable = competitionImage.getDrawable();
        competitionState.setCompoundDrawables(null,drawable,null,null);
        state = getState(competitionIntel);
        competitionState.setText(state);

        //设置比赛名称，比赛地点
        holder.setText(R.id.competitionName,competitionIntel.getCompetitionName());
        holder.setText(R.id.competitionAddress,"比赛地点:"+competitionIntel.getCompetitionAddress());

        //比赛时间
        competitionStartTime = competitionIntel.getCompetitionStartTime();
        competitionEndTime = competitionIntel.getCompetitionEndTime();
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        holder.setText(R.id.competitionTime,"比赛时间:"+format.format(competitionStartTime)+
                " 至 "+format.format(competitionEndTime));

    }

    //判断赛事的状态并返回String
    public String getState(CompetitionIntel competitionIntel){
        //获取时间
        competitionStartTime = competitionIntel.getCompetitionStartTime();
        competitionEndTime = competitionIntel.getCompetitionEndTime();
        applicationStartTime = competitionIntel.getApplicationStartTime();
        applicationEndTime = competitionIntel.getApplicationEndTime();

        Long time = System.currentTimeMillis();
        Date now = new Date(time);
        //小于0时now在以前
        if (now.compareTo(applicationStartTime) < 0 ){
            return Constant.competitionStateOne;
        }else if (now.compareTo(applicationEndTime) < 0){
            return Constant.competitionStateTwo;
        }else if (now.compareTo(competitionStartTime) < 0){
            return Constant.competitionStateThree;
        }else if (now.compareTo(competitionEndTime) < 0){
            return Constant.competitionStateFour;
        }else {
            return Constant.competitionStateFive;
        }


    }
}
