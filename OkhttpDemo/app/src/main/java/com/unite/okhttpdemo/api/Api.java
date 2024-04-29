package com.unite.okhttpdemo.api;

import com.unite.okhttpdemo.domain.PasswordLogin;
import com.unite.okhttpdemo.domain.response.DetailResponse;
import com.unite.okhttpdemo.domain.user.OneUser;
import com.unite.okhttpdemo.table.shiji.ShiJiJson;
import com.unite.okhttpdemo.util.Constant;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *网络请求接口包装类
 *对外部提供一个和框架无关的接口
 */
public class Api {

    /**
     * Api单例字段
     */
    private static Api instance;

    private final Retrofit retrofit;


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

        if (Constant.isDebug){
            //调试模式

            //创建okhttp日志拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

            //设置日志等级
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);

            //添加到网络框架中
            okhttpClientBuilder.addInterceptor(loggingInterceptor);

        }

        //初始化retrofit
        retrofit = new Retrofit.Builder()
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
    }


    //获取创造的retrofit
    public Retrofit getRetrofit(){
        return retrofit;
    }


}
