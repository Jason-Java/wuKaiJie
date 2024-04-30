package com.unite.okhttpdemo.rk.fragment.choosecabinet.fragment;

import android.content.Context;
import android.os.Message;
import android.view.View;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.base.adapter.BaseRecyclerViewAdapter;
import com.unite.okhttpdemo.base.adapter.BaseRecyclerViewHolder;
import com.unite.okhttpdemo.base.fragment.BaseFragment;
import com.unite.okhttpdemo.domain.cabinet.BoxInfo;
import com.unite.okhttpdemo.domain.cabinet.CabinetInfoResponse;
import com.unite.okhttpdemo.util.ToastUtil;

import java.util.List;

/**
 *
 */
public class CabinetAdapter extends BaseRecyclerViewAdapter<CabinetInfoResponse> {


    public CabinetAdapter(int mLayoutID, Context mContext) {
        super(mLayoutID, mContext);
    }

    @Override
    protected void convert(BaseRecyclerViewHolder holder, CabinetInfoResponse bean, int position) {


        holder.getImageView(R.id.cabinet_img).setImageResource(R.mipmap.cabinet_1);
        holder.setText(R.id.cabinet_mc,bean.getBoxInfo().getName());
        holder.setText(R.id.cabinet_syrl,"剩余容量:"+(bean.getTotalHole()-bean.getUsedHole()));
        holder.setText(R.id.cabinet_baifenbi,"("+bean.getUsedHole()+"/"+bean.getTotalHole()+")  ");
        holder.getProgressBar(R.id.cabinet_progress).setMax(bean.getTotalHole());
        holder.getProgressBar(R.id.cabinet_progress).setProgress(bean.getUsedHole());


        //点击柜子
        holder.getLinearLayout(R.id.cabinet_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取所选柜子信息
                CabinetInfoResponse cabinet = mListData.get(holder.getAdapterPosition());
                if (cabinet.getUsedHole() == cabinet.getTotalHole()){
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
