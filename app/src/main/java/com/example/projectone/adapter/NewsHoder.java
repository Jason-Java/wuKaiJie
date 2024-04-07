package com.example.projectone.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectone.R;

public class NewsHoder extends RecyclerView.ViewHolder {

    RelativeLayout newsitem;
    ImageView newsitem_iv;
    TextView newsitem_title;
    TextView newsitem_content;
    TextView newsitem_date;


    public NewsHoder(@NonNull View itemView) {
        super(itemView);
        newsitem = itemView.findViewById(R.id.newsitem);
        newsitem_iv = itemView.findViewById(R.id.newsitem_iv);
        newsitem_title = itemView.findViewById(R.id.newsitem_title);
        newsitem_content = itemView.findViewById(R.id.newsitem_content);
        newsitem_date = itemView.findViewById(R.id.newsitem_date);

    }
}
