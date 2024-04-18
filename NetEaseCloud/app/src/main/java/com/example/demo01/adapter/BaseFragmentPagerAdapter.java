package com.example.demo01.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *通用FragmentPagerAdapter
 * 主要是创建了列表
 * 实现了通用的方法
 * 直接用FragmentPagerAdapter有可能有内存泄漏
 * 所以使用FragmentStatePagerAdapter
 *
 */
public abstract class BaseFragmentPagerAdapter<T> extends FragmentStatePagerAdapter {
    /**
     * 上下文
     */
    Context context;

    //列表数据源
    protected List<T> datum = new ArrayList<>();

    /**
     * 构造方法
     */
    public BaseFragmentPagerAdapter(Context context,@NonNull FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    /**
     * 获取当前位置方法
     */
    protected T getData(int position) {
        return datum.get(position);
    }

    /**
     * 返回有多少个
     */
    @Override
    public int getCount() {
        return datum.size();
    }

    /**
     * 设置数据
     * @param datum
     */
    public void setDatum(List<T> datum){
        if (datum != null &&datum.size() > 0){
            this.datum.clear();
            this.datum.addAll(datum);

            //通知数据改变了
            notifyDataSetChanged();
        }
    }

    /**
     * 添加数据
     * @param datum
     */
    public void addDatum(List<T> datum){
        if (datum != null &&datum.size() > 0){
            this.datum.addAll(datum);

            //通知数据改变了
            notifyDataSetChanged();
        }
    }
}
