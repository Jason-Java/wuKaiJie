package com.unite.okhttpdemo.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

/**
 *viewbinding
 */
public abstract class BaseBindingActivity<T extends ViewBinding> extends BaseActivity{

    protected T binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
    }

    protected abstract void initBinding();
}
