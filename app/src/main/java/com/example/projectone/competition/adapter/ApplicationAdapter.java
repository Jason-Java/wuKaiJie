package com.example.projectone.competition.adapter;

import android.content.Context;
import android.widget.CompoundButton;

import com.example.projectone.R;
import com.example.projectone.my.adapter.BaseRecyclerViewAdapter;
import com.example.projectone.my.adapter.BaseRecyclerViewHolder;
import com.example.projectone.table.CompetitionProject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ApplicationAdapter extends BaseRecyclerViewAdapter<CompetitionProject> {
    String username;
    CompetitionProject competitionProject;

    String itemName;
    private String competitionItem;
    private Date itemTime;
    private String itemSex;
    private int itemMinAge;
    private int itemMaxAge;
    private SimpleDateFormat format;
    List<Boolean> choose;

    public void setChoose(List<Boolean> choose){
        this.choose = choose;
    }


    public ApplicationAdapter(int mLayoutID, Context mContext, String username) {
        super(mLayoutID, mContext, username);
        this.username = username;
    }

    @Override
    protected void convert(BaseRecyclerViewHolder holder, CompetitionProject bean, int position) {
        competitionProject = bean;

        competitionItem = competitionProject.getCompetitionItem();
        itemTime = competitionProject.getItemTime();
        itemSex = competitionProject.getItemSex();
        itemMinAge = competitionProject.getItemMinAge();
        itemMaxAge = competitionProject.getItemMaxAge();

        itemName = itemSex + "子组" + itemMinAge +"~"+ itemMaxAge + "岁" + competitionItem;
        holder.setText(R.id.competitionItemName,itemName);

        format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        holder.setText(R.id.competitionItemTime,format.format(itemTime));

        //设置checkbox初始状态，并设置点击事件
        holder.setCheckBox(R.id.competitionitemCb,false);
        holder.setCheckBox(R.id.competitionitemCb).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int address = holder.getLayoutPosition();
                if (holder.setCheckBox(R.id.competitionitemCb).isChecked()){
                    choose.set(address,true);
                }else {
                    choose.set(address,false);
                }
            }
        });
    }

    public List<Boolean> getChoose(){
        return choose;
    }
}
