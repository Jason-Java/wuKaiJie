package com.example.projectone.add.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.projectone.MainActivity;
import com.example.projectone.R;
import com.example.projectone.table.AddFriend;
import com.example.projectone.table.Friends;
import com.example.projectone.table.User;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddFriendActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText addfriendEt;
    Button addfriendBt;
    ImageView addfriendHeadpicture;
    TextView addfriendName;
    TextView addfriendMytext;
    EditText addfriendSay;
    Button addfriendSend;

    String searchText;

    String emailCheck;
    String telephoneCheck;

    List<User> users;
    User user;

    Intent intent;
    List<AddFriend> addFriends;
    AddFriend addFriend;
    String username;
    String addname;
    String sayText;
    int apply;
    int oldApply;
    Date date;
    Date oldDate;
    Long a1,a2,num;
    boolean b,c,applyTrue;

    List<Friends> relation;
    Friends relationship;
    List<String> group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfriend);

        initView();

        initData();

        initEvent();
    }

    private void initView() {
        toolbar = findViewById(R.id.addfriendToolbar);
        addfriendEt = findViewById(R.id.addfriendEt);
        addfriendBt = findViewById(R.id.addfriendBt);
        addfriendHeadpicture = findViewById(R.id.addfriendHeadpicture);
        addfriendName = findViewById(R.id.addfriendName);
        addfriendMytext = findViewById(R.id.addfriendMytext);
        addfriendSay = findViewById(R.id.addfriendSay);
        addfriendSend = findViewById(R.id.addfriendSend);

    }

    private void initData() {
        emailCheck = "[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
        telephoneCheck = "^1[3-9]\\d{9}$";

        Connector.getDatabase();

    }

    private void initEvent() {
        //搜索按钮
        addfriendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchText = addfriendEt.getText().toString();
                if (searchText.length() == 0){
                    Toast.makeText(AddFriendActivity.this, "搜索框为空", Toast.LENGTH_SHORT).show();
                }else if (searchText.matches(emailCheck)){
                    users = LitePal.where("email = ?",searchText).find(User.class);
                }else if (searchText.matches(telephoneCheck)){
                    users = LitePal.where("telephone = ?",searchText).find(User.class);
                }else {
                    users = LitePal.where("username = ?",searchText).find(User.class);
                }
                if (users.size() == 0){
                    Toast.makeText(AddFriendActivity.this, "搜索无结果", Toast.LENGTH_SHORT).show();
                }else{
                    user = users.get(0);
                    if (user.getHeadpicture() == null){
                        Glide.with(AddFriendActivity.this).load(R.drawable.defaultheadpicture).into(addfriendHeadpicture);
                    }else{
                        // 加载本地图片
                        try {
                            Uri uri = Uri.parse(user.getHeadpicture());
                            Glide.with(AddFriendActivity.this).load(uri).into(addfriendHeadpicture);
                        }catch (Exception e){
                            Toast.makeText(AddFriendActivity.this,"本地资源图片找不到", Toast.LENGTH_SHORT).show();
                            Glide.with(AddFriendActivity.this).load(R.drawable.defaultheadpicture).into(addfriendHeadpicture);
                        }
                    }
                    if (user.getSentence() != null){
                        addfriendMytext.setText(user.getSentence());
                    }
                    addfriendName.setText(user.getUsername());
                    Toast.makeText(AddFriendActivity.this, "搜索成功", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //发送好友申请
        addfriendSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取请求者姓名
                intent = getIntent();
                username = intent.getStringExtra("name");
                //获取朋友姓名
                addname = addfriendName.getText().toString();
                sayText = addfriendSay.getText().toString();

                relation = LitePal.where("username = ?",username).find(Friends.class);
                if (relation.size() != 0){
                    relationship = relation.get(0);
                    group = relationship.getFriends();
                    if (group.contains(addname)){
                        c = true;
                    }else {
                        c = false;
                    }
                }else {
                    c = false;
                }
                addFriends = LitePal.where("username = ? and addname = ?",username,addname).order("date desc").find(AddFriend.class);
                addFriend = new AddFriend();
                long time=System.currentTimeMillis();
                date = new Date(time);
                if (addFriends.size() != 0){
                    addFriend = addFriends.get(0);
                    oldDate = addFriend.getDate();
                    oldApply = addFriend.getApply();
                    a1 = oldDate.getTime();
                    a2 = date.getTime();
                    num = a2 - a1;
                    if (oldApply == 0){
                        applyTrue =true;
                    }else {
                        applyTrue = false;
                    }
                    if (num/24/60/60/1000 < 24){
                        b = true;
                    }else {
                        b = false;
                    }
                }else{
                    b = false;
                    applyTrue = false;
                }




                if (addname.equals("无")){
                    Toast.makeText(AddFriendActivity.this, "没有搜索好友的账号", Toast.LENGTH_SHORT).show();
                }else if (addname.equals(username)){
                    Toast.makeText(AddFriendActivity.this, "不能添加自己", Toast.LENGTH_SHORT).show();
                }else if (sayText.length() > 24){
                    Toast.makeText(AddFriendActivity.this, "长度大于24", Toast.LENGTH_SHORT).show();
                }else if (c) {//已有该好友
                    Toast.makeText(AddFriendActivity.this, "已是好友", Toast.LENGTH_SHORT).show();
//                }else if (b){//两个请求时间差不到一天
//                    Toast.makeText(AddFriendActivity.this, "一天之差未到", Toast.LENGTH_SHORT).show();
                } else if (applyTrue){
                    Toast.makeText(AddFriendActivity.this, "上一个请求还未有结果", Toast.LENGTH_SHORT).show();
                }else{
                    //显示未看
                    apply = 0;
                    //存入数据
                    addFriend = new AddFriend();
                    addFriend.setUsername(username);
                    addFriend.setAddname(addname);
                    addFriend.setSay(sayText);
                    addFriend.setApply(apply);
                    addFriend.setDate(date);
                    addFriend.save();
                    Toast.makeText(AddFriendActivity.this, "发送申请成功", Toast.LENGTH_SHORT).show();
                    //跳回add页面
                    finish();
                }
            }

        });

        //退出
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}