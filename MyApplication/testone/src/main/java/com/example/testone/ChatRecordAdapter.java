package com.example.testone;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ChatRecordAdapter extends RecyclerView.Adapter<ChatRecordHoder> {
    private List<ChatRecord> data = new ArrayList<>();
    private Context context;

    public ChatRecordAdapter(List<ChatRecord> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatRecordHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.chat_item, null);
        ChatRecordHoder chatRecordHoder = new ChatRecordHoder(view);
        return chatRecordHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRecordHoder holder, int position) {
        ChatRecord chatRecord = data.get(position);
        holder.chatitem_name.setText(chatRecord.getName());
        holder.chatitem_chat.setText(chatRecord.getFinalchat());
        holder.chatitem_date.setText(chatRecord.getDate());
        holder.chatitem_cb.setChecked(chatRecord.getCb());

        Glide.with(context).load(data.get(position).getImg()).into(holder.chatitem_iv);

        //点击事件放在adapter中使用，也可以写个接口在activity中调用
        //方法一：在adapter中设置点击事件
        //是否选中更新data
        holder.chatitem_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int address = holder.getAdapterPosition();//获取位置
                ChatRecord change = data.get(address);//获取位置信息
                if (holder.chatitem_cb.isChecked()){
                    change.setCb(true);
                    data.set(address,change);
                }else{
                    change.setCb(false);
                    data.set(address,change);
                }
            }
        });

        //长按item出现悬浮框

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
