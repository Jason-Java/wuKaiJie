package com.unite.okhttpdemo.rk;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.api.ApiService;
import com.unite.okhttpdemo.api.listener.HttpObserver;
import com.unite.okhttpdemo.api.listener.ObserverAdapter;
import com.unite.okhttpdemo.base.activity.BaseBindingActivity;
import com.unite.okhttpdemo.databinding.ActivityRkBinding;
import com.unite.okhttpdemo.domain.cabinet.CabinetInfoResponse;
import com.unite.okhttpdemo.domain.cabinet.CabinetResponse;
import com.unite.okhttpdemo.domain.cabinet.Drawer;
import com.unite.okhttpdemo.domain.response.ListResponse;
import com.unite.okhttpdemo.rk.fragment.AddLabelFragment;
import com.unite.okhttpdemo.rk.fragment.choosecabinet.ChooseCabinetFragment;
import com.unite.okhttpdemo.rk.fragment.choosemode.ChooseModeFragment;
import com.unite.okhttpdemo.domain.shiji.ShiJi;
import com.unite.okhttpdemo.rk.fragment.sure.SureFragment;
import com.unite.okhttpdemo.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RKActivity extends BaseBindingActivity<ActivityRkBinding> {

    //用户选择的试剂
    public static ShiJi sj;

    //所有柜子id的list
    public static List<Integer> cabinetIds = new ArrayList<>();
    private String TAG = "RKActivity";

    //柜子信息
    public static List<CabinetInfoResponse> boxInfos = new ArrayList<>();

    //所选柜子
    public static CabinetInfoResponse cabinet;

    //所选抽屉
    public static Drawer drawer;
    //通信
    Handler handler = new Handler(Looper.myLooper()) {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                //转到选择模板fragment
                replaceFragment(chooseModeFragment, true);
                binding.rkTvStep.setText("试剂模板");
            }
            //跳转到选择柜子
            if (msg.what == 1) {
                if (msg.obj instanceof ShiJi) {
                    //用户选择的试剂
                    sj = (ShiJi) msg.obj;
                    replaceFragment(new ChooseCabinetFragment(handler, sj), true);
                    //返回按钮出现
                    binding.rkReturn.setVisibility(View.VISIBLE);
                    //开始倒计时
                    DownTime();
                    binding.rkTvStep.setText("选择柜子");
                }
            }
            //所选柜子
            if (msg.what == 2) {
                if (msg.obj instanceof CabinetInfoResponse) {
                    cabinet = (CabinetInfoResponse) msg.obj;
                }
            }

            //所选柜子的抽屉
            if (msg.what == 3) {
                if (msg.obj instanceof Drawer) {
                    drawer = (Drawer) msg.obj;
                }

                //跳转到最终界面
                SureFragment sureFragment = new SureFragment();
                replaceFragment(sureFragment,true);
                sureFragment.setHandler(handler);
            }

            //部门所有柜子id
            if (msg.what == 100) {
                if (msg.obj instanceof Integer) {
                    cabinetIds.add((Integer) msg.obj);
                }
            }

            //部门柜子的所有抽屉信息
            if (msg.what == 101) {
                if (msg.obj instanceof CabinetInfoResponse) {
                    boxInfos.add((CabinetInfoResponse) msg.obj);
                }
            }

            //响应SureFragment的点击
            if (msg.what == 50) {
                binding.rkReturn.performClick();
            }
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


        //返回按钮消失
        binding.rkReturn.setVisibility(View.GONE);
        //开始界面
//        replaceFragment(addLabelFragment);
//        binding.rkTvStep.setText("试剂入库");
        replaceFragment(chooseModeFragment, false);
        binding.rkTvStep.setText("试剂模板");
    }


    @Override
    protected void initDatum() {
        super.initDatum();


        //获取部门所有柜子的id
        getAllCabinetId();

    }

    @Override
    protected void initListeners() {
        super.initListeners();

        binding.rkFinishMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //回到上一个进栈的fragment
        binding.rkReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                    //如果fragment只剩一个，那么返回按钮消失
                    if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                        binding.rkReturn.setVisibility(View.GONE);
                    }
                    //进入到前一个fragment开始倒计时
                    DownTime();
                } else {
                    // 如果Fragment栈为空，你可能想要结束Activity
                    finish();
                }
            }
        });

    }

    //替换Fragment
    public void replaceFragment(Fragment fragment, Boolean result) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();//开启一个事务

        //要变换fragment的容器
        fragmentTransaction.replace(R.id.rk_layout, fragment);
        if (result) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }


    //获取部门所有柜子的id
    private void getAllCabinetId() {

        ApiService.getInstance()
                .getCabinetIds(sp.getTokenType() + " " + sp.getToken(), sp.getBMID())
                .subscribe(new HttpObserver<ListResponse<CabinetResponse>>(getMainActivity(), true) {
                    @Override
                    public void onSucceeded(ListResponse<CabinetResponse> data) {
                        for (int i = 0; i < data.getResponse().size(); i++) {

                            Message message = new Message();
                            message.what = 100;
                            message.obj = data.getResponse().get(i).getId();
                            handler.sendMessage(message);
                            LogUtil.d(TAG, "getAllCabinetId" + data.getResponse().get(i).getId());

                            //获取柜子信息
                            getBoxInfo(data.getResponse().get(i).getId());
                        }
                    }
                });
    }


    //获取柜子信息
    private void getBoxInfo(int id) {

        ApiService.getInstance()
                .getCabinetInfo(sp.getTokenType() + " " + sp.getToken(), id)
                .subscribe(new ObserverAdapter<CabinetInfoResponse>() {
                    @Override
                    public void onNext(CabinetInfoResponse cabinetInfoResponse) {
                        super.onNext(cabinetInfoResponse);
                        Message message = new Message();
                        message.what = 101;
                        message.obj = cabinetInfoResponse;
                        handler.sendMessage(message);

                        LogUtil.d(TAG, "getBoxInfo" + cabinetInfoResponse.getBoxInfo().getName());
                    }
                });


    }


    //倒计时
    CountDownTimer countDownTimer;
    final long startTime = 60 * 1000;//60秒

    public void DownTime() {

        // 如果存在之前的倒计时，先取消它
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        if (binding.rkReturn.getVisibility() == View.VISIBLE) {
            countDownTimer = new CountDownTimer(startTime, 1000) {
                @Override
                public void onTick(long l) {
                    binding.rkReturn.setText("返回上一步(" + l / 1000 + "S)");
                }

                @Override
                public void onFinish() {
                    //代码点击按钮
                    binding.rkReturn.performClick();
                }
            }.start();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在Activity销毁时取消倒计时
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}