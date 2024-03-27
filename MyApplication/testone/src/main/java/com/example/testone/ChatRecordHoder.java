package com.example.testone;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatRecordHoder extends RecyclerView.ViewHolder {

    TextView chatitem_name;
    TextView chatitem_chat;
    TextView chatitem_date;
    ImageView chatitem_iv;
    CheckBox chatitem_cb;
    RelativeLayout chatitem;


    public ChatRecordHoder(@NonNull View itemView) {
        super(itemView);
        chatitem_name = itemView.findViewById(R.id.chatitem_name);
        chatitem_chat = itemView.findViewById(R.id.chatitem_chat);
        chatitem_date = itemView.findViewById(R.id.chatitem_date);
        chatitem_iv = itemView.findViewById(R.id.chatitem_iv);
        chatitem_cb = itemView.findViewById(R.id.chatitem_cb);
        chatitem = itemView.findViewById(R.id.chatitem);

    }
}
