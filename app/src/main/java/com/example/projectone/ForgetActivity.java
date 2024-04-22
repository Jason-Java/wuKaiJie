package com.example.projectone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.projectone.databinding.ActivityForgetBinding;
import com.example.projectone.manage.UserManage;

public class ForgetActivity extends AppCompatActivity {

    private ActivityForgetBinding binding;

    String userName;
    String telephone;
    String news;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.forgetToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //发送验证码
        binding.forgetSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = binding.forgetName.getText().toString();
                telephone = binding.forgePhone.getText().toString();
                if (UserManage.FindUser(userName,telephone).size() == 0){
                    Toast.makeText(ForgetActivity.this, "账号和手机号不匹配", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ForgetActivity.this, "1111", Toast.LENGTH_SHORT).show();
                    i = 1;
                }
            }
        });

        binding.forgetFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = binding.forgetName.getText().toString();
                telephone = binding.forgePhone.getText().toString();
                news = binding.forgetNew.getText().toString();
                if (i != 1){
                    Toast.makeText(ForgetActivity.this, "未发送验证码", Toast.LENGTH_SHORT).show();
                }else if (UserManage.FindUser(userName,telephone).size() == 0){
                    Toast.makeText(ForgetActivity.this, "账号和手机号不匹配", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ForgetActivity.this, UserManage.FindUser(userName,telephone).get(0).getPassword(),
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}