package com.unite.okhttpdemo.rk.fragment.choosemode;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.api.Api;
import com.unite.okhttpdemo.base.fragment.BaseFragment;
import com.unite.okhttpdemo.databinding.FragmentChoosemodeBinding;
import com.unite.okhttpdemo.listener.HttpObserver;
import com.unite.okhttpdemo.table.shiji.ShiJi;
import com.unite.okhttpdemo.rk.fragment.choosemode.adapter.RKModeAdapter;
import com.unite.okhttpdemo.table.shiji.ShiJiJson;

import java.util.ArrayList;
import java.util.List;


public class ChooseModeFragment extends BaseFragment<FragmentChoosemodeBinding> {
    List<ShiJi> num = new ArrayList<>();
    RKModeAdapter rkModeAdapter;


    @Override
    protected FragmentChoosemodeBinding onCreateViewBinding(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return FragmentChoosemodeBinding.inflate(getLayoutInflater());
    }

    Handler handler;

    public ChooseModeFragment(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected void initViews() {
        super.initViews();

        //设置布局
        getBinding().rkChooseRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),3));
        rkModeAdapter = new RKModeAdapter(R.layout.item_rk_choose,getActivity());
        getBinding().rkChooseRecyclerview.setAdapter(rkModeAdapter);

    }

    @Override
    protected void initDatum() {
        super.initDatum();
        rkModeAdapter.setData(new ArrayList<>());
        //通信
        rkModeAdapter.setHandler(handler);
    }

    @Override
    protected void initListeners() {
        super.initListeners();

        //清空et
        getBinding().rkChooseBtClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBinding().rkChooseEt.setText("");
                rkModeAdapter.setData(new ArrayList<>());
                getBinding().rkChooseTv.setText("查询结果(0条)");
            }
        });

        //搜索
        getBinding().rkChooseBtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = getBinding().rkChooseEt.getText().toString();
                getShiJi(500,name);
            }
        });


    }

    //获取试剂信息
    private void getShiJi(int rows,String name) {
        Api.getInstance()
                .getShiJi(sp.getTokenType()+" "+sp.getToken(),rows,name)
                .subscribe(new HttpObserver<ShiJiJson>() {
                    @Override
                    public void onSucceeded(ShiJiJson data) {
                        for (int i = 0; i < data.getResponse().getData().size(); i++) {
                            num.add(data.getResponse().getData().get(i));
                            if (num.size() ==500){
                                break;
                            }
                        }
                        getBinding().rkChooseTv.setText("查询结果("+num.size()+"条)");
                        rkModeAdapter.setData(num);
                        num = new ArrayList<>();
                    }
                });
    }
}