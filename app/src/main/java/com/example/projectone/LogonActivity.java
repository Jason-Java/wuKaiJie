package com.example.projectone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectone.table.User;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

public class LogonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        //注册
        EditText et_name = findViewById(R.id.logon_username);
        EditText et_email = findViewById(R.id.logon_email);
        EditText et_telephone = findViewById(R.id.logon_phone);
        EditText et_password = findViewById(R.id.logon_password1);
        EditText et_passwordsure = findViewById(R.id.logon_password2);

        Button bt_logon = findViewById(R.id.logon_login);
        bt_logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取数据
                String name = et_name.getText().toString();
                String email = et_email.getText().toString();
                String telephone = et_telephone.getText().toString();
                String password = et_password.getText().toString();
                String passwordsure = et_passwordsure.getText().toString();

                String emailcheck = "[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
                String telephonecheck = "^1[3-9]\\d{9}$";
                String passwordcheck = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}$";
                //测试各个edittext是否为空
                if (name.length()==0){
                    Toast.makeText(LogonActivity.this, "注册失败，用户名为空", Toast.LENGTH_SHORT).show();
                }
                else if (email.length()==0){
                    Toast.makeText(LogonActivity.this, "注册失败，邮箱为空", Toast.LENGTH_SHORT).show();
                }
                else if (telephone.length()==0){
                    Toast.makeText(LogonActivity.this, "注册失败，电话号码为空", Toast.LENGTH_SHORT).show();
                }
                else if (password.length()==0){
                    Toast.makeText(LogonActivity.this, "注册失败，密码为空", Toast.LENGTH_SHORT).show();
                }
                else if (passwordsure.length()==0){
                    Toast.makeText(LogonActivity.this, "注册失败，没有再一次确认密码", Toast.LENGTH_SHORT).show();
                }
                //判断用户名长度要小于等于10
                else if (name.length()>10){
                    Toast.makeText(LogonActivity.this, "注册失败，用户名长度大于10", Toast.LENGTH_SHORT).show();
                }
                //判断邮箱书写是否正确
                else if (email.matches(emailcheck) == false){
                    Toast.makeText(LogonActivity.this, "注册失败，输入的邮箱格式错误", Toast.LENGTH_SHORT).show();
                }
                //判断电话号码是否正确
                else if (telephone.matches(telephonecheck) == false){
                    Toast.makeText(LogonActivity.this, "注册失败，输入的电话号码格式错误", Toast.LENGTH_SHORT).show();
                }
                //判断密码
                // 字符为数字或字母；
                // 不能全是数字；
                // 不能全是字母；
                //字符数量大于等于8.
                else if (password.matches(passwordcheck) == false){
                    Toast.makeText(LogonActivity.this, "注册失败，密码由数字和字母组成，长度大于等于8", Toast.LENGTH_SHORT).show();
                }
                //判断确认密码是否相同
                else if (passwordsure.equals(password) == false){
                    Toast.makeText(LogonActivity.this, "注册失败，确认密码与原密码不相等", Toast.LENGTH_SHORT).show();
                }
                //判断用户名是否重复
                else if (LitePal.where("username = ?",name).find(User.class).size() == 1){
                    Toast.makeText(LogonActivity.this, "注册失败，用户名已存在", Toast.LENGTH_SHORT).show();
                }
                //判断邮箱是否重复
                else if (LitePal.where("email = ?",email).find(User.class).size() == 1){
                    Toast.makeText(LogonActivity.this, "注册失败，邮箱注册过，请更换", Toast.LENGTH_SHORT).show();
                }
                //判断电话号码是否重复
                else if (LitePal.where("telephone = ?",telephone).find(User.class).size() == 1){
                    Toast.makeText(LogonActivity.this, "注册失败，电话号码注册过，请更换", Toast.LENGTH_SHORT).show();
                }else{
                    //达到以上条件注册成功
                    //现在只要你对数据库有任何的操作，表就会被自动创建出来
                    Connector.getDatabase();
                    User user = new User();
                    user.setUsername(name);
                    user.setEmail(email);
                    user.setTelephone(telephone);
                    user.setPassword(password);
                    user.save();
                    Toast.makeText(LogonActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    //注册成功后返回登录界面
                    Intent intent = new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setClass(LogonActivity.this,LoginActivity.class);
                    startActivity(intent);
                }

            }
        });


        //退出、跳转至主页面
        Button exit = findViewById(R.id.logon_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //从一个activity离开后，按返回键不要再回去
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(LogonActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}