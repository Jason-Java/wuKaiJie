package com.example.projectone.my.adapter;

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
import com.example.projectone.table.Enjoy;
import com.example.projectone.table.Friends;
import com.example.projectone.table.User;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyEnjoyAdapter extends RecyclerView.Adapter<MyEnjoyHoder> {
    Context context;
    List<Enjoy> enjoys;
    Enjoy enjoy;
    String username;
    List<User> users;
    User user;

    String date;

    View view;
    private long firstPressedTime;

    public MyEnjoyAdapter(Context context, String username) {
        this.context = context;
        this.username = username;
    }

    public void setData(List<Enjoy> enjoys){
        this.enjoys = enjoys;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyEnjoyHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = View.inflate(context, R.layout.enjoy_item_1,null);
        MyEnjoyHoder myEnjoyHoder = new MyEnjoyHoder(view);
        return myEnjoyHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyEnjoyHoder holder, int position) {
        enjoy = enjoys.get(position);

        users = LitePal.where("username = ?",username).find(User.class);
        user = users.get(0);
        if (user.getHeadpicture() == null){
            Glide.with(context).load(R.drawable.defaultheadpicture).into(holder.myEnjoyIv);
        }else{
            // 加载本地图片
            try {
                Uri uri = Uri.parse(user.getHeadpicture());
                Glide.with(context).load(uri).into(holder.myEnjoyIv);
            }catch (Exception e){
                Toast.makeText(context,"本地资源找不到", Toast.LENGTH_SHORT).show();
                Glide.with(context).load(R.drawable.defaultheadpicture).into(holder.myEnjoyIv);
            }
        }

        holder.myEnjoyText.setText(enjoy.getText());
        holder.myEnjoyName.setText(username);
        
        date = enjoy.getDate();
        holder.myEnjoyDate.setText(date);


        //删除按钮
        holder.myEnjoyDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (System.currentTimeMillis() - firstPressedTime < 2000) {
                    LitePal.deleteAll(Enjoy.class,"username = ? and date = ?",username,enjoys.get(holder.getAdapterPosition()).getDate());
                    Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                    enjoys = LitePal.where("username = ?",username).order("date desc").find(Enjoy.class);
                    setData(enjoys);
                } else {
                    Toast.makeText(context, "再按一次删除说说", Toast.LENGTH_SHORT).show();
                    firstPressedTime = System.currentTimeMillis();
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return enjoys.size();
    }
}
