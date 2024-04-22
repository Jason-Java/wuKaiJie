package com.example.projectone.add.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectone.R;

public class AddChatFriendHolder extends RecyclerView.ViewHolder {

    TextView name;
    TextView text;
    TextView date;

    public AddChatFriendHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.friendName);
        text = itemView.findViewById(R.id.friendText);
        date = itemView.findViewById(R.id.chatfrienddate);
    }
}
