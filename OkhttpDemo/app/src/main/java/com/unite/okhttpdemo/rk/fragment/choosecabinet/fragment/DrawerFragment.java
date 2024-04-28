package com.unite.okhttpdemo.rk.fragment.choosecabinet.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.base.fragment.BaseFragment;
import com.unite.okhttpdemo.databinding.FragmentDrawerBinding;
import com.unite.okhttpdemo.table.Cabinet;

import java.util.List;

public class DrawerFragment extends BaseFragment<FragmentDrawerBinding> {


    @Override
    protected FragmentDrawerBinding onCreateViewBinding(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return FragmentDrawerBinding.inflate(getLayoutInflater());
    }

    Cabinet cabinet;
    DrawerAdapter drawerAdapter;

    public DrawerFragment(Cabinet cabinet) {
        this.cabinet = cabinet;
    }

    @Override
    protected void initViews() {
        super.initViews();

        getBinding().cabinetDrawerMc.setText(cabinet.getMc());
        getBinding().cabinetDrawerSyrl.setText("剩余容量:"+(cabinet.getSum()-cabinet.getNum()));
        getBinding().cabinetDrawerBaifenbi.setText("("+cabinet.getNum()+"/"+cabinet.getSum()+")  ");
        getBinding().cabinetDrawerProgress.setMax(cabinet.getSum());
        getBinding().cabinetDrawerProgress.setProgress(cabinet.getNum());

        drawerAdapter = new DrawerAdapter(R.layout.item_drawer,getActivity());
        getBinding().drawerRecyclerview.setLayoutManager(
                new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        getBinding().drawerRecyclerview.setAdapter(drawerAdapter);

    }

    @Override
    protected void initDatum() {
        super.initDatum();

        drawerAdapter.setData(cabinet.getDrawers());
        drawerAdapter.setHandler(handler);
    }
}