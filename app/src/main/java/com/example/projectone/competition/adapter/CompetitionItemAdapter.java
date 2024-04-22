package com.example.projectone.competition.adapter;

import android.content.Context;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.projectone.R;
import com.example.projectone.competition.activity.ApplicationActivity;
import com.example.projectone.competition.activity.CompetitionItemActivity;
import com.example.projectone.manage.CompetitionPersonManage;
import com.example.projectone.my.adapter.BaseRecyclerViewAdapter;
import com.example.projectone.my.adapter.BaseRecyclerViewHolder;
import com.example.projectone.table.CompetitionIntel;
import com.example.projectone.table.CompetitionPerson;
import com.example.projectone.table.CompetitionProject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompetitionItemAdapter extends BaseRecyclerViewAdapter<CompetitionProject> {
    String username;
    CompetitionProject competitionProject;
    private String competitionName;
    private String competitionItem;
    private Date itemTime;
    private String itemSex;
    private int itemMinAge;
    private int itemMaxAge;
    private String itemName;
    private SimpleDateFormat format;
    private String itemTimeString;
    List<CompetitionPerson> competitionPeople = new ArrayList<>();

    CompetitionTwoAdapter competitionTwoAdapter;
    Context mContext;


    public CompetitionItemAdapter(int mLayoutID, Context mContext, String username) {
        super(mLayoutID, mContext, username);
        this.username = username;
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseRecyclerViewHolder holder, CompetitionProject bean, int position) {
        competitionProject = bean;

        competitionName = competitionProject.getCompetitionName();
        competitionItem = competitionProject.getCompetitionItem();
        itemTime = competitionProject.getItemTime();
        format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        itemTimeString = format.format(itemTime);
        itemSex = competitionProject.getItemSex();
        itemMinAge = competitionProject.getItemMinAge();
        itemMaxAge = competitionProject.getItemMaxAge();

        itemName = itemSex + "子组" + itemMinAge +"~"+ itemMaxAge + "岁" + competitionItem;

        holder.setText(R.id.competitionOneItem,itemTimeString+"\n"+itemName);

        //RecyclerView
        competitionTwoAdapter = new CompetitionTwoAdapter(R.layout.competitiontwo,mContext);
        holder.setRecyclerView(R.id.competitionOneRecyclerView).setAdapter(competitionTwoAdapter);

        //设置布局
        holder.setRecyclerView(R.id.competitionOneRecyclerView).setLayoutManager(new LinearLayoutManager(mContext,
                LinearLayoutManager.VERTICAL,false));
        //色湖之分割线
        holder.setRecyclerView(R.id.competitionOneRecyclerView).addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL));

        competitionName = competitionProject.getCompetitionName();
        competitionItem = competitionProject.getCompetitionItem();
        itemSex = competitionProject.getItemSex();
        itemMinAge = competitionProject.getItemMinAge();
        itemMaxAge = competitionProject.getItemMaxAge();

        competitionPeople = new ArrayList<>();//不加的话如果下面没有赋值就会导致数据重复利用
        //传入数据
        if (CompetitionPersonManage.FindCompetitionNameItemSexAge(competitionName,competitionItem,itemSex,itemMinAge,itemMaxAge).size() > 0){
            competitionPeople = CompetitionPersonManage.FindCompetitionNameItemSexAge(competitionName,competitionItem,itemSex,itemMinAge,itemMaxAge);
        }
        competitionTwoAdapter.setData(competitionPeople);
    }
}
