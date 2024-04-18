package com.example.demo01.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.demo01.databinding.FragmentGuideBinding;
import com.example.demo01.util.Constant;

/**
 * 引导界面Fragment
 */
public class GuideFragment extends BaseCommonViewBindingFragment<FragmentGuideBinding> {

    /**
     * 构造方法
     */
    public GuideFragment() {
    }

    /**
     *创建方法
     */
    public static GuideFragment newInstance(int id) {
        //创建fragment
        GuideFragment fragment = new GuideFragment();
        //创建Bundle
        Bundle args = new Bundle();

        //添加id
        args.putInt(Constant.ID,id);

        //将bundle设置到fragment
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    /**
     * 返回要显示的View
     * @param inflater
     * @param container
     * @return
     */

    @Override
    protected FragmentGuideBinding onCreateViewBinding(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return FragmentGuideBinding.inflate(inflater,container,false);
    }


    @Override
    protected void initViews() {
        super.initViews();

        //取出数据
        int id = getArguments().getInt(Constant.ID,-1);

        if (id == -1){
            //数据有问题
            //就关闭当前界面
            getActivity().finish();
            return;
        }

        //找控件,显示图片
        getBinding().iv.setImageResource(id);
    }

}