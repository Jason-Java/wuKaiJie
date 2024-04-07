package com.example.projectone.enjoy.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectone.R;

public class RootHoder extends RecyclerView.ViewHolder {
    TextView enjoyRootTitle;
    TextView enjoyRootText;
    TextView enjoyRootDate;
    ImageButton enjoyRootLikesImg;
    TextView enjoyRootLikesNum;
    RecyclerView enjoyRootRecyclerView;
    Button enjoyRootBt;
    EditText enjoyRootEt;

    public RootHoder(@NonNull View itemView) {
        super(itemView);
        enjoyRootTitle = itemView.findViewById(R.id.enjoyRootTitle);
        enjoyRootText = itemView.findViewById(R.id.enjoyRootText);
        enjoyRootDate = itemView.findViewById(R.id.enjoyRootDate);
        enjoyRootLikesImg = itemView.findViewById(R.id.enjoyRootLikesImg);
        enjoyRootLikesNum = itemView.findViewById(R.id.enjoyRootLikesNum);
        enjoyRootRecyclerView = itemView.findViewById(R.id.enjoyRootRecyclerView);
        enjoyRootBt = itemView.findViewById(R.id.enjoyRootBt);
        enjoyRootEt = itemView.findViewById(R.id.enjoyRootEt);
    }
}
