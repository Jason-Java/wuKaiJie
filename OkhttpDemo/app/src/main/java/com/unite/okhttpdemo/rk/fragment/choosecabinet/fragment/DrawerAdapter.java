package com.unite.okhttpdemo.rk.fragment.choosecabinet.fragment;

import android.content.Context;
import android.os.Message;
import android.view.View;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.base.adapter.BaseRecyclerViewAdapter;
import com.unite.okhttpdemo.base.adapter.BaseRecyclerViewHolder;
import com.unite.okhttpdemo.domain.cabinet.Drawer;


import java.util.HashMap;

/**
 *
 */
public class DrawerAdapter extends BaseRecyclerViewAdapter<com.unite.okhttpdemo.domain.cabinet.Drawer> {
    public DrawerAdapter(int mLayoutID, Context mContext) {
        super(mLayoutID, mContext);
    }

    @Override
    protected void convert(BaseRecyclerViewHolder holder, com.unite.okhttpdemo.domain.cabinet.Drawer bean, int position) {
        holder.setText(R.id.Drawer_mc,bean.getContainer().getName());
        holder.setText(R.id.Drawer_syrl,"剩余容量:"+(bean.getTotalHole()-bean.getUsedHole()));
        holder.setText(R.id.Drawer_baifenbi,"("+bean.getUsedHole()+"/"+bean.getTotalHole()+")  ");
        holder.getProgressBar(R.id.Drawer_progress).setMax(bean.getTotalHole());
        holder.getProgressBar(R.id.Drawer_progress).setProgress(bean.getUsedHole());

        holder.getLinearLayout(R.id.drawer_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawer drawer = mListData.get(holder.getAdapterPosition());
                Message message = new Message();
                message.what = 1;
                message.obj = drawer;
                handler.sendMessage(message);
            }
        });
    }


}
