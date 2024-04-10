package com.example.projectone.enjoy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectone.R;
import com.example.projectone.enjoy.adapter.EnjoyAdapter;
import com.example.projectone.table.Enjoy;

import org.litepal.LitePal;

import java.util.List;

public class EnjoyFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    EnjoyAdapter enjoyAdapter;
    Intent intent;
    String username;

    List<Enjoy> enjoys;
    private SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.enjoy_fragment,null);

        initView();

        initData();

        initEvent();

        return view;
    }

    private void initView() {
        recyclerView = view.findViewById(R.id.enjoy_home);

        intent = getActivity().getIntent();
        username = intent.getStringExtra("name");

        //模拟数据
        sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        if (sp.getBoolean("initEnjoy",false) == false){
            initEnjoy();
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("initEnjoy",true);
            editor.apply();
        }

        enjoyAdapter = new EnjoyAdapter(getActivity(),username);
        recyclerView.setAdapter(enjoyAdapter);

        //这两不加不显示
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        //设置item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
    }

    private void initData() {
        enjoys = LitePal.order("date desc").find(Enjoy.class);
        enjoyAdapter.setData(enjoys);
    }

    private void initEvent() {

    }

    private void initEnjoy() {

        Enjoy enjoy1 = new Enjoy();
        enjoy1.setUsername("root");
        enjoy1.setType(0);
        enjoy1.setLikes(0);
        enjoy1.setDate("2014年04月07日 14:22:35");
        enjoy1.setTitle("提高自由泳的10个小技巧");
        enjoy1.setText("在自由泳的练习过程中，技术可以发挥的作用极大，拥有良好的技术可以让游泳过程更愉悦。\n" +
                "那自由泳有什么样的小技巧呢？我们一起往下看吧");
        enjoy1.setUrl("https://zhuanlan.zhihu.com/p/184966936#:~:text=%E6%8F%90%E9%AB%98%E8%87%AA%E7%94%B1%E6%B3%B3%E7%9A%8410%E4%B8%AA%E5%B0%8F%E6%8A%80%E5%B7%A7%EF%BC%81%201%201%E3%80%81%E5%A4%B4%E9%83%A8%E4%BF%9D%E6%8C%81%E8%87%AA%E7%84%B6%E7%9A%84%E4%BD%8D%E7%BD%AE%20%E5%9C%A8%E6%B8%B8%E6%B3%B3%E8%BF%87%E7%A8%8B%E4%B8%AD%EF%BC%8C%E6%88%91%E4%BB%AC%E8%A6%81%E4%BF%9D%E6%8C%81%E5%A4%B4%E9%83%A8%E7%9A%84%E4%BD%8D%E7%BD%AE%E4%B8%8E%E8%BA%AB%E4%BD%93%E5%85%B6%E4%BB%96%E9%83%A8%E4%BD%8D%E4%B8%80%E8%87%B4%EF%BC%8C%E7%9C%BC%E7%9D%9B%E7%9B%B4%E8%A7%86%E6%B0%B4%E5%BA%95%E3%80%82%20...%202%202%E3%80%81%E6%8C%89%E5%8E%8B%E8%83%B8%E9%83%A8%20%E5%9C%A8%E7%BB%83%E4%B9%A0%E8%87%AA%E7%94%B1%E6%B3%B3%E6%97%B6%EF%BC%8C%E4%BF%9D%E6%8C%81%E8%89%AF%E5%A5%BD%E5%B9%B3%E8%A1%A1%E7%9A%84%E5%85%B3%E9%94%AE%E6%98%AF%EF%BC%8C%E5%AD%A6%E4%BC%9A%E6%8C%89%E5%8E%8B%E8%83%B8%E9%83%A8%EF%BC%8C%E8%AE%A9%E8%BA%AB%E4%BD%93%E4%BF%9D%E6%8C%81%E6%B0%B4%E5%B9%B3%E4%B8%94%E8%85%BF%E9%83%A8%E4%B8%8D%E4%BC%9A%E4%B8%8B%E6%B2%89%E3%80%82,...%207%207%E3%80%81%E7%A7%BB%E5%8A%A8%E6%89%8B%E8%87%82%E6%97%B6%E4%B8%8D%E8%A6%81%E8%BF%87%E5%88%86%E5%89%8D%E4%BC%B8%20...%208%208%E3%80%81%E9%95%BF%E8%B7%9D%E7%A6%BB%E8%87%AA%E7%94%B1%E6%B3%B3%E6%97%B6%E4%BD%BF%E7%94%A8%E4%BA%8C%E6%AC%A1%E8%85%BF%20...%20%E6%9B%B4%E5%A4%9A%E9%A1%B9%E7%9B%AE");
        enjoy1.save();


        Enjoy enjoy2 = new Enjoy();
        enjoy2.setUsername("root");
        enjoy2.setType(0);
        enjoy2.setLikes(0);
        enjoy2.setDate("2024年04月07日 17:05:55");
        enjoy2.setTitle("零基础游泳教学：必看五大游泳入门教程");
        enjoy2.setText("游泳是众所周知的一项运动，但是对这一名词的具体解释了解甚少。游泳主要是通过利用水和身体相互协调的作用在水中进行的一项体育运动，该运动既能够增强自身的体质，还能作为一种娱乐休闲活动。那么初学者该怎么做才能快速掌握游泳的方法技巧呢？下面为大家带来零基础游泳教学：必看五大游泳入门教程，让您轻轻松松水上漂。\n" +
                "\n");
        enjoy2.setUrl("https://zhuanlan.zhihu.com/p/114469455");
        enjoy2.save();
    }
}
