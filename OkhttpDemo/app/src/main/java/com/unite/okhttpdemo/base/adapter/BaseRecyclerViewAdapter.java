package com.unite.okhttpdemo.base.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    /*布局ID*/
    private int mLayoutID = -1;
    /*数据源*/
    public List<T> mListData = null;
    /*上下文*/
    private Context mContext = null;

    public Handler handler;

    /*上下文,布局ID,数据源*/
    public BaseRecyclerViewAdapter(Context context, int layoutId, List<T> data) {
        this.mLayoutID = layoutId;
        this.mListData = data;
        this.mContext = context;
    }

    /*上下文,布局ID*/
    public BaseRecyclerViewAdapter(int mLayoutID, Context mContext) {
        this.mLayoutID = mLayoutID;
        this.mContext = mContext;
    }

    /*设置数据源*/
    public void setData(List<T> data) {
        this.mListData = data;
        notifyDataSetChanged();
    }

    //设置通信
    public void setHandler(Handler handler){
        this.handler = handler;
    }


    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(mLayoutID, parent, false);
        //设置背景recycleview
//        inflate.setBackgroundResource(R.drawable.recycle_item);
        final BaseRecyclerViewHolder holder = new BaseRecyclerViewHolder(inflate, mContext);


        /*单击事件回调*/
//        getLayoutPosition 返回布局中最新的计算位置，和用户所见到的位置一致，当做用户输入（例如点击事件）的时候考虑使用
//        getAdapterPosition 返回数据在Adapter中的位置（也许位置的变化还未来得及刷新到布局中），当使用Adapter的时候（例如调用Adapter的notify相关方法时）考虑使用
        return holder;
    }

    /*绑定ViewHolder*/
    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder holder, int position) {
        convert(holder, mListData.get(position),position);
    }

    protected abstract void convert(BaseRecyclerViewHolder holder, T bean,int position);

    /*获取条目数量*/
    @Override
    public int getItemCount() {
        return (mListData == null || mListData.isEmpty()) ? 0 : mListData.size();
    }



}
