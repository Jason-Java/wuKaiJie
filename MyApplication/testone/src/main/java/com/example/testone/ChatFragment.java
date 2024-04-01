package com.example.testone;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {
    public int[] img = new int[]{R.drawable.tp1,R.drawable.tp2,R.drawable.tp3,
            R.drawable.tp4,R.drawable.tp5,R.drawable.tp6};

    List<ChatRecord> data = new ArrayList<>();
    RecyclerView recyclerView;
    ChatRecordAdapter chatRecordAdapter;
    View view;
    PopupWindow mPopWindow;
    SmartRefreshLayout smartRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chat_fragment,null);



        initData();
        Log.i("wkj", "data.size:"+data.size());

        initRecyclerView();

        //全选
        CheckBox checkBox_delete = view.findViewById(R.id.chat_cbdelete);
        checkBox_delete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox_delete.isChecked()){
                    for (int i = 0; i < data.size(); i++) {
                        ChatRecord all = data.get(i);
                        all.setCb(true);
                        data.set(i,all);
                    }
                }else {
                    for (int i = 0; i < data.size(); i++) {
                        ChatRecord all = data.get(i);
                        all.setCb(false);
                        data.set(i,all);
                    }
                }
                //全部刷新
                chatRecordAdapter.notifyDataSetChanged();
            }
        });

        //删除按钮
        Button button_delete = view.findViewById(R.id.chat_btdelete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < data.size(); i++) {
                    boolean select = data.get(i).getCb();
                    if (select){
                        data.remove(i);
                        //删除单个
                        chatRecordAdapter.notifyItemRemoved(i);
                        i--;
                    }
                }
                Log.i("wkj", "datasize:"+data.size());
            }
        });

        //点击省略号出现悬浮框
        ImageButton chat_ellipsis = view.findViewById(R.id.chat_ellipsis);
        chat_ellipsis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.chat_clickellipsis,null);
                mPopWindow = new PopupWindow(popupView);
                mPopWindow.setWidth(300);
                mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

                mPopWindow.setBackgroundDrawable(new BitmapDrawable());
                mPopWindow.setOutsideTouchable(true);

                mPopWindow.showAsDropDown(chat_ellipsis);
            }
        });


        smartRefreshLayout = view.findViewById(R.id.refresh);
        //设置头部刷新的样式
        smartRefreshLayout.setRefreshHeader(new BezierRadarHeader(getActivity()));
        //设置页脚刷新的样式
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                smartRefreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                chatRecordAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                smartRefreshLayout.finishLoadMore(2000);
                for (int i = 100; i < 105; i++) {
                    ChatRecord chatRecord = new ChatRecord();
                    chatRecord.setName("zhang"+i);
                    chatRecord.setFinalchat("你今天"+i+"点下班是吗？");
                    chatRecord.setImg(img[i%6]);
                    chatRecord.setDate("3月"+i+"日");
                    chatRecord.setCb(false);
                    chatRecord.setNote("备注");
                    data.add(chatRecord);
                }
                chatRecordAdapter.notifyDataSetChanged();
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    //模拟数据
    private void initData(){
        for (int i = 0; i < 100; i++) {
            ChatRecord chatRecord = new ChatRecord();
            chatRecord.setName("zhang"+i);
            chatRecord.setFinalchat("你今天"+i+"点下班是吗？");
            chatRecord.setImg(img[i%6]);
            chatRecord.setDate("3月"+i+"日");
            chatRecord.setCb(false);
            chatRecord.setNote("备注");
            data.add(chatRecord);
        }
    }

    private void initRecyclerView(){
        recyclerView = view.findViewById(R.id.chat_list);

        chatRecordAdapter = new ChatRecordAdapter(data,getActivity());

        recyclerView.setAdapter(chatRecordAdapter);

        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        //设置item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));


    }




}
