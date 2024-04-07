package com.example.projectone.chat.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectone.R;

public class ChatHoder extends RecyclerView.ViewHolder{

    RelativeLayout chatitem;
    ImageView chatitemImg;
    TextView chatitemName;


    public ChatHoder(@NonNull View itemView) {
        super(itemView);

        chatitem = itemView.findViewById(R.id.chatitem);
        chatitemImg = itemView.findViewById(R.id.chatitemImg);
        chatitemName = itemView.findViewById(R.id.chatitemName);

    }
}
