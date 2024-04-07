package com.example.projectone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projectone.adapter.ViewPagerMainAdapter;
import com.example.projectone.add.AddFragment;
import com.example.projectone.chat.ChatFragment;
import com.example.projectone.my.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tv_homepage,tv_competition,tv_add,tv_chat,tv_my;
    ImageView iv_homepage,iv_competition,iv_add,iv_chat,iv_my;
    TextView tvCurrent;
    ImageView ivCurrent;
    LinearLayout ll_homepage,ll_competition,ll_add,ll_chat,ll_my;
    ViewPager2 viewPager2;

    Intent intent;

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

        intent = getIntent();
        if (intent.getIntExtra("id",0) == 5){
            changeButtom(R.id.ll_my);
        }

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
        tvCurrent.setTextColor(ContextCompat.getColor(this,R.color.black));
        switch (position){
            case R.id.ll_homepage:
                viewPager2.setCurrentItem(0);
            case 0:
                iv_homepage.setSelected(true);
                tv_homepage.setTextColor(ContextCompat.getColor(this,R.color.bottomcolor));
                ivCurrent = iv_homepage;
                tvCurrent =tv_homepage;
                break;
            case R.id.ll_competition:
                viewPager2.setCurrentItem(1);
            case 1:
                iv_competition.setSelected(true);
                tv_competition.setTextColor(ContextCompat.getColor(this,R.color.bottomcolor));
                ivCurrent = iv_competition;
                tvCurrent =tv_competition;
                break;
            case R.id.ll_add:
                viewPager2.setCurrentItem(2);
            case 2:
                iv_add.setSelected(true);
                tv_add.setTextColor(ContextCompat.getColor(this,R.color.bottomcolor));
                ivCurrent = iv_add;
                tvCurrent =tv_add;
                break;
            case R.id.ll_chat:
                viewPager2.setCurrentItem(3);
            case 3:
                iv_chat.setSelected(true);
                tv_chat.setTextColor(ContextCompat.getColor(this,R.color.bottomcolor));
                ivCurrent = iv_chat;
                tvCurrent =tv_chat;
                break;
            case R.id.ll_my:
                viewPager2.setCurrentItem(4);
            case 4:
                iv_my.setSelected(true);
                tv_my.setTextColor(ContextCompat.getColor(this,R.color.bottomcolor));
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
}