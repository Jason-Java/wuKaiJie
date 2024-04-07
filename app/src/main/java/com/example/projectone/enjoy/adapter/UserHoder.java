package com.example.projectone.enjoy.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectone.R;

public class UserHoder extends RecyclerView.ViewHolder{
    ImageView enjoyUserImg;
    TextView enjoyUserName;
    TextView enjoyUserText;
    TextView enjoyUserDate;
    ImageButton enjoyUserLikesImg;
    TextView enjoyUserLikesNum;
    RecyclerView enjoyUserRecyclerView;
    Button enjoyUserBt;
    EditText enjoyUserEt;

    public UserHoder(@NonNull View itemView) {
        super(itemView);
        enjoyUserImg = itemView.findViewById(R.id.enjoyUserImg);
        enjoyUserName = itemView.findViewById(R.id.enjoyUserName);
        enjoyUserText = itemView.findViewById(R.id.enjoyUserText);
        enjoyUserDate = itemView.findViewById(R.id.enjoyUserDate);
        enjoyUserLikesImg = itemView.findViewById(R.id.enjoyUserLikesImg);
        enjoyUserLikesNum = itemView.findViewById(R.id.enjoyUserLikesNum);
        enjoyUserRecyclerView = itemView.findViewById(R.id.enjoyUserRecyclerView);
        enjoyUserBt = itemView.findViewById(R.id.enjoyUserBt);
        enjoyUserEt = itemView.findViewById(R.id.enjoyUserEt);
    }
}
