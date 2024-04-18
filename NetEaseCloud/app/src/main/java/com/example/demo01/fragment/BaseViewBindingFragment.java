package com.example.demo01.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.example.demo01.activity.BaseCommonActivity;

/**
 *所有fragment+viewBinding通用父类
 */
public abstract class BaseViewBindingFragment<T extends ViewBinding> extends Fragment {

    private T binding;

    /**
     * 找控件
     */
    protected void initViews(){

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

}
