package com.example.projectone.buy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.projectone.Manage.BuyManage;
import com.example.projectone.R;
import com.example.projectone.table.Buy;

import java.util.List;

public class BuyFragment extends Fragment {
    View view;

    RecyclerView recyclerView;
    BuyAdapter buyAdapter;
    Intent intent;
    String username;

    List<Buy> buys;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.buy_fragment,null);

        //模拟数据
//        initBuy();

        initView();

        initData();

        initEvent();

        return view;
    }

    private void initView() {
        recyclerView = view.findViewById(R.id.buyRecyclerView);

        intent = getActivity().getIntent();
        username = intent.getStringExtra("name");
        buyAdapter = new BuyAdapter(username,getActivity());
        recyclerView.setAdapter(buyAdapter);
        //设置layoutManager
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }

    private void initData() {
        buys = BuyManage.FindAll();
        buyAdapter.setData(buys);
    }

    private void initEvent() {

    }

    private void initBuy() {
        BuyManage.AddBuy("https://img3m6.ddimg.cn/72/34/29402676-1_b_9.jpg",
                "游泳（百班千人暑期书单 四年级推荐阅读 大奖作家刘海栖运动主题儿童文学新作）",
                36,
                "https://product.dangdang.com/29402676.html",
                "书");

        BuyManage.AddBuy("https://img3m7.ddimg.cn/21/6/23794077-1_b_2.jpg",
                "从零开始学游泳：仰泳、蛙泳、蝶泳、自由泳技巧完全图解",
                33,
                "https://product.dangdang.com/23794077.html",
                "书");

        BuyManage.AddBuy("https://img3m6.ddimg.cn/24/0/24168696-1_b_6.jpg",
                "游泳运动从入门到精通",
                45,
                "https://product.dangdang.com/24168696.html",
                "书");

        BuyManage.AddBuy("https://img3m5.ddimg.cn/92/21/23641985-1_b_3.jpg",
                "美国游泳协会力邀奥运会美国国家游泳队医疗组成员撰写",
                33,
                "https://product.dangdang.com/23641985.html",
                "书");

        BuyManage.AddBuy("https://y.zdmimg.com/202307/31/64c760ac346742682.jpg_d200.jpg",
                "DECATHLON 迪卡侬 泳衣女连体 深藏青色",
                50,
                "https://www.smzdm.com/p/109012182/",
                "游泳装备");

        BuyManage.AddBuy("https://qny.smzdm.com/202403/29/660667d526d2c4728.jpg_d200.jpg",
                "Keep 防水防雾泳镜 870 曜石黑",
                19,
                "https://www.smzdm.com/p/108134868/",
                "游泳装备");

        BuyManage.AddBuy("https://qny.smzdm.com/202306/25/64981988204f97005.jpg_d200.jpg",
                "DECATHLON 迪卡侬 2023年新健身包干湿分离女游泳防水迷彩绿4736667",
                40,
                "https://www.smzdm.com/p/108938119/",
                "游泳用品");

        BuyManage.AddBuy("https://qny.smzdm.com/202404/01/660a4970741a45825.jpg_d200.jpg",
                "361° 泳裤男游泳衣泳帽泳镜套装防尴尬专业训练泳裤分体游泳装备",
                65,
                "https://www.smzdm.com/p/108360890/",
                "游泳装备");

        BuyManage.AddBuy("https://qny.smzdm.com/202404/02/660b01da601307199.jpg_d200.jpg",
                "LI-NING 李宁 泳裤 泳镜 泳帽五分套装 男士游泳裤游泳镜时尚游泳装备LN55-169黑XL近视200度",
                80,
                "https://www.smzdm.com/p/108412503/",
                "游泳装备");

        BuyManage.AddBuy("https://y.zdmimg.com/202401/24/65b08293da3c46226.jpg_d200.jpg",
                "LI-NING 李宁 泳镜防水防雾男女高清游泳眼镜平光游泳镜LNJT224-10暗夜黑",
                59,
                "https://www.smzdm.com/p/108794461/",
                "游泳装备");

//        BuyManage.AddBuy("",
//                "",
//                ,
//                "",
//                "");
//
//        BuyManage.AddBuy("",
//                "",
//                ,
//                "",
//                "");
    }
}
