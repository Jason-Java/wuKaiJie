package com.unite.okhttpdemo.api;

import android.os.Handler;
import android.os.Looper;

import com.unite.okhttpdemo.domain.PasswordLogin;
import com.unite.okhttpdemo.domain.limit.Children;
import com.unite.okhttpdemo.domain.response.DetailResponse;
import com.unite.okhttpdemo.domain.user.OneUser;
import com.unite.okhttpdemo.table.shiji.ShiJiJson;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 *
 */
public class ApiService {


    /**
     * Api单例字段
     */
    private static ApiService instance;

    /**
     * Server单例
     */
    private final Service service;

    public static ApiService getInstance(){
        if (instance == null){
            instance = new ApiService();
        }
        return instance;
    }


    public ApiService() {
        service = Api.getInstance().getRetrofit().create(Service.class);
    }

    /**
     * 用户密码登录
     */
    public Observable<PasswordLogin> passwordLogin(String user,String password){
        return service.passwordLogin(user,password)
                .subscribeOn(Schedulers.io())//网络请求放子线程
                .observeOn(AndroidSchedulers.mainThread());//观察在主线程
    }


    /**
     * 获取用户信息
     */
    public Observable<OneUser.Response> getUser(String token){
        return service.getUser(token)
                .subscribeOn(Schedulers.io())//网络请求放子线程
                .observeOn(AndroidSchedulers.mainThread())
                .map(OneUser->OneUser.getResponse());
    }


    /**
     * 获取权限
     */
    public Observable<DetailResponse<Children>> getLimit(String token){
        return service.getLimit(token)
                .subscribeOn(Schedulers.io())//网络请求放子线程
                .observeOn(AndroidSchedulers.mainThread());//观察在主线程
    }


    /**
     * 获取试剂
     */
    public Observable<ShiJiJson> getShiJi(String token, int rows, String name){
        return service.getShiJi(token,rows,name)
                .subscribeOn(Schedulers.io())//网络请求放子线程
                .observeOn(AndroidSchedulers.mainThread());//观察在主线程
    }
}
