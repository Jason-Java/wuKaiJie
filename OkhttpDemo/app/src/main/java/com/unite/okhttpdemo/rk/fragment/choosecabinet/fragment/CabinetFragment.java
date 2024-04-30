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
import com.unite.okhttpdemo.api.ApiService;
import com.unite.okhttpdemo.api.listener.HttpObserver;
import com.unite.okhttpdemo.base.fragment.BaseFragment;
import com.unite.okhttpdemo.databinding.FragmentCabinetBinding;
import com.unite.okhttpdemo.domain.cabinet.BoxInfo;
import com.unite.okhttpdemo.domain.cabinet.CabinetInfoResponse;
import com.unite.okhttpdemo.rk.RKActivity;

import java.util.ArrayList;
import java.util.List;


public class CabinetFragment extends BaseFragment<FragmentCabinetBinding> {

    CabinetAdapter cabinetAdapter;

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

        cabinetAdapter.setData(RKActivity.boxInfos);
        cabinetAdapter.setHandler(handler);
    }




}