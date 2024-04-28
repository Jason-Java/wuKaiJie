package com.example.chat.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat.Chat;
import com.example.chat.R;

import java.util.List;

public class AddChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    String name;
    List<Chat> chats;
    Chat chat;
    private View view;
    private AddChatUserHolder addChatUserHolder;
    private AddChatFriendHolder addChatFriendHolder;

    public AddChatAdapter(Context context, String name) {
        this.name = name;
        this.context = context;
    }

    public void setData(List<Chat> chats){
        this.chats = chats;
    }

    @Override
    public int getItemViewType(int position) {
        if (chats.get(position).getName().equals(name)){
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
            view = View.inflate(context, R.layout.chatitem_user,null);
            addChatUserHolder = new AddChatUserHolder(view);
            return addChatUserHolder;
        }else {
            view = View.inflate(context,R.layout.chatitem_friend,null);
            addChatFriendHolder = new AddChatFriendHolder(view);
            return addChatFriendHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        chat = chats.get(position);

        switch (getItemViewType(position)){
            case 0:
                addChatUserHolder.name.setText(chat.getName());
                addChatUserHolder.text.setText(chat.getText());
                break;
            case 1:
                addChatFriendHolder.name.setText(chat.getName());
                addChatFriendHolder.text.setText(chat.getText());
        }
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }
}
