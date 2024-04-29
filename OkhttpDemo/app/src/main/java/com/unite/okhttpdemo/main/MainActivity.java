package com.unite.okhttpdemo.main;

import android.view.View;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.api.ApiService;
import com.unite.okhttpdemo.base.activity.BaseBindingActivity;
import com.unite.okhttpdemo.databinding.ActivityMainBinding;
import com.unite.okhttpdemo.domain.limit.Children;
import com.unite.okhttpdemo.domain.response.DetailResponse;
import com.unite.okhttpdemo.api.listener.HttpObserver;
import com.unite.okhttpdemo.rk.RKActivity;
import com.unite.okhttpdemo.util.Constant;
import com.unite.okhttpdemo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseBindingActivity<ActivityMainBinding> {

    //获取到的该账号的试剂柜权限
    public static List<String> limit = new ArrayList<>();


    @Override
    protected void initBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void initViews() {
        super.initViews();

        //登录设置用户名
        binding.mainTvUser.setText(sp.getUser());
    }

    @Override
    protected void initDatum() {
        super.initDatum();

        //获取权限
        getUserLimit();
    }

    @Override
    protected void initListeners() {
        super.initListeners();

        //查询领取
        binding.ivFindAndReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (limit.contains(Constant.ACTION_LY)){
                    ToastUtil.successShortToast(R.string.limit_true);
                }else {
                    ToastUtil.successShortToast(R.string.limit_false);
                }
            }
        });

        //快速开锁
        binding.ivOpenLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (limit.contains(Constant.ACTION_OPEN_LOCK)){
                    ToastUtil.successShortToast(R.string.limit_true);
                }else {
                    ToastUtil.errorShortToast(R.string.limit_false);
                }
            }
        });

        //归还
        binding.ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (limit.contains(Constant.ACTION_GH)){
                    ToastUtil.successShortToast(R.string.limit_true);
                }else {
                    ToastUtil.errorShortToast(R.string.limit_false);
                }
            }
        });

        //入库
        binding.ivPutInStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (limit.contains(Constant.ACTION_RK)){
                    startActivity(RKActivity.class);
                }else {
                    ToastUtil.errorShortToast(R.string.limit_false);
                }
            }
        });

        //送处
        binding.ivFinalAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (limit.contains(Constant.ACTION_SC)){
                    ToastUtil.successShortToast(R.string.limit_true);
                }else {
                    ToastUtil.errorShortToast(R.string.limit_false);
                }
            }
        });

        //收货
        //没找到权限

    }

    //获取用户权限,试剂柜权限
    private void getUserLimit() {
        ApiService.getInstance()
                .getLimit(sp.getTokenType()+" "+sp.getToken())
                .subscribe(new HttpObserver<DetailResponse<Children>>() {
                    @Override
                    public void onSucceeded(DetailResponse<Children> data) {
                        //根节点
                        List<Children> name = data.getResponse().getChildren();
                        Children sjg = new Children();
                        for (int i = 0; i < name.size(); i++) {
                            if (name.get(i).getPath().equals(Constant.REGEANBOKX)){
                                sjg = name.get(i);
                                break;
                            }
                        }
                        //查看试剂柜所有权限
                        for (int i = 0; i < sjg.getChildren().size(); i++) {
                            limit.add(sjg.getChildren().get(i).getPath());
                        }

                    }
                });

    }
}