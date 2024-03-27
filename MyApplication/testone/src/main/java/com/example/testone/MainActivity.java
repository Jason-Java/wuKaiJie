package com.example.testone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tv_homepage,tv_competition,tv_add,tv_chat,tv_my;
    ImageView iv_homepage,iv_competition,iv_add,iv_chat,iv_my;
    TextView tvCurrent;
    ImageView ivCurrent;
    LinearLayout ll_homepage,ll_competition,ll_add,ll_chat,ll_my;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll_homepage = findViewById(R.id.ll_homepage);
        ll_competition = findViewById(R.id.ll_competition);
        ll_add = findViewById(R.id.ll_add);
        ll_chat = findViewById(R.id.ll_chat);
        ll_my = findViewById(R.id.ll_my);

        iv_homepage = findViewById(R.id.iv_homepage);
        iv_competition = findViewById(R.id.iv_competition);
        iv_add = findViewById(R.id.iv_add);
        iv_chat = findViewById(R.id.iv_chat);
        iv_my = findViewById(R.id.iv_my);

        tv_homepage = findViewById(R.id.tv_homepage);
        tv_competition = findViewById(R.id.tv_competition);
        tv_add = findViewById(R.id.tv_add);
        tv_chat = findViewById(R.id.tv_chat);
        tv_my = findViewById(R.id.tv_my);

        ivCurrent = iv_homepage;
        tvCurrent = tv_homepage;

        initPage();

        ll_homepage.setOnClickListener(ll);
        ll_competition.setOnClickListener(ll);
        ll_add.setOnClickListener(ll);
        ll_chat.setOnClickListener(ll);
        ll_my.setOnClickListener(ll);
    }

    private void initPage() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomePageFragment());
        fragments.add(new CompetitionFragment());
        fragments.add(new AddFragment());
        fragments.add(new ChatFragment());
        fragments.add(new MyFragment());

        viewPager2 = findViewById(R.id.viewpage_main);
        viewPager2.setAdapter(new ViewPagerMainAdapter(getSupportFragmentManager(),
                getLifecycle(),fragments));
        //当界面滑动
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {//滑动时改变的事物
                super.onPageSelected(position);
                changeButtom(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void changeButtom(int position) {
        ivCurrent.setSelected(false);
        tvCurrent.setTextColor(getResources().getColor(R.color.black));
        switch (position){
            case R.id.ll_homepage:
                viewPager2.setCurrentItem(0);
            case 0:
                iv_homepage.setSelected(true);
                tv_homepage.setTextColor(getResources().getColor(R.color.bottomcolor));
                ivCurrent = iv_homepage;
                tvCurrent =tv_homepage;
                break;
            case R.id.ll_competition:
                viewPager2.setCurrentItem(1);
            case 1:
                iv_competition.setSelected(true);
                tv_competition.setTextColor(getResources().getColor(R.color.bottomcolor));
                ivCurrent = iv_competition;
                tvCurrent =tv_competition;
                break;
            case R.id.ll_add:
                viewPager2.setCurrentItem(2);
            case 2:
                iv_add.setSelected(true);
                tv_add.setTextColor(getResources().getColor(R.color.bottomcolor));
                ivCurrent = iv_add;
                tvCurrent =tv_add;
                break;
            case R.id.ll_chat:
                viewPager2.setCurrentItem(3);
            case 3:
                iv_chat.setSelected(true);
                tv_chat.setTextColor(getResources().getColor(R.color.bottomcolor));
                ivCurrent = iv_chat;
                tvCurrent =tv_chat;
                break;
            case R.id.ll_my:
                viewPager2.setCurrentItem(4);
            case 4:
                iv_my.setSelected(true);
                tv_my.setTextColor(getResources().getColor(R.color.bottomcolor));
                ivCurrent = iv_my;
                tvCurrent =tv_my;
                break;
        }
    }

    View.OnClickListener ll = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            changeButtom(view.getId());
        }
    };


//    View.OnClickListener ll = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            FragmentManager fm = getSupportFragmentManager();
//            FragmentTransaction ft = fm.beginTransaction();
//            Fragment f = null;
//            switch (view.getId()){
//                case R.id.ll_homepage:
//                    f = new HomePageFragment();
//                    break;
//                case R.id.ll_competition:
//                    f = new CompetitionFragment();
//                    break;
//                case R.id.ll_add:
//                    f = new AddFragment();
//                    break;
//                case R.id.ll_chat:
//                    f = new ChatFragment();
//                    break;
//                case R.id.ll_my:
//                    f = new MyFragment();
//                    break;
//            }
//            ft.replace(R.id.fragmentlayout,f);
//            ft.commit();
//        }
//    };
}