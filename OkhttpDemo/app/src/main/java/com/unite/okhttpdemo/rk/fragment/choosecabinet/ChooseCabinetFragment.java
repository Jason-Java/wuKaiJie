package com.unite.okhttpdemo.rk.fragment.choosecabinet;

import androidx.annotation.NonNull;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.base.fragment.BaseFragment;
import com.unite.okhttpdemo.databinding.FragmentChoosecabinetBinding;
import com.unite.okhttpdemo.rk.fragment.choosecabinet.fragment.CabinetFragment;
import com.unite.okhttpdemo.rk.fragment.choosecabinet.fragment.DrawerFragment;
import com.unite.okhttpdemo.table.Cabinet;
import com.unite.okhttpdemo.table.shiji.ShiJi;
import com.unite.okhttpdemo.table.SjResult;
import com.unite.okhttpdemo.util.ToastUtil;


public class ChooseCabinetFragment extends BaseFragment<FragmentChoosecabinetBinding> {
    ShiJi sj;
    CabinetFragment cabinetFragment = new CabinetFragment();
    DrawerFragment drawerFragment;
    Cabinet cabinet;
    SjResult sjResult;


    Handler handler;
    public ChooseCabinetFragment(Handler handler,ShiJi sj) {
        this.handler = handler;
        this.sj = sj;
    }


    //子级handler
    Handler childrenHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0){
                if (msg.obj instanceof Cabinet){
                    cabinet = (Cabinet) msg.obj;
                }
                drawerFragment = new DrawerFragment(cabinet);
                switchToFragment(drawerFragment,R.id.chooseCabinet_layout,true);
                drawerFragment.setHandler(childrenHandler);
                getBinding().tvChooseDrawer.setTextColor(getResources().getColor(R.color.light_blue));
                getBinding().viewChooseDrawer.setBackgroundResource(R.color.light_blue);
                getBinding().tvChooseCabinet.setTextColor(getResources().getColor(R.color.black));
                getBinding().viewChooseCabinet.setBackgroundResource(R.color.white);
            }

            if (msg.what == 1){
                if (msg.obj instanceof SjResult){
                    sjResult = (SjResult) msg.obj;
                }
                sjResult.setCabinet(cabinet);
                sjResult.setSj(sj);
                ToastUtil.successShortToast("成功");
            }

        }
    };

    @Override
    protected FragmentChoosecabinetBinding onCreateViewBinding(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return FragmentChoosecabinetBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initViews() {
        super.initViews();

        getBinding().chooseCabinetSj.setText(sj.getCommonName());
        getBinding().chooseCabinetCas.setText("CAS:"+sj.getCAS());
        getBinding().chooseCabinetBm.setText("别名:"+sj.getAlias());

        //跳转到选择柜子
        switchToFragment(cabinetFragment, R.id.chooseCabinet_layout,false);
        cabinetFragment.setHandler(childrenHandler);

    }





}