package com.example.projectone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectone.manage.UserManage;
import com.example.projectone.table.User;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private String[] PM_MULTIPLE={
            Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.INTERNET
    };

    private TextView logon;
    private Button login;
    private EditText et_pwd;
    private EditText et_name;
    private CheckBox rememberpwd;
    private CheckBox autologin;
    private SharedPreferences sp;

    Boolean init;
    Boolean initlogon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        applyForMultiplePermissions();

        
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
        SharedPreferences.Editor editor = sp.edit();

        //初始化账号
        initlogon = sp.getBoolean("initlogon",false);
        if (initlogon == false){
            initlogon();
        }


        //关闭app，再打开
        Boolean rememberpwd2 = sp.getBoolean("rememberpwd",false);//取不到就取默认值false
        Boolean autologin2 = sp.getBoolean("autologin",false);
        if (rememberpwd2){
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
            //验证用户名是否为空
            Connector.getDatabase();
            List<User> user = LitePal.where("username = ?",name).find(User.class);
            if (user.size() > 1){
                Toast.makeText(this, "数据库错误", Toast.LENGTH_SHORT).show();
            }else if (user.size() == 0){
                Toast.makeText(this, "用户名不存在，请注册", Toast.LENGTH_SHORT).show();
            }else{
                String correctpwd = user.get(0).getPassword();
                if (correctpwd.equals(pwd)){
                    //记住密码
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
                    //自动登录
                    if (autologin.isChecked()){
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean("autologin",true);
                        editor.apply();
                    }else {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.remove("autologin");
                        editor.apply();
                    }

                    //跳转到主页面
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("init",init);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void initlogon() {
        UserManage.Add("root", "wkj123456",
                "15868374899","1842256004@qq.com");
        UserManage.Add("wkj", "wkj123456",
                "15868374891","1842256001@qq.com");
        UserManage.Add("wtf", "wkj123456",
                "15868374811","1842256002@qq.com");
        UserManage.Add("woc", "wkj123456",
                "15868374111","1842256003@qq.com");
    }

    //权限申请
    public void applyForMultiplePermissions(){
        Log.i("apply","applyForMultiplePermissions");
        try{
            //如果操作系统SDK级别在23之上（android6.0），就进行动态权限申请
            if(Build.VERSION.SDK_INT>=23){
                ArrayList<String> pmList=new ArrayList<>();
                //获取当前未授权的权限列表
                for(String permission:PM_MULTIPLE){
//                    检查应用是否具有某个危险权限，如果应用具有此权限，方法将返回 PackageManager.PERMISSION_GRANTED；
//                    如果应用不具有此权限，方法将返回 PackageManager.PERMISSION_DENIED。
                    int nRet= ContextCompat.checkSelfPermission(this,permission);
                    Log.i("apply","checkSelfPermission nRet="+nRet);
                    if(nRet!= PackageManager.PERMISSION_GRANTED){
                        pmList.add(permission);
                    }
                }

                if(pmList.size()>0){
                    Log.i("apply","进行权限申请...");
                    String[] sList=pmList.toArray(new String[0]);
//                    应用可以通过这个方法动态申请权限，调用后会弹出一个对话框提示用户授权所申请的权限组
                    ActivityCompat.requestPermissions(this,sList,10000);
                }
                else{
                    Toast.makeText(this, "所以权限申请完毕", Toast.LENGTH_SHORT).show();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void see(View view) {
        //该可见
//        if (et_pwd.getInputType() == 131073){
//            et_pwd.setInputType();
//        }
//        Toast.makeText(this, et_pwd.getInputType()+"", Toast.LENGTH_SHORT).show();
    }
}