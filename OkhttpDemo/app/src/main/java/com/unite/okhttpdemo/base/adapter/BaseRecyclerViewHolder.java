package com.unite.okhttpdemo.base.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    private View inflate;
    private Context mContext;

    public BaseRecyclerViewHolder(@NonNull View itemView, Context mContext) {
        super(itemView);
        this.inflate = itemView;
        this.mContext = mContext;
    }

    /*回调函数*/
    public interface CallBack<T> {
        void run(T t);
    }


//    设置Textview文本内容
    public void setText(int id,String text){
        TextView tx = (TextView) inflate.findViewById(id);
        tx.setText(text);
    }

    public void setText(int id, SpannableString text){
        TextView tx = (TextView) inflate.findViewById(id);
        tx.setText(text);
    }

    //获取Textview的文字信息
    public String getText(int tvID){
        TextView tx = (TextView) inflate.findViewById(tvID);
        return tx.getText().toString();
    }

    //获取Textview的文字信息
    public ProgressBar getProgressBar(int id){
        ProgressBar progressBar = (ProgressBar) inflate.findViewById(id);
        return progressBar;
    }

    //获取控件
    public <T> T get(int id){
        T img = (T) inflate.findViewById(id);
        return img;
    }

    /*设置CheckBox 控件*/
    public void setCheckBox(int cbID, boolean cb, CallBack<CheckBox> callBack) {
        CheckBox cbox = (CheckBox) inflate.findViewById(cbID);
        cbox.setChecked(cb);
        if (callBack != null) {
            callBack.run(cbox);
        }
    }

    /*设置CheckBox 控件*/
    public CheckBox setCheckBox(int cbID) {
        CheckBox cbox = (CheckBox) inflate.findViewById(cbID);

        return  cbox;
    }

    /*设置CheckBox 控件*/
    public void setCheckBox(int cbID, boolean cb) {
        CheckBox cbox = (CheckBox) inflate.findViewById(cbID);
        cbox.setChecked(cb);}

    /*设置Button 控件*/
    public Button getButton(int btnID) {
        Button btn = (Button) inflate.findViewById(btnID);
        return btn;
    }

    /*设置imageview 控件*/
    public ImageView getImageView (int imgID) {
        ImageView  img = (ImageView ) inflate.findViewById(imgID);
        return img;
    }

    /*设置Button 属性*/
    public void setButton(int btnID,String text,int backgroundcolor) {
        Button btn = (Button) inflate.findViewById(btnID);
        btn.setText(text);
        btn.setBackgroundResource(backgroundcolor);
    }

    /*设置ImageButton 控件*/
    public void setImageButton(int ibtnID, CallBack<ImageButton> callBack) {
        ImageButton ibtn = (ImageButton) inflate.findViewById(ibtnID);
        if (callBack != null) {
            callBack.run(ibtn);
        }
    }

    /*设置LinearLayout 控件*/
    public LinearLayout getLinearLayout(int layoutID) {
        LinearLayout layout = (LinearLayout) inflate.findViewById(layoutID);
        return layout;
    }

    /*设置RelativeLayout 控件*/
    public void setRelativeLayout(int layoutID, CallBack<RelativeLayout> callBack) {
        RelativeLayout layout = (RelativeLayout) inflate.findViewById(layoutID);
        if (callBack != null) {
            callBack.run(layout);
        }
    }

    /*设置GridView 控件*/
    public void setGridView(int gridID, CallBack<GridView> callBack) {
        GridView grid = (GridView) inflate.findViewById(gridID);
        if (callBack != null) {
            callBack.run(grid);
        }
    }

    /*设置RecyclerView 控件*/
    public RecyclerView setRecyclerView(int id) {
        RecyclerView grid = (RecyclerView) inflate.findViewById(id);
        return grid;
    }
}
