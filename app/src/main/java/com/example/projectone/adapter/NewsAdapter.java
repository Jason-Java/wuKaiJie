package com.example.projectone.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectone.DetailActivity;
import com.example.projectone.R;
import com.example.projectone.table.News;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsHoder> {

    private List<News> data = new ArrayList<>();
    private Context context;
    Intent intent;
    Date date;
    DateFormat format;

    public NewsAdapter(List<News> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.news_listitem, null);
        NewsHoder newsHoder = new NewsHoder(view);
        return newsHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHoder holder, int position) {
        News news = data.get(position);
        holder.newsitem_title.setText(news.getTitle());
        holder.newsitem_content.setText("  "+news.getContent());
        date = news.getDate();
        format = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            holder.newsitem_date.setText(format.format(date));
        }catch (Exception e){
            Log.e("looknews", "时间格式转换失败"+e);
        }

        Glide.with(context).load(data.get(position).getImage()).into(holder.newsitem_iv);

        //点击newsitem转到新闻详细页面
        holder.newsitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context, DetailActivity.class);
                intent.putExtra("url",news.getUrl());
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
