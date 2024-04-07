package com.example.projectone.my.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectone.R;

public class MyEnjoyHoder extends RecyclerView.ViewHolder {
    ImageView myEnjoyIv;
    TextView myEnjoyName;
    TextView myEnjoyDate;
    TextView myEnjoyText;
    Button myEnjoyDelete;

    public MyEnjoyHoder(@NonNull View itemView) {
        super(itemView);
        myEnjoyIv = itemView.findViewById(R.id.myenjoy_iv);
        myEnjoyName = itemView.findViewById(R.id.myenjoy_name);
        myEnjoyDate = itemView.findViewById(R.id.myenjoy_date);
        myEnjoyText = itemView.findViewById(R.id.myenjoy_text);
        myEnjoyDelete = itemView.findViewById(R.id.myenjoy_delete);

    }
}
