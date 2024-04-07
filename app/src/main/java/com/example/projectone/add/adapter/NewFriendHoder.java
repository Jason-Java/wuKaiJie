package com.example.projectone.add.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectone.R;

public class NewFriendHoder extends RecyclerView.ViewHolder {
    ImageView newfriendHeadpicture;
    TextView newfriendName;
    TextView newfriendMytext;
    Button newfriendAgree;
    Button newfriendRefuse;
    TextView newfriendResult;
    TextView newfriendDate;

    public NewFriendHoder(@NonNull View itemView) {
        super(itemView);
        newfriendHeadpicture = itemView.findViewById(R.id.newfriendHeadpicture);
        newfriendName = itemView.findViewById(R.id.newfriendName);
        newfriendMytext = itemView.findViewById(R.id.newfriendMytext);
        newfriendAgree = itemView.findViewById(R.id.newfriendAgree);
        newfriendRefuse = itemView.findViewById(R.id.newfriendRefuse);
        newfriendResult = itemView.findViewById(R.id.newfriendResult);
        newfriendDate = itemView.findViewById(R.id.newfriendDate);
    }
}
