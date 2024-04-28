package com.unite.okhttpdemo.rk.fragment.choosemode.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.base.adapter.BaseRecyclerViewAdapter;
import com.unite.okhttpdemo.base.adapter.BaseRecyclerViewHolder;
import com.unite.okhttpdemo.table.shiji.ShiJi;

import java.util.List;

/**
 *
 */
public class RKModeAdapter extends BaseRecyclerViewAdapter<ShiJi> {

    ShiJi sj;

    public RKModeAdapter(Context context, int layoutId, List<ShiJi> data) {
        super(context, layoutId, data);
    }

    public RKModeAdapter(int mLayoutID, Context mContext) {
        super(mLayoutID, mContext);
    }

    @Override
    protected void convert(BaseRecyclerViewHolder holder, ShiJi bean, int position) {

        holder.setText(R.id.item_sj_mc,bean.getCommonName());

        String text_bm = "别名:"+bean.getAlias();
        holder.setText(R.id.item_sj_bm,setBold(text_bm,0,3));

        String text_cas = "CAS:"+bean.getCAS();
        holder.setText(R.id.item_sj_cas,setBold(text_cas,0,4));

        String text_cd = "纯度:"+bean.getConsistency();
        holder.setText(R.id.item_sj_cd,setBold(text_cd,0,3));

        String text_gg = "规格:"+bean.getModel();
        holder.setText(R.id.item_sj_gg,setBold(text_gg,0,3));


        holder.getButton(R.id.item_sj_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sj = mListData.get(holder.getAdapterPosition());
                Message message = new Message();
                message.what = 1;
                message.obj = sj;
                handler.sendMessage(message);
            }
        });



    }


    public SpannableString setBold(String text,int start,int end){
        SpannableString spannableString = new SpannableString(text);
        // 设置“这是加粗的文字”为加粗
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(boldSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
