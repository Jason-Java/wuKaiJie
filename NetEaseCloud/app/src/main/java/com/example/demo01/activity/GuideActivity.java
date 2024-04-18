package com.example.demo01.activity;

import android.os.Bundle;
import android.view.View;

import com.example.demo01.MainActivity;
import com.example.demo01.R;
import com.example.demo01.adapter.GuideAdapter;
import com.example.demo01.databinding.ActivityGuideBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导界面
 */
public class GuideActivity extends BaseCommonActivity {

    private ActivityGuideBinding binding;
    private GuideAdapter guideAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuideBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void initViews() {
        super.initViews();

        //隐藏状态栏
        hideStatusBar();

        //测试显示fragment
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container, GuideFragment.newInstance(R.drawable.guide1))
//                .commit();
    }

    @Override
    protected void initDatum() {
        super.initDatum();

        //创建适配器
        guideAdapter = new GuideAdapter(getMainActivity(),getSupportFragmentManager());

        //设置适配器的控件
        binding.vp.setAdapter(guideAdapter);

        //指示器
        //让指示器根据列表控件配合工作
        binding.ci.setViewPager(binding.vp);

        //适配器注册数据源观察者
        guideAdapter.registerDataSetObserver(binding.ci.getDataSetObserver());

        //准备数据
        List<Integer> datum = new ArrayList<>();
        datum.add(R.drawable.guide1);
        datum.add(R.drawable.guide2);
        datum.add(R.drawable.guide3);
        datum.add(R.drawable.guide4);
        datum.add(R.drawable.guide5);

        //设置数据到适配器
        guideAdapter.setDatum(datum);
    }

    @Override
    protected void initListeners() {
        super.initListeners();

        //跳转至登录注册界面
        binding.btLoginOrRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityAfterFinishThis(LoginOrRegisterActivity.class);

                setShowGuide();
            }
        });

        //跳转至首页
        binding.btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityAfterFinishThis(MainActivity.class);

                setShowGuide();
            }
        });
    }

    /**
     * 设置不再显示引导界面
     */
    private void setShowGuide(){
//        PreferencesUtil.getInstance(getMainActivity()).setShowGuide(false);
        sp.setShowGuide(false);
    }
}