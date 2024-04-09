package com.example.projectone.my.adapter;

import android.content.Context;
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
    private List<T> mListData = null;
    /*上下文*/
    private Context mContext = null;
    /*点击事件*/
    private OnItemClickListener mClickListener = null;
    /*单击事件和长单击事件的屏蔽标识*/
    private boolean clickFlag = true;

    private String username;

    /*上下文,布局ID,数据源*/
    public BaseRecyclerViewAdapter(Context context, int layoutId, List<T> data) {
        this.mLayoutID = layoutId;
        this.mListData = data;
        this.mContext = context;
    }

    /*上下文,布局ID*/
    public BaseRecyclerViewAdapter(int mLayoutID, Context mContext,String username) {
        this.mLayoutID = mLayoutID;
        this.mContext = mContext;
        this.username = username;
    }

    /*设置数据源*/
    public void setData(List<T> data) {
        this.mListData = data;
        notifyDataSetChanged();
    }

    /*在任意位置添加 Item */
    public void addData(int position, T data) {
        mListData.add(position, data);
        //如果想使用动画必用
        notifyItemInserted(position);
    }

    /*移除某位置的数据*/
    public void removeData(int position) {
        mListData.remove(position);
        //如果想使用动画必用
        notifyItemRemoved(position);
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
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickFlag) {
                    if (mClickListener != null) {
                        mClickListener.OnItemClick(v, holder.getLayoutPosition());
                    }
                }
                clickFlag = true;
            }
        });
        //单击长按事件回调
        inflate.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mClickListener != null) {
                    mClickListener.OnItemLongClick(v, holder.getLayoutPosition());
                }
                clickFlag = false;
                return false;
            }
        });
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

    /*设置点击事件*/
    public void setOnItemClickListener(OnItemClickListener onClickListener) {
        this.mClickListener = onClickListener;
    }

    public interface OnItemClickListener {
        /*点击事件*/
        void OnItemClick(View view, int position);

        /*长按点击事件*/
        void OnItemLongClick(View view, int position);
    }


}
