package com.example.projectone.chat.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectone.R;
import com.example.projectone.table.Friends;
import com.example.projectone.table.User;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatHoder> {
    Context context;
    List<String> data = new ArrayList<>();
    String username;
    String friendname;

    View view;
    View popupView;
    PopupWindow popupWindow;
    ChatHoder chatHoder;

    List<User> friendnews;
    User friendnew;

    private long firstPressedTime;
    String deletename;
    List<Friends> friends;
    Friends friend;
    List<String> num = new ArrayList<>();
    ChatAdapter chatAdapter;

    public ChatAdapter(Context context, String username) {
        this.context = context;
        this.username = username;
    }

    public void setData(List<String> data){
        this.data = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ChatHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = View.inflate(context, R.layout.chat_item,null);
        chatHoder = new ChatHoder(view);
        return chatHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHoder holder, int position) {
        friendname = data.get(position);

        friendnews = LitePal.where("username = ?",friendname).find(User.class);
        friendnew = friendnews.get(0);

        if (friendnew.getHeadpicture() == null){
            Glide.with(context).load(R.drawable.defaultheadpicture).into(holder.chatitemImg);
        }else{
            // 加载本地图片
            try {
                Uri uri = Uri.parse(friendnew.getHeadpicture());
                Glide.with(context).load(uri).into(holder.chatitemImg);
            }catch (Exception e){
                Toast.makeText(context,"本地资源找不到", Toast.LENGTH_SHORT).show();
                Glide.with(context).load(R.drawable.defaultheadpicture).into(holder.chatitemImg);
            }
        }
        holder.chatitemName.setText(friendname);


        //长按
        holder.chatitem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                popupView = LayoutInflater.from(context).inflate(R.layout.chat_popupwindow,null);
                popupWindow = new PopupWindow(popupView);
                popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

                TextView chatitem_deletename = popupView.findViewById(R.id.chatitem_deletename);
                Button chatitem_delete = popupView.findViewById(R.id.chatitem_delete);

                chatitem_deletename.setText(data.get(holder.getAdapterPosition()));

                //获取焦点，保证edittext能输入
                popupWindow.setFocusable(true);
                //外部是否可以点击
                //只有加上它之后，PopupWindow才会对手机的返回按钮有响应：即，点击手机返回按钮，可以关闭PopupWindow；
                // 如果不加setBackgroundDrawable（）将关闭的PopupWindow所在的Activity.
                Drawable drawable = new BitmapDrawable();
                popupWindow.setBackgroundDrawable(drawable);
                popupWindow.setOutsideTouchable(true);

                //popupwidow放在rootview里
                View rootview = LayoutInflater.from(context).inflate(R.layout.chat_fragment,null);
                popupWindow.showAtLocation(rootview, Gravity.CENTER,0,0);


                chatitem_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (System.currentTimeMillis() - firstPressedTime < 2000) {
                            deletename = chatitem_deletename.getText().toString();

                            friends = new ArrayList<>();
                            friend = new Friends();
                            friends = LitePal.where("username = ?",username).find(Friends.class);
                            friend = friends.get(0);
                            num = friend.getFriends();
                            num.remove(deletename);
                            friend.setFriends(num);
                            friend.save();

                            setData(num);

                            friends = new ArrayList<>();
                            friend = new Friends();
                            friends = LitePal.where("username = ?",deletename).find(Friends.class);
                            friend = friends.get(0);
                            num = friend.getFriends();
                            num.remove(username);
                            friend.setFriends(num);
                            friend.save();


                            Toast.makeText(context, "已删除好友"+deletename, Toast.LENGTH_SHORT).show();

                            popupWindow.dismiss();//悬浮框消失
                        } else {
                            Toast.makeText(context, "再按一次退出", Toast.LENGTH_SHORT).show();
                            firstPressedTime = System.currentTimeMillis();
                        }
                    }
                });

                return true;
            }
        });

        //开始聊天
        holder.chatitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
