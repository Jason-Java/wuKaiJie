package com.example.projectone.buy;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectone.DetailActivity;
import com.example.projectone.R;
import com.example.projectone.table.Buy;

import java.util.ArrayList;
import java.util.List;

public class BuyAdapter extends RecyclerView.Adapter<BuyAdapter.BuyHolder> {
    BuyHolder buyHolder;
    View view;

    String username;
    Context context;

    List<Buy> buys = new ArrayList<>();
    Buy buy;

    String buyImage;
    String buyName;
    int buyPrice;
    String buyUrl;
    String buyShop;

    Intent intent;

    public void setData(List<Buy> buys){
        this.buys = buys;
        notifyDataSetChanged();
    }

    public BuyAdapter(String username, Context context) {
        this.username = username;
        this.context = context;
    }

    @NonNull
    @Override
    public BuyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = View.inflate(context,R.layout.buyitem,null);
        buyHolder = new BuyHolder(view);
        return buyHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BuyHolder holder, int position) {
        buy = buys.get(position);

        buyImage = buy.getBuyImage();
        buyName = buy.getBuyName();
        buyPrice = buy.getBuyPrice();
        buyUrl = buy.getBuyUrl();
        buyShop = buy.getBuyShop();
        //初始化
        Glide.with(context).load(buyImage).into(holder.buyIv);
        holder.buyName.setText(buyName);
        holder.buyPrice.setText(buyPrice+"元");

        //点击图片进入详情页面
        holder.buyIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context, DetailActivity.class);
                intent.putExtra("url",buys.get(holder.getAdapterPosition()).getBuyUrl());
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        //点击收藏or已收藏
        holder.buyCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return buys.size();
    }

    public class BuyHolder extends RecyclerView.ViewHolder{
        ImageView buyIv;
        TextView buyName;
        TextView buyPrice;
        Button buyCollection;

        public BuyHolder(@NonNull View itemView) {
            super(itemView);
            buyIv = itemView.findViewById(R.id.buyIv);
            buyName = itemView.findViewById(R.id.buyName);
            buyPrice = itemView.findViewById(R.id.buyPrice);
            buyCollection = itemView.findViewById(R.id.buyCollection);
        }
    }
}
