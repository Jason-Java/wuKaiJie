package com.unite.okhttpdemo.rk.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.base.fragment.BaseFragment;
import com.unite.okhttpdemo.databinding.FragmentAddlabelBinding;
import com.unite.okhttpdemo.main.MainActivity;
import com.unite.okhttpdemo.rk.RKActivity;
import com.unite.okhttpdemo.util.Constant;
import com.unite.okhttpdemo.util.ToastUtil;


public class AddLabelFragment extends BaseFragment<FragmentAddlabelBinding> {

    @Override
    protected FragmentAddlabelBinding onCreateViewBinding(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return FragmentAddlabelBinding.inflate(getLayoutInflater());
    }

    public AddLabelFragment() {
    }

    Handler handler;
    public AddLabelFragment(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected void initListeners() {
        super.initListeners();

        getBinding().rkBtNewadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.limit.contains(Constant.ACTION_NEW_RK)){

                    //转到选择模板fragment
                    Message message = new Message();
                    message.what = 0;
                    handler.sendMessage(message);

                }else {
                    ToastUtil.errorShortToast(R.string.limit_false);
                }
            }
        });
    }
}