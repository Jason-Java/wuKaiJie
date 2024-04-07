package com.example.projectone.chat;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectone.R;
import com.example.projectone.add.activity.NewFriendActivity;
import com.example.projectone.chat.adapter.ChatAdapter;
import com.example.projectone.table.Friends;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {
    View view;
    RecyclerView chatRecycleview;
    ChatAdapter chatAdapter;

    Intent intent;
    String username;
    List<Friends> friends;
    Friends friend;
    List<String> data;

    private boolean isFirstLoading = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chat_fragment,null);

        initView();

        initData();
        //这两不加不显示
        chatRecycleview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        //设置item的分割线
        chatRecycleview.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        initEvent();


        return view;
    }


    private void initData() {

        chatAdapter.setData(data);

    }

    public void initView() {
        intent = getActivity().getIntent();
        username = intent.getStringExtra("name");

        friends = LitePal.where("username = ?",username).find(Friends.class);
        data = new ArrayList<>();//防止为空
        if (friends.size() == 1){
            friend = friends.get(0);
            data = friend.getFriends();
        }
        chatRecycleview  = view.findViewById(R.id.chat_list);
        chatAdapter = new ChatAdapter(getActivity(),username);
        chatRecycleview.setAdapter(chatAdapter);
    }

    private void initEvent() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoading){
            initView();
            initData();
        }else {
            isFirstLoading = true;
        }
    }
}
