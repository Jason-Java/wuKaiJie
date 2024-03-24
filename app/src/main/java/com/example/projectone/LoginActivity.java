package com.example.projectone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private TextView logon;
    private Button login;
    private EditText et_pwd;
    private EditText et_name;
    private CheckBox rememberpwd;
    private CheckBox autologin;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        logon = findViewById(R.id.loginregister);
        login = findViewById(R.id.logintoMain);
        et_name = findViewById(R.id.loginUsername);
        et_pwd = findViewById(R.id.loginPassword);
        rememberpwd = findViewById(R.id.checkbox_rememberpwd);
        autologin = findViewById(R.id.checkbox_autologin);
                
        
        //跳转至注册页面
        logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,LogonActivity.class);
                startActivity(intent);
            }
        });

        //内部存储
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);


        //关闭app，再打开
        Boolean remberpwd2 = sp.getBoolean("rememberpwd",false);//取不到就取默认值false
        Boolean autologin2 = sp.getBoolean("autologin",false);
        if (remberpwd2){
            et_name.setText(sp.getString("name",""));
            et_pwd.setText(sp.getString("password",""));
            rememberpwd.setChecked(true);
        }
        if (autologin2){
            autologin.setChecked(true);
            Toast.makeText(this, "自动登录", Toast.LENGTH_SHORT).show();
            logintoMain(new View(this));
        }

    }
    //跳转至主页面
    public void logintoMain(View view) {
        String name = et_name.getText().toString();
        String pwd = et_pwd.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)){
            Toast.makeText(LoginActivity.this, "用户名或者密码为空", Toast.LENGTH_SHORT).show();
        }else{
            //少一步检验密码是否正确
            if (rememberpwd.isChecked()){
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name",name);
                editor.putString("password",pwd);
                editor.putBoolean("rememberpwd",true);
                editor.apply();//数据上交
            }else{
                SharedPreferences.Editor editor = sp.edit();
                editor.remove("name");
                editor.remove("password");
                editor.remove("rememberpwd");
                editor.apply();
            }
            if (autologin.isChecked()){
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("autologin",true);
                editor.apply();
            }else {
                SharedPreferences.Editor editor = sp.edit();
                editor.remove("autologin");
                editor.apply();
            }
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
}