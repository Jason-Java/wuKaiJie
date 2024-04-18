package com.example.demo01.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo01.R;
import com.example.demo01.databinding.ActivityLoginOrRegisterBinding;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginOrRegisterActivity extends BaseCommonActivity {

    ActivityLoginOrRegisterBinding binding;

    @BindView(R.id.bt_register)
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginOrRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void initViews() {
        super.initViews();

        //显示亮色状态
        lightStatusBar();
    }

    //登录按钮
    @OnClick(R.id.bt_login)
    public void OnLoginClick(){
        startActivity(LoginActivity.class);
    }

    //注册按钮
    @OnClick(R.id.bt_register)
    public void OnRegisterClick(){
        startActivity(RegisterActivity.class);
    }
}