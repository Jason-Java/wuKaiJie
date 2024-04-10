package com.example.projectone.my.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.projectone.manage.Constant;
import com.example.projectone.manage.PersonManage;
import com.example.projectone.databinding.ActivityMypersonBinding;
import com.example.projectone.table.Person;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyPersonActivity extends AppCompatActivity {
    private ActivityMypersonBinding binding;
    Intent intent;
    String userName;

    List<Person> people;
    Person person;

    String personName;
    String personCard;
    String personSex;
    String age;
    int personAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMypersonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();

        initEvent();
    }

    private void initView() {
        intent = getIntent();
        userName = intent.getStringExtra("name");

        //初始页面
        people = PersonManage.FindUserName(userName);
        if (people.size() != 0){
            person = people.get(0);
            binding.personName.setText(person.getPersonName());
            binding.personCard.setText(person.getPersonCard());
            binding.personSex.setText(person.getPersonSex());
            binding.personAge.setText(person.getPersonAge()+"");
        }


    }

    private void initEvent() {
        //返回我的
        binding.personBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //清空按钮
        binding.personDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                people = PersonManage.FindUserName(userName);
                if (people.size() == 0){
                    Toast.makeText(MyPersonActivity.this, "无绑定用户", Toast.LENGTH_SHORT).show();
                }else {
                    person = people.get(0);
                    //获取页面数据
                    personName = binding.personName.getText().toString();
                    personCard = binding.personCard.getText().toString();//18位身份证
                    personSex = binding.personSex.getText().toString();
                    age = binding.personAge.getText().toString();
                    //判断年龄是否符合转换数字条件
                    if (age.matches(Constant.personAgeCheck)){
                        personAge = Integer.valueOf(age);
                    }else {
                        personAge = 0;
                    }
                    //校对
                    if (person.getPersonName().equals(personName) && person.getPersonCard().equals(personCard) &&
                    person.getPersonSex().equals(personSex) && person.getPersonAge() == personAge){
                        PersonManage.Delete(userName,personName,personCard,personSex,personAge);
                        Toast.makeText(MyPersonActivity.this, "清空完成", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(MyPersonActivity.this, "内容错误，请重新打开后在清空", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        //确认修改按钮
        binding.personUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personName = binding.personName.getText().toString();
                personCard = binding.personCard.getText().toString();//18位身份证
                personSex = binding.personSex.getText().toString();
                age = binding.personAge.getText().toString();
                //判断年龄是否符合转换数字条件
                if (age.matches(Constant.personAgeCheck)){
                    personAge = Integer.valueOf(age);
                }else {
                    personAge = 0;
                }
                //是否为空
                if (personName.length() == 0){
                    Toast.makeText(MyPersonActivity.this, "用户姓名为空", Toast.LENGTH_SHORT).show();
                }else if (personCard.length() == 0){
                    Toast.makeText(MyPersonActivity.this, "身份证号码为空", Toast.LENGTH_SHORT).show();
                }else if (personSex.length() == 0){
                    Toast.makeText(MyPersonActivity.this, "性别为空", Toast.LENGTH_SHORT).show();
                }else if (age.length() == 0){
                    Toast.makeText(MyPersonActivity.this, "年龄为空", Toast.LENGTH_SHORT).show();
                }else if (personName.matches(Constant.personNameCheck) == false){
                    Toast.makeText(MyPersonActivity.this, "用户姓名格式错误", Toast.LENGTH_SHORT).show();
                }else if (personCard.matches(Constant.personCardCheck ) == false){
                    Toast.makeText(MyPersonActivity.this, "身份证号码格式错误", Toast.LENGTH_SHORT).show();
                }else if (PersonSexCheck(personCard,personSex) == false){
                    Toast.makeText(MyPersonActivity.this, "性别信息与身份证不符合", Toast.LENGTH_SHORT).show();
                }else if (personAge == 0){
                    Toast.makeText(MyPersonActivity.this, "年龄格式错误", Toast.LENGTH_SHORT).show();
                }else if (PersonAgeCheck(personCard,personAge) == false){
                    Toast.makeText(MyPersonActivity.this, "年龄信息与身份证不符合", Toast.LENGTH_SHORT).show();
                }else if (PersonManage.FindUserName(userName).size() != 0){
                    Toast.makeText(MyPersonActivity.this, "账号已绑定用户请先清空", Toast.LENGTH_SHORT).show();
                }else if (PersonManage.FindPersonCard(personCard).size() != 0){
                    Toast.makeText(MyPersonActivity.this, "该身份证已被其他账号绑定，有问题请联系后台", Toast.LENGTH_SHORT).show();
                } else {
                    PersonManage.Add(userName,personName,personCard,personSex,personAge);
                    Toast.makeText(MyPersonActivity.this, "录入成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    //性别信息与身份证
    public boolean PersonSexCheck(String personCard,String personSex){
        String sexString = personCard.substring(16,17);
        int sexInt = Integer.valueOf(sexString);
        String sex;
        if (sexInt%2 == 1){
            sex = "男";
        }else {
            sex = "女";
        }

        if (sex.equals(personSex)){
            return true;
        }else {
            return false;
        }
    }
    //年龄信息与身份证
    public boolean PersonAgeCheck(String personCard,int personAge){
        String OldTimeString = personCard.substring(6,14);
        int OldTime = Integer.valueOf(OldTimeString);
        Long time = System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String NowTimeString = format.format(date);
        int NowTime = Integer.valueOf(NowTimeString);
        int ageCheck = (NowTime - OldTime)/10000 + 1;
        if (ageCheck == personAge){
            return true;
        }else {
            return false;
        }
    }
}