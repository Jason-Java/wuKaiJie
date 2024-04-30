package com.unite.okhttpdemo.rk.fragment.choosecabinet.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.base.fragment.BaseFragment;
import com.unite.okhttpdemo.databinding.FragmentDrawerBinding;
import com.unite.okhttpdemo.domain.cabinet.CabinetInfoResponse;

import java.util.List;

public class DrawerFragment extends BaseFragment<FragmentDrawerBinding> {


    @Override
    protected FragmentDrawerBinding onCreateViewBinding(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return FragmentDrawerBinding.inflate(getLayoutInflater());
    }

    CabinetInfoResponse cabinet;
    DrawerAdapter drawerAdapter;

    public DrawerFragment(CabinetInfoResponse cabinet) {
        this.cabinet = cabinet;
    }

    @Override
    protected void initViews() {
        super.initViews();

        getBinding().cabinetDrawerMc.setText(cabinet.getBoxInfo().getName());
        getBinding().cabinetDrawerSyrl.setText("剩余容量:"+(cabinet.getTotalHole()-cabinet.getUsedHole()));
        getBinding().cabinetDrawerBaifenbi.setText("("+cabinet.getUsedHole()+"/"+cabinet.getTotalHole()+")  ");
        getBinding().cabinetDrawerProgress.setMax(cabinet.getTotalHole());
        getBinding().cabinetDrawerProgress.setProgress(cabinet.getUsedHole());

        drawerAdapter = new DrawerAdapter(R.layout.item_drawer,getActivity());
        getBinding().drawerRecyclerview.setLayoutManager(
                new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        getBinding().drawerRecyclerview.setAdapter(drawerAdapter);

    }

    @Override
    protected void initDatum() {
        super.initDatum();

        drawerAdapter.setData(cabinet.getContainers());
        drawerAdapter.setHandler(handler);
    }

    @Override
    protected void initListeners() {
        super.initListeners();

        getBinding().cabinetDrawerGzImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //传回chooseCabinetFragment
                Message message = new Message();
                message.what = 50;
                handler.sendMessage(message);
            }
        });
    }
}