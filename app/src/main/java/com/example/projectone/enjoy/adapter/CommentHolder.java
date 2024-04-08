package com.example.projectone.enjoy.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectone.R;

public class CommentHolder extends RecyclerView.ViewHolder{
    TextView commentitem;

    public CommentHolder(@NonNull View itemView) {
        super(itemView);
        commentitem = itemView.findViewById(R.id.commentitem);
    }
}