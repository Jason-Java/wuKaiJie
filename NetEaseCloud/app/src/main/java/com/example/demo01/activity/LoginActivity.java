package com.example.demo01.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo01.R;
import com.example.demo01.ToastUtil;
import com.example.demo01.databinding.ActivityLoginBinding;
import com.example.demo01.util.LogUtil;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * 登录界面
 */

public class LoginActivity extends BaseTitleActivity {

    String TAG = "LoginActivity";

    ActivityLoginBinding binding;

    //用户名输入框
    @BindView(R.id.et_username)
    EditText etUsername;

    //密码输入框
    @BindView(R.id.et_password)
    EditText etPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    //登录按钮
    @OnClick(R.id.bt_login)
    public void onLoginClick(){
        //trim去掉输入字符串最前面和最后面的空格
        String username = etUsername.getText().toString().trim();
        if (StringUtils.isBlank(username)){
            LogUtil.d(TAG,"onLoginClick username empty");
//            Toast.makeText(getMainActivity(), R.string.enter_username, Toast.LENGTH_SHORT).show();
//            Toasty.error(getMainActivity(), R.string.enter_username,Toasty.LENGTH_SHORT).show();
            ToastUtil.errorShortToast(getMainActivity(), R.string.enter_username);
            return;
        }

        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)){
            LogUtil.w(TAG,"onLoginClick password empty");
//            Toast.makeText(getMainActivity(), R.string.enter_password, Toast.LENGTH_SHORT).show();
            ToastUtil.errorShortToast(getMainActivity(), R.string.enter_password);
            return;
        }

        //TODO 调用登录方法

        ToastUtil.successShortToast(getMainActivity(), R.string.login_success);

    }

    //登录按钮
    @OnClick(R.id.bt_forget_password)
    public void onForgetPasswordClick(){

    }
}