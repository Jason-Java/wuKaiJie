package com.example.demo01.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.demo01.R;
import com.example.demo01.api.Api;
import com.example.demo01.api.Service;
import com.example.demo01.domain.Sheet;
import com.example.demo01.domain.SheetDetailWrapper;
import com.example.demo01.domain.SheetListWrapper;
import com.example.demo01.domain.User;
import com.example.demo01.domain.response.DetailResponse;
import com.example.demo01.listener.HttpObserver;
import com.example.demo01.listener.ObserverAdapter;
import com.example.demo01.util.Constant;
import com.example.demo01.util.HttpUtil;
import com.example.demo01.util.LoadingUtil;
import com.example.demo01.util.StringUtil;
import com.example.demo01.util.ToastUtil;
import com.example.demo01.databinding.ActivityLoginBinding;
import com.example.demo01.util.LogUtil;

import org.apache.commons.lang3.StringUtils;

import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
    public void onLoginClick() {
        //测试网络请求

//        //初始化okhttp
//        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
//
//        //初始化retrofit
//        Retrofit retrofit = new Retrofit.Builder()
//                //让retrofit使用okhttp
//                .client(okhttpClientBuilder.build())
//                //api地址
//                .baseUrl(Constant.ENDPOINT)
//                //适配rxjava
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                //使用gson解析json
//                //包括请求参数和响应
//                .addConverterFactory(GsonConverterFactory.create())
//                //创建retrofit
//                .build();
//
//        //创建service
//        Service service = retrofit.create(Service.class);
//
//        //请求歌单详情
//        service.sheetDatil("1")
//                .subscribeOn(Schedulers.io())//网络请求放子线程
//                .observeOn(AndroidSchedulers.mainThread())//观察在主线程
//                .subscribe(new Observer<SheetDetailWrapper>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    /**
//                     * 请求成功
//                     * @param sheetDetailWrapper
//                     */
//                    @Override
//                    public void onNext(SheetDetailWrapper sheetDetailWrapper) {
//                        LogUtil.d(TAG,"request sheet detail success"+sheetDetailWrapper.getData().getTitle());
//
//                    }
//
//                    /**
//                     * 请求失败
//                     * @param e
//                     */
//                    @Override
//                    public void onError(Throwable e) {
//                        LogUtil.d(TAG,"request sheet detail failed"+e.getLocalizedMessage());
//
//                        //判断错误类型
//                        if (e instanceof UnknownHostException){
//                            ToastUtil.errorShortToast(R.string.error_network_unknown_host);
//                        }else if (e instanceof ConnectException){
//                            ToastUtil.errorShortToast(R.string.error_network_connect);
//                        }else if (e instanceof SocketTimeoutException){
//                            ToastUtil.errorShortToast(R.string.error_network_timeout);
//                        }else if (e instanceof HttpException){
//                            HttpException exception = (HttpException) e;
//
//                            //获取响应码
//                            int code = exception.code();
//                            if (code == 401){
//                                ToastUtil.errorShortToast(R.string.error_network_not_auth);
//                            }else if (code == 403){
//                                ToastUtil.errorShortToast(R.string.error_network_not_permission);
//                            }else if (code == 404){
//                                ToastUtil.errorShortToast(R.string.error_network_not_found);
//                            }else if (code == 500){
//                                ToastUtil.errorShortToast(R.string.error_network_server);
//                            }else{
//                                ToastUtil.errorShortToast(R.string.error_network_unknown);
//                            }
//
//                        }else{
//                            ToastUtil.errorShortToast(R.string.error_network_unknown);
//                        }
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        //使用重构api
        //请求成功 onSubscribe->onNext->onComplete
        //请求失败 onSubscribe->onError
//        Api.getInstance()
//                .sheetDatil("1")
//                .subscribe(new Observer<SheetDetailWrapper>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                LogUtil.d(TAG,"onSubscribe");
//                //显示加载提示框
//                LoadingUtil.showLoading(getMainActivity());
//            }
//
//            @Override
//            public void onNext(SheetDetailWrapper sheetDetailWrapper) {
//                LogUtil.d(TAG,"request sheet detail success"+sheetDetailWrapper.getData().getTitle());
//                //隐藏加载提示框
//                LoadingUtil.hideLoading();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                LogUtil.d(TAG,"onError");
//                //隐藏加载提示框
//                LoadingUtil.hideLoading();
//
//            }
//
//            @Override
//            public void onComplete() {
//                LogUtil.d(TAG,"onComplete");
//            }
//        });

//        //测试加载提示框
//        LoadingUtil.showLoading(getMainActivity());
//
//        //3秒后隐藏加载提示框
//        //因为显示后无法点击后面的按钮
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                LoadingUtil.hideLoading();
//            }
//        },3000);

        //请求歌单列表
//        Api.getInstance().sheets()
//                .subscribe(new Observer<SheetListWrapper>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        LogUtil.d(TAG,"onSubscribe");
//                    }
//
//                    @Override
//                    public void onNext(SheetListWrapper sheetListWrapper) {
//                        LogUtil.d(TAG,"OnNext:"+sheetListWrapper.getData().getData().size());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                        LogUtil.d(TAG,"onError");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        LogUtil.d(TAG,"onComplete");
//                    }
//                });

        //使用DetailResponse
//        Api.getInstance().sheetDatil("1")
//                .subscribe(new Observer<DetailResponse<Sheet>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(DetailResponse<Sheet> sheetDetailResponse) {
//                        LogUtil.d(TAG,"OnNext" + sheetDetailResponse.getData().getTitle());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        //ObserverAdapter
//        Api.getInstance().sheetDatil("1")
//                .subscribe(new ObserverAdapter<DetailResponse<Sheet>>(){
//                    @Override
//                    public void onNext(DetailResponse<Sheet> sheetDetailResponse) {
//                        super.onNext(sheetDetailResponse);
//                        LogUtil.d(TAG,"OnNext" + sheetDetailResponse.getData().getTitle());
//
//                    }
//                });

//        使用HttpObserver
//        Api.getInstance().sheetDatil("1")
//                .subscribe(new HttpObserver<DetailResponse<Sheet>>() {
//                    @Override
//                    public void onSucceeded(DetailResponse<Sheet> data) {
//                        LogUtil.d(TAG, "OnSucceeded" + data.getData().getTitle());
//                    }
//                });


//        //模拟404错误
        Api.getInstance().userDatil("-1","11111111")
                .subscribe(new HttpObserver<DetailResponse<User>>(getMainActivity(),true) {
                    @Override
                    public void onSucceeded(DetailResponse<User> data) {
                        LogUtil.d(TAG,"OnNext" + data.getData());
                    }

                    @Override
                    public boolean onFailed(DetailResponse<User> data, Throwable e) {
                        LogUtil.d(TAG,"onFailed" + e);
                        HttpUtil.handlerRequest(data,e);
                        return true;
                    }
                });

//        //获取用户名
//        //trim去掉输入字符串最前面和最后面的空格
//        String username = etUsername.getText().toString().trim();
//        if (StringUtils.isBlank(username)){
//            LogUtil.d(TAG,"onLoginClick username empty");
////            Toast.makeText(getMainActivity(), R.string.enter_username, Toast.LENGTH_SHORT).show();
////            Toasty.error(getMainActivity(), R.string.enter_username,Toasty.LENGTH_SHORT).show();
//            ToastUtil.errorShortToast(R.string.enter_username);
//            return;
//        }
//
//        //如果用户名不是手机号不是邮箱，就是格式错误
//        if (!(StringUtil.isPhone(username) || StringUtil.isEmail(username))){
//            ToastUtil.errorShortToast(R.string.error_username_format);
//            return;
//        }
//
//        //获取密码
//        String password = etPassword.getText().toString().trim();
//        if (TextUtils.isEmpty(password)){
//            LogUtil.w(TAG,"onLoginClick password empty");
////            Toast.makeText(getMainActivity(), R.string.enter_password, Toast.LENGTH_SHORT).show();
//            ToastUtil.errorShortToast( R.string.enter_password);
//            return;
//        }
//
//        //如果密长度小于6位或者大于15位
//        if (!StringUtil.isPassword(password)){
//            ToastUtil.errorShortToast(R.string.error_password_format);
//            return;
//        }


    }

    //忘记密码按钮
    @OnClick(R.id.bt_forget_password)
    public void onForgetPasswordClick() {


    }
}