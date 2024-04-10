package com.example.projectone.competition.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.projectone.R;
import com.example.projectone.competition.adapter.ApplicationAdapter;
import com.example.projectone.databinding.ActivityApplicationBinding;
import com.example.projectone.manage.CommentManage;
import com.example.projectone.manage.CompetitionIntelManage;
import com.example.projectone.manage.CompetitionPersonManage;
import com.example.projectone.manage.CompetitionProjectManage;
import com.example.projectone.manage.PersonManage;
import com.example.projectone.table.CompetitionIntel;
import com.example.projectone.table.CompetitionPerson;
import com.example.projectone.table.CompetitionProject;
import com.example.projectone.table.Enjoy;
import com.example.projectone.table.Person;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ApplicationActivity extends AppCompatActivity {

    private ActivityApplicationBinding binding;
    Intent intent;
    String username;
    String competitionName;
    Person person;
    CompetitionIntel competitionIntel;
    private SimpleDateFormat format;
    String applicationStartTime;
    String applicationEndTime;
    List<Boolean> choose = new ArrayList<>();

    ApplicationAdapter applicationAdapter;
    List<CompetitionProject> competitionProjects;
    CompetitionProject competitionProject;
    private long firstPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApplicationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();

        initData();

        initEvent();
    }

    private void initView() {
        intent = getIntent();
        username = intent.getStringExtra("name");
        competitionName = intent.getStringExtra("competitionName");

        person = PersonManage.FindUserName(username).get(0);
        binding.applicationUserName.setText("账号名称："+username);
        binding.applicationPersonName.setText("用户姓名："+person.getPersonName());
        binding.applicationPersonCard.setText("身份证号码："+person.getPersonCard());
        binding.applicationPersonSex.setText("用户性别："+person.getPersonSex());
        binding.applicationPersonAge.setText("用户年龄："+person.getPersonAge());
        binding.applicationCompetitionName.setText("比赛名称："+competitionName);
        competitionIntel = CompetitionIntelManage.FindCompetition(competitionName);
        format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        applicationStartTime = format.format(competitionIntel.getApplicationStartTime());
        applicationEndTime = format.format(competitionIntel.getApplicationEndTime());
        binding.applicationTime.setText("报名时间："+applicationStartTime+"至"+applicationEndTime);

        //RecyclerView
        //设置布局
        binding.applicationRecyclerView.setLayoutManager(new LinearLayoutManager(ApplicationActivity.this,
                LinearLayoutManager.VERTICAL,false));
        //色湖之分割线
        binding.applicationRecyclerView.addItemDecoration(new DividerItemDecoration(ApplicationActivity.this,
                DividerItemDecoration.VERTICAL));

        applicationAdapter = new ApplicationAdapter(R.layout.applicationitem,ApplicationActivity.this,username);
        binding.applicationRecyclerView.setAdapter(applicationAdapter);

    }

    private void initData() {

        competitionProjects = CompetitionProjectManage.FindAgeSex(competitionName,person.getPersonAge(),
                person.getPersonSex());
        applicationAdapter.setData(competitionProjects);

        //观察checkbox
        for (int i = 0; i < competitionProjects.size(); i++) {
            choose.add(false);
        }
        applicationAdapter.setChoose(choose);

    }

    private void initEvent() {
        binding.applicationToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //点击报名
        binding.applicationBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //返回checkbox的状态
                choose = applicationAdapter.getChoose();
                int num = 0;
                for (int i = 0; i < choose.size(); i++) {
                    if (choose.get(i) == true){
                        num += 1;
                    }
                }
                int numMax = CompetitionIntelManage.FindCompetition(competitionName).getCompetitionMaxNum();
                if (num == 0){
                    Toast.makeText(ApplicationActivity.this, "没有选择任何项目", Toast.LENGTH_SHORT).show();
                }else if (num > numMax){
                    Toast.makeText(ApplicationActivity.this, "最多选"+numMax+"个", Toast.LENGTH_SHORT).show();
                }else {
                    if (System.currentTimeMillis() - firstPressedTime < 2000) {
                        for (int i = 0; i < choose.size(); i++) {
                            if (choose.get(i) == true){
                                competitionProject = competitionProjects.get(i);
                                person = PersonManage.FindUserName(username).get(0);
                                CompetitionPersonManage.Add(competitionProject.getCompetitionName(),
                                        competitionProject.getCompetitionItem(),
                                        username,person.getPersonName(),
                                        person.getPersonCard(),person.getPersonSex(),person.getPersonAge());
                            }
                        }
                        Toast.makeText(ApplicationActivity.this, "报名成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ApplicationActivity.this, "再按一次即可报名", Toast.LENGTH_SHORT).show();
                        firstPressedTime = System.currentTimeMillis();
                    }
                }
            }
        });

    }
}