package com.unite.okhttpdemo.rk.fragment.sure;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.base.fragment.BaseFragment;
import com.unite.okhttpdemo.databinding.ActivityRkBinding;
import com.unite.okhttpdemo.databinding.FragmentSureBinding;
import com.unite.okhttpdemo.rk.RKActivity;


public class SureFragment extends BaseFragment<FragmentSureBinding> {


    @Override
    protected FragmentSureBinding onCreateViewBinding(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return FragmentSureBinding.inflate(getLayoutInflater());
    }


    @Override
    protected void initViews() {
        super.initViews();
        getBinding().sureSj.setText(RKActivity.sj.getCommonName());
        getBinding().sureCas.setText(RKActivity.sj.getCAS());
        getBinding().sureBm.setText(RKActivity.sj.getAlias());
        getBinding().sureGzmc.setText(RKActivity.cabinet.getBoxInfo().getName());
        getBinding().sureCtmc.setText(RKActivity.drawer.getContainer().getName());
        getBinding().sureCtrl.setText("单元概述："+RKActivity.drawer.getTotalHole());
        getBinding().sureCtzb.setText("存量比例："+RKActivity.drawer.getUsedHole()+"/"+RKActivity.drawer.getTotalHole());
    }

    @Override
    protected void initListeners() {
        super.initListeners();


        getBinding().sureGzImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = new Message();
                message.what = 50;
                handler.sendMessage(message);
            }
        });
    }
}