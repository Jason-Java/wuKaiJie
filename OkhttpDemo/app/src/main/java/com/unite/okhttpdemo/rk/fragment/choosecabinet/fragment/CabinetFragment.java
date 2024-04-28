package com.unite.okhttpdemo.rk.fragment.choosecabinet.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.base.fragment.BaseFragment;
import com.unite.okhttpdemo.databinding.FragmentCabinetBinding;
import com.unite.okhttpdemo.table.Cabinet;
import com.unite.okhttpdemo.table.Drawer;

import java.util.ArrayList;
import java.util.List;


public class CabinetFragment extends BaseFragment<FragmentCabinetBinding> {
    List<Cabinet> cabinets = new ArrayList<>();
    CabinetAdapter cabinetAdapter;
    List<Drawer> drawers =new ArrayList<>();

    @Override
    protected FragmentCabinetBinding onCreateViewBinding(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return FragmentCabinetBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initViews() {
        super.initViews();

        cabinetAdapter = new CabinetAdapter(R.layout.item_cabinet,getActivity());
        getBinding().cabinetRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),4));
        getBinding().cabinetRecyclerview.setAdapter(cabinetAdapter);
    }

    @Override
    protected void initDatum() {
        super.initDatum();

        //初始数据抽屉
        for (int i = 0; i < 5; i++) {
            Drawer drawer = new Drawer();
            drawer.setMc("抽屉"+i);
            drawer.setNum(i*2);
            drawer.setSum(10);
            drawers.add(drawer);
        }

        //初始数据柜子
        for (int i = 0; i < 10; i++) {
            Cabinet cabinet = new Cabinet();
            cabinet.setMc("柜子"+i);
            cabinet.setNum(20);
            cabinet.setSum(50);
            cabinet.setXh((i+1)%2);
            cabinet.setDrawers(drawers);
            cabinets.add(cabinet);
        }

        cabinetAdapter.setData(cabinets);
        cabinetAdapter.setHandler(handler);
    }
}