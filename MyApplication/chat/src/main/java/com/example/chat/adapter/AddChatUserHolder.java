package com.example.chat.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat.R;

public class AddChatUserHolder extends RecyclerView.ViewHolder {

    TextView name;
    TextView text;
    public AddChatUserHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.userName);
        text = itemView.findViewById(R.id.userText);
    }
}
