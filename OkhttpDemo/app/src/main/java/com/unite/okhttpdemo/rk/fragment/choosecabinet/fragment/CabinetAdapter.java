package com.unite.okhttpdemo.rk.fragment.choosecabinet.fragment;

import android.content.Context;
import android.os.Message;
import android.view.View;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.base.adapter.BaseRecyclerViewAdapter;
import com.unite.okhttpdemo.base.adapter.BaseRecyclerViewHolder;
import com.unite.okhttpdemo.base.fragment.BaseFragment;
import com.unite.okhttpdemo.table.Cabinet;
import com.unite.okhttpdemo.util.ToastUtil;

import java.util.List;

/**
 *
 */
public class CabinetAdapter extends BaseRecyclerViewAdapter<Cabinet> {


    public CabinetAdapter(int mLayoutID, Context mContext) {
        super(mLayoutID, mContext);
    }

    @Override
    protected void convert(BaseRecyclerViewHolder holder, Cabinet bean, int position) {
        if (bean.getXh() == 1){
            holder.getImageView(R.id.cabinet_img).setImageResource(R.mipmap.cabinet_1);
        }else {
            holder.getImageView(R.id.cabinet_img).setImageResource(R.mipmap.cabinet_2);
        }

        holder.setText(R.id.cabinet_mc,bean.getMc());
        holder.setText(R.id.cabinet_syrl,"剩余容量:"+(bean.getSum()-bean.getNum()));
        holder.setText(R.id.cabinet_baifenbi,"("+bean.getNum()+"/"+bean.getSum()+")  ");
        holder.getProgressBar(R.id.cabinet_progress).setMax(bean.getSum());
        holder.getProgressBar(R.id.cabinet_progress).setProgress(bean.getNum());


        //点击柜子
        holder.getLinearLayout(R.id.cabinet_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cabinet cabinet = mListData.get(holder.getAdapterPosition());
                if (cabinet.getNum() == cabinet.getSum()){
                    ToastUtil.errorShortToast("柜子容量已满");
                }else {
                    //传递选择的柜子,childHandler
                    Message message = new Message();
                    message.what = 0;
                    message.obj = cabinet;
                    handler.sendMessage(message);
                }
            }
        });
    }
}
