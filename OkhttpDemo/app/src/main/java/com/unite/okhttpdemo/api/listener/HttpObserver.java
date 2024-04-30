package com.unite.okhttpdemo.api.listener;

import com.unite.okhttpdemo.base.activity.BaseActivity;
import com.unite.okhttpdemo.domain.response.BaseResponse;
import com.unite.okhttpdemo.util.HttpUtil;
import com.unite.okhttpdemo.util.LoadingUtil;
import com.unite.okhttpdemo.util.LogUtil;

import io.reactivex.disposables.Disposable;

/**
 *网络请求Observer
 */
public abstract class HttpObserver<T> extends ObserverAdapter<T>{

    private static final String TAG = "HttpObserver";
    //是否显示加载对话框
    private boolean isShowLoading;
    //界面
    private BaseActivity activity;

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
    public HttpObserver(BaseActivity activity,boolean isShowLoading){
        this.activity = activity;
        this.isShowLoading = isShowLoading;
    }

    /**
     * 请求成功
     * @param data
     * @return
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

        //检查是否需要隐藏加载提示框
        CheckHideLoading();

        if (isSucceeded(t)){
            LogUtil.d(TAG,"onSucceeded");
            //请求正常
            onSucceeded(t);
        }else {
            LogUtil.d(TAG,"no onSucceeded");
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
            LogUtil.d(TAG,baseResponse.getStatus()+"\n"+baseResponse.getMsg());
            return baseResponse.getStatus() == null || baseResponse.getStatus() == 200 ||baseResponse.getStatus() == 0;
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
