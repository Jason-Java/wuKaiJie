package com.example.projectone.enjoy.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectone.DetailActivity;
import com.example.projectone.manage.CommentManage;
import com.example.projectone.R;
import com.example.projectone.table.Comment;
import com.example.projectone.table.Enjoy;
import com.example.projectone.table.Like;
import com.example.projectone.table.User;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EnjoyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    String username;

    List<Enjoy> enjoys = new ArrayList<>();
    Enjoy enjoy;
    int type;

    View view;

    RootHoder rootHoder;
    UserHoder userHoder;
    String date;
    Intent intent;
    List<Like> likes = new ArrayList<>();
    Like like;
    int likestate;
    int likenum;
    String likename;

    String commentText;
    List<Comment> comments = new ArrayList<>();
    Comment comment;
    String commentName;
    Long time;
    Date commentTime;
    String commentDate;
    SimpleDateFormat format;
    private List<User> users = new ArrayList<>();
    private User user;
    private CommentAdapter commentAdapter;

    public EnjoyAdapter(Context context, String username) {
        this.context = context;
        this.username = username;
    }

    public void setData(List<Enjoy> enjoys){
        this.enjoys = enjoys;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        //根据你的条件，返回不同的type
        enjoy = enjoys.get(position);
        type = enjoy.getType();
        if (type == 0){
            return 0;
        }else {
            return 1;
        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = null;
        if (viewType == 0){//系统root
            view = View.inflate(context, R.layout.enjoyitem_root,null);
            rootHoder = new RootHoder(view);
            return  rootHoder;
        }else{//用户
            view = View.inflate(context, R.layout.enjoyitem_user,null);
            userHoder = new UserHoder(view);
            return  userHoder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        enjoy = enjoys.get(position);

        switch (getItemViewType(position)){
            case 0://系统
                rootHoder = (RootHoder) holder;

                rootHoder.enjoyRootTitle.setText(enjoy.getTitle());
                rootHoder.enjoyRootText.setText(enjoy.getText());
                rootHoder.enjoyRootLikesNum.setText(enjoy.getLikes()+"");

                date = enjoy.getDate();
                rootHoder.enjoyRootDate.setText(date);
                //点击文本跳转详情页面
                rootHoder.enjoyRootText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("url",enjoy.getUrl());
                        context.startActivity(intent);
                    }
                });


                //点赞初始化
                likes = LitePal.where("username = ? and likename = ? and enjoydate = ?",username,"root",date).find(Like.class);
                if (likes.size() == 0){//未点赞过
                    like = new Like();
                    like.setUsername(username);
                    like.setLikename("root");
                    like.setLike(0);
                    like.setEnjoydate(date);
                    like.save();
                }else {//有点赞记录
                    like = likes.get(0);
                    if (like.getLike() == 1){
                        rootHoder.enjoyRootLikesImg.setSelected(true);
                    }
                }

                //点赞点击
                rootHoder.enjoyRootLikesImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        date = enjoys.get(holder.getAdapterPosition()).getDate();
                        likename = "root";
                        likes = LitePal.where("username = ? and likename = ? and enjoydate = ?",username,likename,date).find(Like.class);
                        like = likes.get(0);
                        likestate = like.getLike();
                        enjoy = enjoys.get(holder.getAdapterPosition());
                        likenum = enjoy.getLikes();
                        if (likestate == 0){//本来未点赞
                            rootHoder.enjoyRootLikesImg.setSelected(true);
                            //单个记录
                            like.setLike(1);
                            like.save();
                            //总记录加一
                            likenum += 1;
                            enjoy.setLikes(likenum);
                            enjoy.save();
                        }else{//本来点赞
                            rootHoder.enjoyRootLikesImg.setSelected(false);
                            //单个记录
                            like.setLike(0);
                            like.save();
                            //总记录加一
                            likenum -= 1;
                            enjoy.setLikes(likenum);
                            enjoy.save();
                        }
                        //刷新
                        enjoys = LitePal.order("date desc").find(Enjoy.class);
                        setData(enjoys);
                    }
                });

                //评论初始化
                commentAdapter = new CommentAdapter(context,username);
                rootHoder.enjoyRootRecyclerView.setAdapter(commentAdapter);
                rootHoder.enjoyRootRecyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
//                rootHoder.enjoyRootRecyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
                comments = CommentManage.FindALlComment("root",date);
                commentAdapter.setData(comments);

                //发送评论
                rootHoder.enjoyRootBt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //评论内容获取
                        commentText = ((RootHoder) holder).enjoyRootEt.getText().toString();
                        if (commentText.length() != 0){
                            //username已存在
                            //commentname获取
                            enjoy = enjoys.get(holder.getAdapterPosition());
                            commentName = enjoy.getUsername();
                            //分享发布时间获取,String
                            date = enjoy.getDate();
                            //评论时间获取
                            time = System.currentTimeMillis();
                            commentTime = new Date(time);
                            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            commentDate = format.format(commentTime);
                            Toast.makeText(context, ""+commentText, Toast.LENGTH_SHORT).show();
                            CommentManage.AddComment(username,commentName,date,commentDate,commentText);

                            //edittext清空
                            ((RootHoder) holder).enjoyRootEt.setText("");
                            //查找给评论的所有评论
                            comments = CommentManage.FindALlComment(commentName,date);
                            //更新评论
                            commentAdapter.setData(comments);
                            notifyDataSetChanged();
                        }
                    }
                });


                break;
            case 1://用户
                userHoder = (UserHoder) holder;

                users = LitePal.where("username = ?",enjoy.getUsername()).find(User.class);
                user = users.get(0);
                if (user.getHeadpicture() == null){
                    Glide.with(context).load(R.drawable.defaultheadpicture).into(userHoder.enjoyUserImg);
                }else{
                    // 加载本地图片
                    try {
                        Uri uri = Uri.parse(user.getHeadpicture());
                        Glide.with(context).load(uri).into(userHoder.enjoyUserImg);
                    }catch (Exception e){
                        Glide.with(context).load(R.drawable.defaultheadpicture).into(userHoder.enjoyUserImg);
                    }
                }
                userHoder.enjoyUserName.setText(enjoy.getUsername());
                date = enjoy.getDate();
                userHoder.enjoyUserDate.setText(date);
                userHoder.enjoyUserText.setText(enjoy.getText());
                userHoder.enjoyUserLikesNum.setText(enjoy.getLikes()+"");

                //点赞初始化
                likename = enjoy.getUsername();
                likes = LitePal.where("username = ? and likename = ? and enjoydate = ?",username,likename,date).find(Like.class);
                if (likes.size() == 0){//未点赞过
                    like = new Like();
                    like.setUsername(username);
                    like.setLikename(likename);
                    like.setLike(0);
                    like.setEnjoydate(date);
                    like.save();
                }else {//有点赞记录
                    like = likes.get(0);
                    if (like.getLike() == 1){
                        userHoder.enjoyUserLikesImg.setSelected(true);
                    }
                }

                //点赞点击
                userHoder.enjoyUserLikesImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        date = enjoys.get(holder.getAdapterPosition()).getDate();
                        likename = enjoys.get(holder.getAdapterPosition()).getUsername();
                        likes = LitePal.where("username = ? and likename = ? and enjoydate = ?",username,likename,date).find(Like.class);
                        like = likes.get(0);
                        likestate = like.getLike();
                        enjoy = enjoys.get(holder.getAdapterPosition());
                        likenum = enjoy.getLikes();
                        if (likestate == 0){//本来未点赞
                            userHoder.enjoyUserLikesImg.setSelected(true);
                            //单个记录
                            like.setLike(1);
                            like.save();
                            //总记录加一
                            likenum += 1;
                            enjoy.setLikes(likenum);
                            enjoy.save();
                        }else{//本来点赞
                            userHoder.enjoyUserLikesImg.setSelected(false);
                            //单个记录
                            like.setLike(0);
                            like.save();
                            //总记录加一
                            likenum -= 1;
                            enjoy.setLikes(likenum);
                            enjoy.save();
                        }
                        //刷新
                        enjoys = LitePal.order("date desc").find(Enjoy.class);
                        setData(enjoys);
                    }
                });

                //评论初始化
                commentAdapter = new CommentAdapter(context,username);
                userHoder.enjoyUserRecyclerView.setAdapter(commentAdapter);
                userHoder.enjoyUserRecyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
//                rootHoder.enjoyRootRecyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
                comments = CommentManage.FindALlComment(likename,date);
                commentAdapter.setData(comments);

                //发送评论
                userHoder.enjoyUserBt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //评论内容获取
                        commentText = ((UserHoder) holder).enjoyUserEt.getText().toString();
                        if (commentText.length() != 0){
                            //username已存在
                            //commentname获取
                            enjoy = enjoys.get(holder.getAdapterPosition());
                            commentName = enjoy.getUsername();
                            //分享发布时间获取,String
                            date = enjoy.getDate();
                            //评论时间获取
                            time = System.currentTimeMillis();
                            commentTime = new Date(time);
                            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            commentDate = format.format(commentTime);
                            Toast.makeText(context, ""+commentText, Toast.LENGTH_SHORT).show();
                            CommentManage.AddComment(username,commentName,date,commentDate,commentText);

                            //edittext清空
                            ((UserHoder) holder).enjoyUserEt.setText("");
                            //查找给评论的所有评论
                            comments = CommentManage.FindALlComment(commentName,date);
                            //更新评论
                            commentAdapter.setData(comments);
                            notifyDataSetChanged();
                        }
                    }
                });


        }
    }

    @Override
    public int getItemCount() {
        return enjoys.size();
    }
}
