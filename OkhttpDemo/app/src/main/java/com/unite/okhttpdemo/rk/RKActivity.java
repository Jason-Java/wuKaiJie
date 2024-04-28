package com.unite.okhttpdemo.rk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.base.activity.BaseBindingActivity;
import com.unite.okhttpdemo.databinding.ActivityRkBinding;
import com.unite.okhttpdemo.rk.fragment.AddLabelFragment;
//import com.unite.okhttpdemo.rk.fragment.choosecabinet.ChooseCabinetFragment;
import com.unite.okhttpdemo.rk.fragment.choosemode.ChooseModeFragment;
import com.unite.okhttpdemo.table.shiji.ShiJi;

public class RKActivity extends BaseBindingActivity<ActivityRkBinding> {


    //通信
    Handler handler = new Handler(Looper.myLooper()){

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                //转到选择模板fragment
                replaceFragment(chooseModeFragment);
                binding.rkTvStep.setText("试剂模板");
            }

//            if (msg.what == 1){
//                if (msg.obj instanceof ShiJi){
//                    replaceFragment(new ChooseCabinetFragment(handler,(ShiJi) msg.obj));
//                    binding.rkTvStep.setText("选择柜子");
//                }
//            }
        }
    };

    AddLabelFragment addLabelFragment = new AddLabelFragment(handler);
    ChooseModeFragment chooseModeFragment = new ChooseModeFragment(handler);


    @Override
    protected void initBinding() {
        binding = ActivityRkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void initViews() {
        super.initViews();
        //开始界面
        replaceFragment(addLabelFragment);
        binding.rkTvStep.setText("试剂入库");
    }

    //替换Fragment
    public void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();//开启一个事务
        //要变换fragment的容器
        fragmentTransaction.replace(R.id.rk_layout,fragment);
        fragmentTransaction.commit();
    }


}