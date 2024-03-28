package com.example.testone;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ChatRecordAdapter extends RecyclerView.Adapter<ChatRecordHoder> {
    private List<ChatRecord> data = new ArrayList<>();
    private Context context;
    private PopupWindow mPopWindow;

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
        holder.chatitem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                View popupView = LayoutInflater.from(context).inflate(R.layout.chatitem_longclick,null);
                mPopWindow = new PopupWindow(popupView);
                mPopWindow.setWidth(400);
                mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

                TextView chatitem_longname = popupView.findViewById(R.id.chatitem_longname);
                EditText chatitem_longtext = popupView.findViewById(R.id.chatitem_longtext);
                Button chatitem_longsave = popupView.findViewById(R.id.chatitem_longsave);

                //设置名字
                int address = holder.getAdapterPosition();
                ChatRecord longitem = data.get(address);
                chatitem_longname.setText(longitem.getName());
                //设置备注
                chatitem_longtext.setText(longitem.getFinalchat());
                //获取焦点，保证edittext能输入
                mPopWindow.setFocusable(true);
                //外部是否可以点击
                //只有加上它之后，PopupWindow才会对手机的返回按钮有响应：即，点击手机返回按钮，可以关闭PopupWindow；
                // 如果不加setBackgroundDrawable（）将关闭的PopupWindow所在的Activity.
                mPopWindow.setBackgroundDrawable(new BitmapDrawable());
                mPopWindow.setOutsideTouchable(true);
                //保存
                chatitem_longsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String text = chatitem_longtext.getText().toString();
                        longitem.setFinalchat(text);
                        holder.chatitem_chat.setText(text);
                        mPopWindow.dismiss();//悬浮框消失
                    }
                });

                //popupwidow放在rootview里
                View rootview = LayoutInflater.from(context).inflate(R.layout.chat_fragment,null);
                mPopWindow.showAtLocation(rootview, Gravity.CENTER,0,0);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
