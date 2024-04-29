package com.unite.okhttpdemo.rk.fragment.choosemode.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.PopupWindow;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.base.adapter.BaseRecyclerViewAdapter;
import com.unite.okhttpdemo.base.adapter.BaseRecyclerViewHolder;
import com.unite.okhttpdemo.table.shiji.ShiJi;

import java.util.Locale;

/**
 *
 */
public class SearchAdapter extends BaseRecyclerViewAdapter<ShiJi> {
    String searchtext;
    PopupWindow popupWindow;

    public SearchAdapter(int mLayoutID, Context mContext,String searchtext,PopupWindow popupWindow) {
        super(mLayoutID, mContext);
        this.searchtext = searchtext;
        this.popupWindow = popupWindow;

    }

    @Override
    public void setHandler(Handler handler) {
        super.setHandler(handler);
    }

    @Override
    protected void convert(BaseRecyclerViewHolder holder, ShiJi bean, int position) {

        searchtext = searchtext.toLowerCase(Locale.ROOT);
        String text = bean.getCommonName()+";"+bean.getCAS();
        String checktext = text.toLowerCase(Locale.ROOT);
        int init = checktext.indexOf(searchtext);
        StringBuffer stringBuilder=new StringBuffer(text);
        stringBuilder.insert(init+searchtext.length(),"</font>");
        stringBuilder.insert(init,"<font color = '#FF0000'>");

        holder.getTextView(R.id.shiji_mc).setText(Html.fromHtml(stringBuilder.toString()));


        holder.getTextView(R.id.shiji_mc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShiJi sj = mListData.get(holder.getAdapterPosition());
                String mc = sj.getCommonName();
                Message message = new Message();
                message.what = 0;
                message.obj = mc;
                handler.sendMessage(message);
                //关闭悬浮窗
                popupWindow.dismiss();
            }
        });

    }
}
