package com.example.projectone.competition;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.projectone.R;
import com.example.projectone.buy.BuyFragment;
import com.example.projectone.competition.activity.ApplicationActivity;
import com.example.projectone.competition.activity.CompetitionItemActivity;
import com.example.projectone.competition.adapter.CompetitionAdapter;
import com.example.projectone.competitiondata.ChongQing;
import com.example.projectone.competitiondata.NanJing;
import com.example.projectone.databinding.BuyFragmentBinding;
import com.example.projectone.databinding.CompetitionFragmentBinding;
import com.example.projectone.manage.CompetitionIntelManage;
import com.example.projectone.manage.CompetitionPersonManage;
import com.example.projectone.manage.CompetitionProjectManage;
import com.example.projectone.manage.Constant;
import com.example.projectone.manage.PersonManage;
import com.example.projectone.my.activity.MyBuyCollectionActivity;
import com.example.projectone.my.adapter.BaseRecyclerViewAdapter;
import com.example.projectone.table.CompetitionIntel;
import com.example.projectone.table.CompetitionPerson;
import com.example.projectone.table.Person;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CompetitionFragment extends Fragment {

    View view;
    private CompetitionFragmentBinding binding;

    private int competitionImage;
    private String competitionName;
    private String competitionAddress;
    private int competitionMaxNum;
    private String competitionStartTime;
    private String competitionEndTime;
    private String applicationStartTime;
    private String applicationEndTime;

    Intent intent;
    String username;
    CompetitionAdapter competitionAdapter;
    private SharedPreferences sp;
    String state;
    List<CompetitionIntel> competitionIntels;
    CompetitionIntel competitionIntel;
    private SimpleDateFormat format;
    private String competitionItem;
    private String itemTime;
    private String itemSex;
    private int itemMinAge;
    private int itemMaxAge;
    private Person person;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CompetitionFragmentBinding.inflate(inflater,container,false);
        view = binding.getRoot();

        initView();

        initData();

        initEvent();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initView() {
        intent = getActivity().getIntent();
        username = intent.getStringExtra("name");
        //初始数据库
        sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        if (sp.getBoolean("initCompetition",false) == false){
            initCompetition();
            initCompetitionProject();
            ChongQing.initChongQingCompetitionProject();
            ChongQing.initChongQingCompetitionPerson();
            NanJing.initNanJingCompetitionProject();
            NanJing.initNanJingCompetitionPerson();
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("initCompetition",true);
            editor.apply();
        }

        //设置布局
        binding.CompetitionRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));
        //色湖之分割线
        binding.CompetitionRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));

        competitionAdapter = new CompetitionAdapter(R.layout.competitionitem,getActivity(),username);
        binding.CompetitionRecyclerview.setAdapter(competitionAdapter);

    }

    private void initData() {
        competitionIntels = CompetitionIntelManage.FindAll();
        competitionAdapter.setData(CompetitionIntelManage.FindAll());
    }

    private void initEvent() {
        //点击进入状态页面
        competitionAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                competitionIntel = competitionIntels.get(position);
                state = competitionAdapter.getState(competitionIntels.get(position));
                //未开始报名
                format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                applicationStartTime = format.format(competitionIntel.getApplicationStartTime());
                applicationEndTime = format.format(competitionIntel.getApplicationEndTime());
                if (state == Constant.competitionStateOne){
                    Toast.makeText(getActivity(), "报名时间为"+applicationStartTime+"至"+applicationEndTime, Toast.LENGTH_SHORT).show();
                }

                //报名进行中
                if (state == Constant.competitionStateTwo){
                    if (PersonManage.FindUserName(username).size() > 0){
                        person = PersonManage.FindUserName(username).get(0);
                    }


                    if (PersonManage.FindUserName(username).size() == 0){
                        Toast.makeText(getActivity(), "请先去我的创建比赛资料", Toast.LENGTH_SHORT).show();
                    }else if (CompetitionProjectManage.CheckAge(username,competitionIntel.getCompetitionName())){//判断年龄是否允许
                        Toast.makeText(getActivity(), "没有适合你的比赛项目，请换一个比赛", Toast.LENGTH_SHORT).show();
                    }else if (CompetitionPersonManage.FindPersonCard(person.getPersonCard()).size() > 0){
                        Toast.makeText(getActivity(), "你绑定的用户身份证已报名", Toast.LENGTH_SHORT).show();
                    }else {//进入报名页面
                        intent = new Intent(getActivity(), ApplicationActivity.class);
                        intent.putExtra("name",username);
                        intent.putExtra("competitionName",competitionIntel.getCompetitionName());
                        startActivity(intent);
                    }
                }

                //报名已结束、比赛未开始 + 正在比赛+比赛结束
                if (state == Constant.competitionStateThree || state == Constant.competitionStateFour ||
                state == Constant.competitionStateFive){
                    intent = new Intent(getActivity(), CompetitionItemActivity.class);
                    intent.putExtra("name",username);
                    intent.putExtra("competitionName",competitionIntel.getCompetitionName());
                    startActivity(intent);
                }
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        });

    }

    private void initCompetition(){
        //比赛未开始、报名未开始测试项目
        competitionImage = R.drawable.hangzhou;
        competitionName = "杭州市第十届'市长杯'青少年（儿童）游泳锦标赛";
        competitionAddress = "杭州奥体中心体育游泳馆";
        competitionStartTime = "2024-12-23 09:00:00";
        competitionEndTime = "2024-12-24 18:00:00";
        applicationStartTime = "2024-11-01 00:00:00";
        applicationEndTime = "2024-11-13 23:59:59";
        competitionMaxNum = 2;
        CompetitionIntelManage.Add(competitionImage,competitionName,competitionAddress, competitionStartTime,
                competitionEndTime, applicationStartTime,applicationEndTime,competitionMaxNum);


        //报名中
        competitionImage = R.drawable.shenzhen;
        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionAddress = "华润深圳湾体育中心游泳馆";
        competitionStartTime = "2024-12-10 08:30:00";
        competitionEndTime = "2024-12-14 22:00:00";
        applicationStartTime = "2024-4-10 00:00:00";
        applicationEndTime = "2024-6-13 23:59:59";
        competitionMaxNum = 2;
        CompetitionIntelManage.Add(competitionImage,competitionName,competitionAddress, competitionStartTime,
                competitionEndTime, applicationStartTime,applicationEndTime,competitionMaxNum);

        //报名已结束，比赛未开始
        competitionImage = R.drawable.chongqing;
        competitionName = "中国体育彩票'浩沙杯' 2023年重庆市青少年游泳锦标赛";
        competitionAddress = "重庆市奥体中心游泳馆";
        competitionStartTime = "2024-06-11 09:00:00";
        competitionEndTime = "2024-06-14 16:59:59";
        applicationStartTime = "2024-03-01 00:00:00";
        applicationEndTime = "2024-04-01 23:59:59";
        competitionMaxNum = 2;
        CompetitionIntelManage.Add(competitionImage,competitionName,competitionAddress, competitionStartTime,
                competitionEndTime, applicationStartTime,applicationEndTime,competitionMaxNum);

        //比赛进行中的测试项目
        competitionImage = R.drawable.nanjing;
        competitionName = "'魅力江苏 最美体育' —— '泳往直前'2024江苏省游泳俱乐部联赛";
        competitionAddress = "南京奥体中心游泳馆";
        competitionStartTime = "2024-04-10 08:30:00";
        competitionEndTime = "2024-06-14 22:00:00";
        applicationStartTime = "2024-02-15 00:00:00";
        applicationEndTime = "2024-03-13 23:59:59";
        competitionMaxNum = 2;
        CompetitionIntelManage.Add(competitionImage,competitionName,competitionAddress, competitionStartTime,
                competitionEndTime, applicationStartTime,applicationEndTime,competitionMaxNum);

        //比赛已结束的测试项目
        competitionImage = R.drawable.shanghai;
        competitionName = "2024年上海市青少年体育精英系列赛游泳比赛第一站";
        competitionAddress = "浦发银行上海东方体育中心游泳馆";
        competitionStartTime = "2024-04-05 08:30:00";
        competitionEndTime = "2024-04-07 22:00:00";
        applicationStartTime = "2024-02-15 00:00:00";
        applicationEndTime = "2024-03-13 23:59:59";
        competitionMaxNum = 2;
        CompetitionIntelManage.Add(competitionImage,competitionName,competitionAddress, competitionStartTime,
                competitionEndTime, applicationStartTime,applicationEndTime,competitionMaxNum);

        competitionImage = R.drawable.chengdu;
        competitionName = "'游遍四川'2024年四川省 U系列游泳公开赛（成都站）";
        competitionAddress = "西南交通大学（犀浦校区）游泳馆";
        competitionStartTime = "2024-04-06 08:30:00";
        competitionEndTime = "2024-04-08 22:00:00";
        applicationStartTime = "2024-02-15 00:00:00";
        applicationEndTime = "2024-03-13 23:59:59";
        competitionMaxNum = 2;
        CompetitionIntelManage.Add(competitionImage,competitionName,competitionAddress, competitionStartTime,
                competitionEndTime, applicationStartTime,applicationEndTime,competitionMaxNum);

    }

    public void initCompetitionProject(){
        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米蝶泳";
        itemTime = "2024-12-10 08:30:00";
        itemSex = "男";
        itemMinAge = 13;
        itemMaxAge = 15;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米蝶泳";
        itemTime = "2024-12-10 09:00:00";
        itemSex = "女";
        itemMinAge = 13;
        itemMaxAge = 15;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米蝶泳";
        itemTime = "2024-12-10 09:30:00";
        itemSex = "男";
        itemMinAge = 16;
        itemMaxAge = 18;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米蝶泳";
        itemTime = "2024-12-10 10:00:00";
        itemSex = "女";
        itemMinAge = 16;
        itemMaxAge = 18;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米蝶泳";
        itemTime = "2024-12-10 10:30:00";
        itemSex = "男";
        itemMinAge = 19;
        itemMaxAge = 21;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米蝶泳";
        itemTime = "2024-12-10 11:00:00";
        itemSex = "女";
        itemMinAge = 19;
        itemMaxAge = 21;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米仰泳";
        itemTime = "2024-12-10 11:30:00";
        itemSex = "男";
        itemMinAge = 13;
        itemMaxAge = 15;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米仰泳";
        itemTime = "2024-12-10 12:00:00";
        itemSex = "女";
        itemMinAge = 13;
        itemMaxAge = 15;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米仰泳";
        itemTime = "2024-12-10 12:30:00";
        itemSex = "男";
        itemMinAge = 16;
        itemMaxAge = 18;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米仰泳";
        itemTime = "2024-12-10 13:00:00";
        itemSex = "女";
        itemMinAge = 16;
        itemMaxAge = 18;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米仰泳";
        itemTime = "2024-12-10 14:30:00";
        itemSex = "男";
        itemMinAge = 19;
        itemMaxAge = 21;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米仰泳";
        itemTime = "2024-12-10 15:00:00";
        itemSex = "女";
        itemMinAge = 19;
        itemMaxAge = 21;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);


        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米蛙泳";
        itemTime = "2024-12-11 08:30:00";
        itemSex = "男";
        itemMinAge = 13;
        itemMaxAge = 15;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米蛙泳";
        itemTime = "2024-12-11 09:00:00";
        itemSex = "女";
        itemMinAge = 13;
        itemMaxAge = 15;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米蛙泳";
        itemTime = "2024-12-11 09:30:00";
        itemSex = "男";
        itemMinAge = 16;
        itemMaxAge = 18;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米蛙泳";
        itemTime = "2024-12-11 10:00:00";
        itemSex = "女";
        itemMinAge = 16;
        itemMaxAge = 18;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米蛙泳";
        itemTime = "2024-12-11 10:30:00";
        itemSex = "男";
        itemMinAge = 19;
        itemMaxAge = 21;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米蛙泳";
        itemTime = "2024-12-11 11:00:00";
        itemSex = "女";
        itemMinAge = 19;
        itemMaxAge = 21;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米自由泳";
        itemTime = "2024-12-11 11:30:00";
        itemSex = "男";
        itemMinAge = 13;
        itemMaxAge = 15;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米自由泳";
        itemTime = "2024-12-11 12:00:00";
        itemSex = "女";
        itemMinAge = 13;
        itemMaxAge = 15;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米自由泳";
        itemTime = "2024-12-11 12:30:00";
        itemSex = "男";
        itemMinAge = 16;
        itemMaxAge = 18;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米自由泳";
        itemTime = "2024-12-11 13:00:00";
        itemSex = "女";
        itemMinAge = 16;
        itemMaxAge = 18;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米自由泳";
        itemTime = "2024-12-11 14:30:00";
        itemSex = "男";
        itemMinAge = 19;
        itemMaxAge = 21;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);

        competitionName = "2024年深圳南山区青少年游泳锦标赛";
        competitionItem = "50米自由泳";
        itemTime = "2024-12-11 15:00:00";
        itemSex = "女";
        itemMinAge = 19;
        itemMaxAge = 21;
        CompetitionProjectManage.Add(competitionName,competitionItem,itemTime, itemSex,itemMinAge,itemMaxAge);
    }
}
