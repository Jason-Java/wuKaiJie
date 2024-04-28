package com.unite.okhttpdemo.rk.fragment.choosecabinet.fragment;

import android.content.Context;
import android.os.Message;
import android.view.View;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.base.adapter.BaseRecyclerViewAdapter;
import com.unite.okhttpdemo.base.adapter.BaseRecyclerViewHolder;
import com.unite.okhttpdemo.table.Cabinet;
import com.unite.okhttpdemo.table.Drawer;
import com.unite.okhttpdemo.table.SjResult;

import java.util.HashMap;

/**
 *
 */
public class DrawerAdapter extends BaseRecyclerViewAdapter<Drawer> {
    public DrawerAdapter(int mLayoutID, Context mContext) {
        super(mLayoutID, mContext);
    }

    @Override
    protected void convert(BaseRecyclerViewHolder holder, Drawer bean, int position) {

        holder.setText(R.id.Drawer_mc,bean.getMc());
        holder.setText(R.id.Drawer_syrl,"剩余容量:"+(bean.getSum()-bean.getNum()));
        holder.setText(R.id.Drawer_baifenbi,"("+bean.getNum()+"/"+bean.getSum()+")  ");
        holder.getProgressBar(R.id.Drawer_progress).setMax(bean.getSum());
        holder.getProgressBar(R.id.Drawer_progress).setProgress(bean.getNum());

        holder.getLinearLayout(R.id.drawer_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SjResult sjResult = new SjResult();
                sjResult.setDrawer(mListData.get(holder.getAdapterPosition()));
                sjResult.setSj(null);
                sjResult.setCabinet(null);
                Message message = new Message();
                message.what = 1;
                message.obj = sjResult;
                handler.sendMessage(message);
            }
        });


    }
}
