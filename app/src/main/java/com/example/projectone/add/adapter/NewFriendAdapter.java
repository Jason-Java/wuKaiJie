package com.example.projectone.add.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectone.R;
import com.example.projectone.chat.ChatFragment;
import com.example.projectone.chat.adapter.ChatAdapter;
import com.example.projectone.table.AddFriend;
import com.example.projectone.table.Friends;
import com.example.projectone.table.User;

import org.litepal.LitePal;
import org.litepal.annotation.Column;
import org.litepal.tablemanager.Connector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewFriendAdapter extends RecyclerView.Adapter<NewFriendHoder> {

    private Context context;
    private List<AddFriend> data = new ArrayList<>();
    private List<User> users;
    User user;
    private String name;
    AddFriend friend;
    View view;

    private  String username;
    private  String addname;
    private  String say;
    private int apply;
    private Date date;

    List<Friends> relation;
    Friends relationship;
    ChatFragment chatFragment;


    public NewFriendAdapter(Context context, List<AddFriend> data, String name) {
        this.context = context;
        this.data = data;
        this.name = name;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewFriendHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = View.inflate(context, R.layout.newfriend_item,null);
        NewFriendHoder newFriendHoder = new NewFriendHoder(view);
        return newFriendHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewFriendHoder holder, int position) {
        friend = data.get(position);
        //时间
        date = friend.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH点");
        try {
            holder.newfriendDate.setText(format.format(date));
        }catch (Exception e){
            Log.e("looknews", "时间格式转换失败"+e);
        }

        //区别
        Connector.getDatabase();
        username = friend.getUsername();
        addname = friend.getAddname();
        apply = friend.getApply();
        //请求者是自己
        if (username.equals(name)){
            holder.newfriendName.setText(addname);
            users = LitePal.where("username = ?",addname).find(User.class);
            user = users.get(0);
            if (user.getHeadpicture() == null){
                Glide.with(context).load(R.drawable.defaultheadpicture).into(holder.newfriendHeadpicture);
            }else{
                // 加载本地图片
                try {
                    Uri uri = Uri.parse(user.getHeadpicture());
                    Glide.with(context).load(uri).into(holder.newfriendHeadpicture);
                }catch (Exception e){
                    Toast.makeText(context,e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
            holder.newfriendMytext.setText("");
            holder.newfriendAgree.setVisibility(View.GONE);
            holder.newfriendRefuse.setVisibility(View.GONE);
//            是否同意（0表示还未看、1表示拒绝、2表示同意）
            holder.newfriendResult.setVisibility(View.VISIBLE);
            if (apply ==0){
                holder.newfriendResult.setText("等待回复");
            }else if (apply == 1){
                holder.newfriendResult.setText("对方已拒绝");
            }else if (apply == 2){
                holder.newfriendResult.setText("对方已同意");
            }
        }else{
            holder.newfriendName.setText(username);
            users = LitePal.where("username = ?",username).find(User.class);
            user = users.get(0);
            if (user.getHeadpicture() == null){
                Glide.with(context).load(R.drawable.defaultheadpicture).into(holder.newfriendHeadpicture);
            }else{
                // 加载本地图片
                try {
                    Uri uri = Uri.parse(user.getHeadpicture());
                    Glide.with(context).load(uri).into(holder.newfriendHeadpicture);
                }catch (Exception e){
                    Toast.makeText(context,e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
            holder.newfriendMytext.setText(friend.getSay());
            if (apply == 1){
                holder.newfriendAgree.setVisibility(View.GONE);
                holder.newfriendRefuse.setVisibility(View.GONE);
                holder.newfriendResult.setVisibility(View.VISIBLE);
                holder.newfriendResult.setText("已拒绝");
            }else if (apply==2){
                holder.newfriendAgree.setVisibility(View.GONE);
                holder.newfriendRefuse.setVisibility(View.GONE);
                holder.newfriendResult.setVisibility(View.VISIBLE);
                holder.newfriendResult.setText("已同意");
            }
        }

        //点击拒绝
        holder.newfriendRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friend = new AddFriend();
                friend = data.get(holder.getAdapterPosition());
                friend.setApply(1);
                friend.save();
                notifyDataSetChanged();
            }
        });

        //点击同意
        holder.newfriendAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friend = new AddFriend();
                friend = data.get(holder.getAdapterPosition());
                //一定要重新赋值
                username = friend.getUsername();
                addname = friend.getAddname();
                friend.setApply(2);
                friend.save();
                //请求者
                relation = LitePal.where("username = ?",username).find(Friends.class);
                if (relation.size() == 0){
                    relationship = new Friends();
                    relationship.setUsername(username);
                    List<String> newfriends = new ArrayList<>();
                    newfriends.add(addname);
                    relationship.setFriends(newfriends);
                    relationship.save();
                }else{
                    relationship = relation.get(0);
                    List<String> newfriends = relationship.getFriends();
                    newfriends.add(addname);
                    relationship.setFriends(newfriends);
                    relationship.save();
                }
                //被请求者
                relation = LitePal.where("username = ?",addname).find(Friends.class);
                if (relation.size() == 0){
                    relationship = new Friends();
                    relationship.setUsername(addname);
                    List<String> newfriends = new ArrayList<>();
                    newfriends.add(username);
                    relationship.setFriends(newfriends);
                    relationship.save();
                }else{
                    relationship = relation.get(0);
                    List<String> newfriends = relationship.getFriends();
                    newfriends.add(username);
                    relationship.setFriends(newfriends);
                    relationship.save();
                }
                notifyDataSetChanged();
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

}
