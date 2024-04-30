package com.unite.okhttpdemo.login;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.api.ApiService;
import com.unite.okhttpdemo.base.activity.BaseBindingActivity;
import com.unite.okhttpdemo.databinding.ActivityLoginBinding;
import com.unite.okhttpdemo.domain.PasswordLogin;
import com.unite.okhttpdemo.domain.response.DetailResponse;
import com.unite.okhttpdemo.api.listener.HttpObserver;
import com.unite.okhttpdemo.domain.user.User;
import com.unite.okhttpdemo.main.MainActivity;
import com.unite.okhttpdemo.util.Constant;
import com.unite.okhttpdemo.util.LogUtil;
import com.unite.okhttpdemo.util.PopupWindowUtil;
import com.unite.okhttpdemo.util.ToastUtil;


public class LoginActivity extends BaseBindingActivity<ActivityLoginBinding> {

    //获取token_type和token
    PasswordLogin login = new PasswordLogin();

    private static final String TAG = "LoginActivity";
    //popupwindow按钮状态，0为账号密码登录，1为人脸登录
    private int popStatus = 0;


    @Override
    protected void initBinding() {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void initViews() {
        super.initViews();

        //检测上次是否有用户登录过
        String oldUser = sp.getUser();
        if (oldUser != null) {
            binding.etUser.setText(oldUser);
            binding.etPassword.setText("123456");
        }
    }

    @Override
    protected void initListeners() {
        super.initListeners();

        //显示悬浮框，账号密码和统一登录选项
        binding.btOtherway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopuoWindow();
            }
        });

        //账号密码登录
        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = binding.etUser.getText().toString();
                String password = binding.etPassword.getText().toString();

                ApiService.getInstance()
                        .passwordLogin(user, password)
                        .subscribe(new HttpObserver<PasswordLogin>() {
                            @Override
                            public void onSucceeded(PasswordLogin data) {
                                LogUtil.d(TAG, "onClick:passwordLogin" + data.getToken_type());
                                login.setToken_type(data.getToken_type());
                                login.setToken(data.getToken());

                                //获取成功，获取用户信息，获取用户权限
                                if (login.getToken() != null) {
                                    //保存获取的token_type和token
                                    sp.setTokenType(login.getToken_type());
                                    sp.setToken(login.getToken());
                                    //获取用户信息
//                                    getUserMessage();
                                    //获取用户权限
//                                    getUserLimit();
                                    //设置用户偏好
                                    sp.setUser(user);
                                    //跳转到
                                    startActivityAfterFinishThis(MainActivity.class);
                                } else {
                                    //显示登陆失败原因
                                    ToastUtil.errorShortToast(data.getMessage());
                                }
                            }
                        });

            }
        });
    }


    //创建悬浮窗
    private void createPopuoWindow() {
        View popupView = LayoutInflater.from(getMainActivity()).inflate(R.layout.popupwindow_login, null);
        View rootview = LayoutInflater.from(getMainActivity()).inflate(R.layout.activity_login, null);

        PopupWindow popupWindow = PopupWindowUtil.getPopupWindow(getMainActivity(), popupView, rootview,
                Constant.loginPopupWindowWidth, Constant.loginPopupWindowHeight,
                Constant.loginPopupWindow_x, Constant.loginPopupWindow_y);

        //设置遮罩层
        PopupWindowUtil.setMask(getMainActivity(), 0.5f);


        Button passwordLogin = popupView.findViewById(R.id.login_pop_bt_passwordlogin);
        Button unifiedLogin = popupView.findViewById(R.id.login_pop_bt_unifiedlogin);
        if (popStatus == 0) {//显示账号密码登录
            passwordLogin.setText(R.string.passwordLogin);
        } else if (popStatus == 1) {//显示人脸登录
            passwordLogin.setText(R.string.faceLogin);
        }

        //点击外部把背景还原
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopupWindowUtil.setMask(getMainActivity(), 1.0f);
            }
        });

        passwordLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popStatus == 0) {//显示账号密码登录
                    binding.ivFaceLogin.setVisibility(View.GONE);
                    binding.tvPasswordLogin.setVisibility(View.VISIBLE);
                    binding.etUser.setVisibility(View.VISIBLE);
                    binding.etPassword.setVisibility(View.VISIBLE);
                    binding.btLogin.setVisibility(View.VISIBLE);
                    popupWindow.dismiss();
                    popStatus = 1;
                } else if (popStatus == 1) {//显示人脸登录
                    binding.ivFaceLogin.setVisibility(View.VISIBLE);
                    binding.tvPasswordLogin.setVisibility(View.GONE);
                    binding.etUser.setVisibility(View.GONE);
                    binding.etPassword.setVisibility(View.GONE);
                    binding.btLogin.setVisibility(View.GONE);
                    popupWindow.dismiss();
                    popStatus = 0;
                }
                //把背景还原
                PopupWindowUtil.setMask(getMainActivity(), 1.0f);

            }
        });

    }


}