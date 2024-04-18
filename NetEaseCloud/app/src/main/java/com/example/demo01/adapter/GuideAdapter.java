package com.example.demo01.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.demo01.R;
import com.example.demo01.fragment.GuideFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *引导界面适配器
 */
public class GuideAdapter extends BaseFragmentPagerAdapter<Integer> {
    /**
     * 构造方法
     * @param fm
     */
    public GuideAdapter(Context context, @NonNull FragmentManager fm) {
        super(context,fm);
    }
    /**
     * 返回当前位置Fragment
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return GuideFragment.newInstance(getData(position));
    }



}
