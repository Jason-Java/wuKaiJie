package com.example.projectone.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectone.R;
import com.example.projectone.add.activity.AddEnjoyActivity;
import com.example.projectone.add.activity.AddFriendActivity;
import com.example.projectone.add.activity.NewFriendActivity;
import com.example.projectone.my.activity.ChangemyActivity;

public class AddFragment extends Fragment {
    Button add_addfriend;
    Button add_newfriend;
    Button addEnjoy;
    View view;
    Intent intent;
    String name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_fragment,null);

        initView();

        initData();

        initEvent();

        return view;
    }

    private void initView() {
        add_addfriend = view.findViewById(R.id.add_addfriend);
        add_newfriend = view.findViewById(R.id.add_newfriend);
        addEnjoy = view.findViewById(R.id.add_addEnjoy);

        intent = getActivity().getIntent();
        name = intent.getStringExtra("name");

    }

    private void initData() {

    }

    private void initEvent() {
        //跳转添加朋友界面
        add_addfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), AddFriendActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        //跳转新的朋友界面
        add_newfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), NewFriendActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);

            }
        });

        //跳转添加分享界面
        addEnjoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), AddEnjoyActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

    }
}