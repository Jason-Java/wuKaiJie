package com.example.projectone;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.projectone.buy.BuyFragment;
import com.example.projectone.enjoy.EnjoyFragment;


public class HomePageFragment extends Fragment {
    LinearLayout enjoyll,newsll,buyll;
    TextView enjoytext,newstext,buytext;
    View enjoyview,newsview,buyview;
    ViewPager2 viewPager;
    View view;
    TextView tvCurrent;
    View viewCurrent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homepage_fragment,null);

        enjoyll = view.findViewById(R.id.homepage_enjoyll);
        enjoytext = view.findViewById(R.id.homepage_enjoytext);
        enjoyview = view.findViewById(R.id.homepage_enjoyview);
        newsll = view.findViewById(R.id.homepage_newsll);
        newstext = view.findViewById(R.id.homepage_newstext);
        newsview = view.findViewById(R.id.homepage_newsview);
        buyll = view.findViewById(R.id.homepage_buyll);
        buytext = view.findViewById(R.id.homepage_buytext);
        buyview = view.findViewById(R.id.homepage_buyview);

        //初始
        tvCurrent = newstext;
        viewCurrent = newsview;
        replaceFragment(new NewsFragment());
        newstext.setTextColor(ContextCompat.getColor(getActivity(),R.color.bottomcolor));
        newsview.setBackgroundResource(R.color.bottomcolor);


        enjoyll.setOnClickListener(ll);
        newsll.setOnClickListener(ll);
        buyll.setOnClickListener(ll);

        return view;
    }

    private void changeButtom(int position) {
        tvCurrent.setTextColor(ContextCompat.getColor(getActivity(),R.color.nochoose));
        viewCurrent.setBackgroundResource(R.color.white);
        switch (position){
            case R.id.homepage_enjoyll:
                enjoytext.setTextColor(ContextCompat.getColor(getActivity(),R.color.bottomcolor));
                enjoyview.setBackgroundResource(R.color.bottomcolor);
                tvCurrent = enjoytext;
                viewCurrent = enjoyview;
                replaceFragment(new EnjoyFragment());
                break;
            case R.id.homepage_newsll:
                newstext.setTextColor(ContextCompat.getColor(getActivity(),R.color.bottomcolor));
                newsview.setBackgroundResource(R.color.bottomcolor);
                tvCurrent = newstext;
                viewCurrent = newsview;
                replaceFragment(new NewsFragment());
                break;
            case R.id.homepage_buyll:
                buytext.setTextColor(ContextCompat.getColor(getActivity(),R.color.bottomcolor));
                buyview.setBackgroundResource(R.color.bottomcolor);
                tvCurrent = buytext;
                viewCurrent = buyview;
                replaceFragment(new BuyFragment());
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();//开启一个事务
        fragmentTransaction.replace(R.id.homepage_ll,fragment);
        fragmentTransaction.commit();
    }

    View.OnClickListener ll = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            changeButtom(view.getId());
        }
    };
}
