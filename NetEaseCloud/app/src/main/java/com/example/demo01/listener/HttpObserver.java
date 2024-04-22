package com.example.demo01.listener;

import android.text.TextUtils;

import com.example.demo01.R;
import com.example.demo01.activity.BaseCommonActivity;
import com.example.demo01.domain.response.BaseResponse;
import com.example.demo01.util.HttpUtil;
import com.example.demo01.util.LoadingUtil;
import com.example.demo01.util.LogUtil;
import com.example.demo01.util.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 *网络请求Observer
 */
public abstract class HttpObserver<T> extends ObserverAdapter<T>{

    private static final String TAG = "HttpObserver";
    //是否显示加载对话框
    private boolean isShowLoading;
    //界面
    private BaseCommonActivity activity;

    /**
     * 无参构造方法
     */
    public HttpObserver() {
    }

    /**
     * 构造方法
     * @param activity
     * @param isShowLoading
     */
    public HttpObserver(BaseCommonActivity activity,boolean isShowLoading){
        this.activity = activity;
        this.isShowLoading = isShowLoading;
    }

    /**
     * 请求成功
     * @param data
     */
    public abstract void onSucceeded(T data);

    /**
     * 请求失败
     * @param data
     * @param e
     */
    public boolean onFailed(T data,Throwable e){
        return false;
    }

    @Override
    public void onSubscribe(Disposable d) {
        super.onSubscribe(d);
        if (isShowLoading){
            //显示加载对话框
            LoadingUtil.showLoading(activity);
        }
    }

    @Override
    public void onNext(T t) {
        super.onNext(t);

        LogUtil.d(TAG,"onNext"+t);
        //检查是否需要隐藏加载提示框
        CheckHideLoading();

        if (isSucceeded(t)){
            //请求正常
            onSucceeded(t);
        }else {
            //请求出错
            handlerRequest(t,null);
        }


    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        LogUtil.d(TAG,"onError"+e.getLocalizedMessage());

        //检查是否需要隐藏加载提示框
        CheckHideLoading();

        //处理错误
        handlerRequest(null,e);
    }

    /**
     * 判断网络请求是否成功
     * @param t
     * @return
     */
    private boolean isSucceeded(T t) {
        if (t instanceof BaseResponse){
            //判断具体的业务请求是否成功
            BaseResponse baseResponse = (BaseResponse) t;

            //没有状态码表示成功
            //这是我们和服务端的一个规定
            LogUtil.d(TAG,baseResponse.getMessage()+"aaaaa"+baseResponse.getStatus());
            return baseResponse.getStatus() == 0 || baseResponse.getStatus() == null;
        }
        return false;
    }

    /**
     * 处理错误网络请求
     * @param
     * @param error
     */
    private void handlerRequest(T data, Throwable error) {
        if (onFailed(data, error)){//子类处理错误

        }else{//父类处理错误
            HttpUtil.handlerRequest(data,error);
        }
    }

    //检查是否需要隐藏加载提示框
    private void CheckHideLoading() {
        if (isShowLoading){
            LoadingUtil.hideLoading();
        }
    }
}
