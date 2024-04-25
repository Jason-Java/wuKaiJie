package com.unite.okhttpdemo.login;

import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.api.Api;
import com.unite.okhttpdemo.base.activity.BaseBindingActivity;
import com.unite.okhttpdemo.databinding.ActivityLoginBinding;
import com.unite.okhttpdemo.domain.PasswordLogin;
import com.unite.okhttpdemo.domain.limit.OneChildren;
import com.unite.okhttpdemo.domain.response.DetailResponse;
import com.unite.okhttpdemo.domain.user.OneUser;
import com.unite.okhttpdemo.listener.HttpObserver;
import com.unite.okhttpdemo.main.MainActivity;
import com.unite.okhttpdemo.util.Constant;
import com.unite.okhttpdemo.util.LogUtil;


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

                Api.getInstance()
                        .passwordLogin(user,password)
                        .subscribe(new HttpObserver<PasswordLogin>() {
                            @Override
                            public void onSucceeded(PasswordLogin data) {
                                LogUtil.d(TAG,"onClick:passwordLogin"+data.getToken_type());
                                login.setToken_type(data.getToken_type());
                                login.setToken(data.getToken());

                                //获取用户信息，获取用户权限
                                if (login.getToken() != null){
                                    getUserMessage();
                                    getUserLimit();
                                    startActivityAfterFinishThis(MainActivity.class);
                                }
                            }
                        });

            }
        });
    }

    //获取用户权限
    private void getUserLimit() {
        Api.getInstance()
                .getLimit(login.getToken_type()+" "+login.getToken())
                .subscribe(new HttpObserver<DetailResponse<OneChildren>>() {
                    @Override
                    public void onSucceeded(DetailResponse<OneChildren> data) {
                        LogUtil.d(TAG,"onClick:getUserLimit"+
                                data.getResponse().getChildren().get(0).getName()+
                                "\n"+data.getResponse().getChildren().get(1).getChildren().get(0).getName());
                    }
                });
    }

    //获取用户信息
    private void getUserMessage() {
        Api.getInstance()
                .getUser(login.getToken_type()+" "+login.getToken())
                .subscribe(new HttpObserver<OneUser>() {
                    @Override
                    public void onSucceeded(OneUser data) {
                        LogUtil.d(TAG,"onClick:getUserMessage"+
                                data.getResponse().getTwoUser().getUserRealName()+
                                "\n"+data.getResponse().getTwoCompany().get(0).getName());
                    }
                });
    }

    //创建悬浮窗
    private void createPopuoWindow() {
        View popupView = LayoutInflater.from(getMainActivity()).inflate(R.layout.popupwindow_login, null);

        PopupWindow popupWindow = new PopupWindow(popupView);

        //获取屏幕的宽度和高度
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = getWindowWidthPx();
        int screenHeight = getWindowHeightPx();
        popupWindow.setWidth((int) screenWidth / Constant.DesignWidthInDp * Constant.loginPopupWindowWidth);
        popupWindow.setHeight((int) screenHeight / Constant.DesignHeightInDp * Constant.loginPopupWindowHeight);

        //设置是否获取焦点
        popupWindow.setFocusable(true);
        //设置可以触摸弹出框以外的区域
        popupWindow.setOutsideTouchable(true);

        //popupwidow放在rootview里
        View rootview = LayoutInflater.from(getMainActivity()).inflate(R.layout.activity_login, null);
        popupWindow.showAtLocation(rootview, Gravity.LEFT | Gravity.TOP
                , ((int) screenWidth / Constant.DesignWidthInDp * Constant.loginPopupWindow_x)
                , ((int) screenHeight / Constant.DesignHeightInDp * Constant.loginPopupWindow_y));

        //设置遮罩层
        setMask(0.5f);


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
                setMask(1.0f);
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
                setMask(1.0f);

            }
        });

    }

    //设置遮罩层
    private void setMask(float f) {
        WindowManager.LayoutParams lp =getWindow().getAttributes();
        lp.alpha = f;
        getWindow().setAttributes(lp);
    }


}