package com.example.projectone.chat.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectone.R;
import com.example.projectone.manage.UserManage;
import com.example.projectone.table.ChatRecord;

import java.util.List;

public class ChatItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    String userName;
    String friendName;
    List<ChatRecord> chatRecords;
    ChatRecord chatRecord;
    View view;
    ChatUserHolder chatUserHolder;
    ChatFriendHolder chatFriendHolder;

    public ChatItemAdapter(Context context, String userName, String friendName) {
        this.context = context;
        this.userName = userName;
        this.friendName = friendName;
    }

    public void setData(List<ChatRecord> chatRecords){
        this.chatRecords = chatRecords;
        notifyDataSetChanged();
    }

    //判断用哪个holder


    @Override
    public int getItemViewType(int position) {
        chatRecord = chatRecords.get(position);
        if (chatRecord.getUserName().equals(userName)){
            return 0;
        }else {
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = null;
        if (viewType == 0){
            view = View.inflate(context, R.layout.chatuser,null);
            chatUserHolder = new ChatUserHolder(view);
            return chatUserHolder;
        }else {
            view = View.inflate(context,R.layout.chatfriend,null);
            chatFriendHolder = new ChatFriendHolder(view);
            return chatFriendHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        chatRecord = chatRecords.get(position);

        switch (getItemViewType(position)){
            case 0:
                if (UserManage.FindHeadPicture(userName) != null){
                    Glide.with(context).load(UserManage.FindHeadPicture(userName)).into(chatUserHolder.imageView);
                }
                chatUserHolder.name.setText(userName);
                chatUserHolder.text.setText(chatRecord.getText());



                break;
            case 1:

                if (UserManage.FindHeadPicture(userName) != null){
                    Glide.with(context).load(UserManage.FindHeadPicture(userName)).into(chatFriendHolder.imageView);
                }
                chatFriendHolder.name.setText(userName);
                chatFriendHolder.text.setText(chatRecord.getText());
        }
    }

    @Override
    public int getItemCount() {
        return chatRecords.size();
    }
}
