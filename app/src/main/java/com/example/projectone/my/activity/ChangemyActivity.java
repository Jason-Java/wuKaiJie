package com.example.projectone.my.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.projectone.MainActivity;
import com.example.projectone.R;
import com.example.projectone.table.User;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class ChangemyActivity extends AppCompatActivity {
    Toolbar changemy_toolbar;
    EditText changemy_name;
    EditText changemy_email;
    EditText changemy_phone;
    EditText changemy_newpassword1;
    EditText changemy_newpassword2;
    EditText changemy_mysay;
    Button changemy_change;

    Intent intent;
    String name;
    String newname;
    String password1;
    String password2;
    String passwordcheck;
    String mysay;
    int change;
    List<User> users;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changemy);

        initView();

        initData();

        initEvent();
    }

    private void initView() {
        changemy_toolbar = findViewById(R.id.changemy_Toolbar);
        changemy_name = findViewById(R.id.changemy_name);
        changemy_email = findViewById(R.id.changemy_email);
        changemy_phone = findViewById(R.id.changemy_phone);
        changemy_newpassword1 = findViewById(R.id.changemy_newpassword1);
        changemy_newpassword2 = findViewById(R.id.changemy_newpassword1);
        changemy_mysay = findViewById(R.id.changemy_mysay);
        changemy_change = findViewById(R.id.changemy_change);

        intent = getIntent();
        name = intent.getStringExtra("name");
        changemy_name.setText(name);

        Connector.getDatabase();
        users = LitePal.where("username = ?",name).find(User.class);
        user = users.get(0);
        changemy_email.setText(user.getEmail());
        changemy_phone.setText(user.getTelephone());
        if(user.getSentence() != null){
            changemy_mysay.setText(user.getSentence());
        }

    }

    private void initData() {
    }

    private void initEvent() {
        //退出
        changemy_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //更改按钮
        changemy_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change = 0;
                users = LitePal.where("username = ?",name).find(User.class);
                user = users.get(0);
                newname = changemy_name.getText().toString();
                if (newname.equals(name) == false){
                    if (newname.length() == 0){
                        Toast.makeText(ChangemyActivity.this, "更改失败，用户名为空", Toast.LENGTH_SHORT).show();
                        change += 1;
                    }else if (newname.length()>10){
                        Toast.makeText(ChangemyActivity.this, "更改失败，用户名长度大于10", Toast.LENGTH_SHORT).show();
                        change += 1;
                    }else if (LitePal.where("username = ?",newname).find(User.class).size() == 1){
                        Toast.makeText(ChangemyActivity.this, "更改失败，用户名已存在", Toast.LENGTH_SHORT).show();
                        change += 1;
                    }else{
                        user.setUsername(newname);
                    }
                }

                password1 = changemy_newpassword1.getText().toString();
                password2 = changemy_newpassword2.getText().toString();
                passwordcheck = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}$";
                if (password1.length() > 0){
                    if (password1.matches(passwordcheck) == false){
                        Toast.makeText(ChangemyActivity.this, "更改失败，密码由数字和字母组成，长度大于等于8", Toast.LENGTH_SHORT).show();
                        change += 1;
                    }else if (password1.equals(password2) == false){
                        Toast.makeText(ChangemyActivity.this, "更改失败，确认密码与原密码不相等", Toast.LENGTH_SHORT).show();
                        change += 1;
                    }else{
                        user.setPassword(password1);
                    }
                }

                mysay = changemy_mysay.getText().toString();
                if (mysay.length() >0){
                    if (mysay.length() > 24){
                        Toast.makeText(ChangemyActivity.this, "更改失败，个人名言长度大于24", Toast.LENGTH_SHORT).show();
                        change += 1;
                    }else{
                        user.setSentence(mysay);
                    }
                }

                if (change == 0){
                    user.save();
                    Toast.makeText(ChangemyActivity.this, "更改成功", Toast.LENGTH_SHORT).show();
                    intent = new Intent(ChangemyActivity.this, MainActivity.class);
                    //从一个activity离开后，按返回键不要再回去
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("name",newname);
                    intent.putExtra("id",5);
                    startActivity(intent);
                    ChangemyActivity.this.finish();
                }else {
                    Toast.makeText(ChangemyActivity.this, "更改失败", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}