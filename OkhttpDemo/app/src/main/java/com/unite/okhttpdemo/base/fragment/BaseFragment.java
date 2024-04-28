package com.unite.okhttpdemo.base.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;

import com.unite.okhttpdemo.util.PreferencesUtil;
import com.unite.okhttpdemo.util.ToastUtil;

/**
 *
 */
public abstract class BaseFragment<T extends ViewBinding> extends Fragment {

    private T binding;
    private FragmentTransaction transaction;
    //设置通信
    public Handler handler;
    public void setHandler(Handler handler){
        this.handler = handler;
    }

    /**
     * 找控件
     */
    /**
     * 找控件
     */
    protected PreferencesUtil sp;
    protected void initViews(){

        //设置偏好
        sp = PreferencesUtil.getInstance(getActivity());
    }

    /**
     * 设置数据
     */
    protected void initDatum(){

    }

    /**
     * 绑定监听器
     */
    protected void initListeners(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 调用onCreateViewBinding方法获取binding
        binding = onCreateViewBinding(inflater, container);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 引用置空处理
        binding = null;
    }

    // 子类使用该方法来使用binding,子类不能复写
    public final T getBinding() {
        return binding;
    }


    protected abstract T onCreateViewBinding(@NonNull LayoutInflater inflater,@NonNull ViewGroup container);

    /**
     *  View创建完毕
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initDatum();
        initListeners();
    }

    //一个fragment切换到另一个fragment
    public void switchToFragment(BaseFragment fragment,int id) {
        if (getActivity() != null) {
            transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(id, fragment);
            //当你想让用户能够返回到前一个Fragment时，你可以使用回退栈。
            transaction.addToBackStack(null);
            transaction.commit();
        }else {
            ToastUtil.errorShortToast("找不到界面");
        }
    }
}
