package com.example.projectone.add.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectone.R;
import com.example.projectone.table.Network;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AddChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    String address;
    List<Network> networks;
    Network network;
    private View view;
    private AddChatUserHolder addChatUserHolder;
    private AddChatFriendHolder addChatFriendHolder;

    public AddChatAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Network> networks){
        this.networks = networks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (networks.get(position).getNews() == 1){
            return 0;
        }else{
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = null;
        if (viewType == 0){
            view = View.inflate(context, R.layout.chatitem_user,null);
            return new AddChatUserHolder(view);
        }else {
            view = View.inflate(context,R.layout.chatitem_friend,null);
            return new AddChatFriendHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        network = networks.get(position);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(network.getDate());

        switch (getItemViewType(position)){
            case 0:
                addChatUserHolder = (AddChatUserHolder) holder;
                addChatUserHolder.name.setText(network.getHost());
                addChatUserHolder.text.setText(network.getText());
                addChatUserHolder.date.setText(date);
                break;
            case 1:
                addChatFriendHolder = (AddChatFriendHolder) holder;
                addChatFriendHolder.name.setText(network.getHost());
                addChatFriendHolder.text.setText(network.getText());
                addChatFriendHolder.date.setText(date);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return networks.size();
    }
}
