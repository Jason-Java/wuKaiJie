package com.example.demo01.api;

import com.example.demo01.domain.Session;
import com.example.demo01.domain.Sheet;
import com.example.demo01.domain.SheetDetailWrapper;
import com.example.demo01.domain.SheetListWrapper;
import com.example.demo01.domain.User;
import com.example.demo01.domain.response.DetailResponse;
import com.example.demo01.util.Constant;
import com.example.demo01.util.LogUtil;
import com.example.demo01.util.StringUtil;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

/**
 *网络请求接口包装类
 *对外部提供一个和框架无关的接口
 */
public class Api {
    /**
     * Api单例字段
     */
    private static Api instance;

    /**
     * Server单例
     */
    private final Service service;


    /**
     * 返回当前对象的唯一实例
     *
     * 单例设计模式
     * 由于移动端很少有高并发
     * 所以这个就是简单判断
     *
     * @return
     */
    public static Api getInstance(){
        if (instance == null){
            instance = new Api();
        }
        return instance;
    }

    public Api() {
        //初始化okhttp
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();

        if (LogUtil.isDebug){
            //调试模式

            //创建okhttp日志拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

            //设置日志等级
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);

            //添加到网络框架中
            okhttpClientBuilder.addInterceptor(loggingInterceptor);

            //添加stetho抓包拦截器
            okhttpClientBuilder.addNetworkInterceptor(new StethoInterceptor());

        }

        //初始化retrofit
        Retrofit retrofit = new Retrofit.Builder()
                //让retrofit使用okhttp
                .client(okhttpClientBuilder.build())
                //api地址
                .baseUrl(Constant.ENDPOINT)
                //适配rxjava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //使用gson解析json
                //包括请求参数和响应
                .addConverterFactory(GsonConverterFactory.create())
                //创建retrofit
                .build();

        //创建service
        service = retrofit.create(Service.class);
    }

    /**
    * 歌单详情
     * */
    public Observable<DetailResponse<Sheet>> sheetDatil(String id){
        return service.sheetDatil(id)
                    .subscribeOn(Schedulers.io())//网络请求放子线程
                    .observeOn(AndroidSchedulers.mainThread());//观察在主线程

    }

    public Observable<DetailResponse<Session>> login(User data){
        return service.login(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 歌单列表
     */
    public Observable<SheetListWrapper> sheets(){
        return service.sheets()
                .subscribeOn(Schedulers.io())//网络请求放子线程
                .observeOn(AndroidSchedulers.mainThread());//观察在主线程
    }

    /**
     * 用户详情
     * */
    public Observable<DetailResponse<User>> userDatil(String id,String nickname){
        //添加查询参数
        HashMap<String,String> data = new HashMap<>();

        if (StringUtils.isBlank(nickname)){
            //如果昵称不为空才添加
            data.put(Constant.NICKNAME,nickname);
        }

        return service.userDetail(id,data)
                .subscribeOn(Schedulers.io())//网络请求放子线程
                .observeOn(AndroidSchedulers.mainThread());//观察在主线程

    }

}
