package com.example.projectone.chat.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectone.R;

public class ChatFriendHolder extends RecyclerView.ViewHolder {


    ImageView imageView;
    TextView name;
    TextView text;

    public ChatFriendHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.chatFriendIv);
        name = itemView.findViewById(R.id.chatFriendName);
        text = itemView.findViewById(R.id.chatFriendText);
    }
}
