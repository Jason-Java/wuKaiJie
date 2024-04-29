package com.unite.okhttpdemo.rk.fragment.choosemode;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.unite.okhttpdemo.R;
import com.unite.okhttpdemo.api.ApiService;
import com.unite.okhttpdemo.base.fragment.BaseFragment;
import com.unite.okhttpdemo.databinding.FragmentChoosemodeBinding;
import com.unite.okhttpdemo.api.listener.HttpObserver;
import com.unite.okhttpdemo.rk.fragment.choosemode.adapter.SearchAdapter;
import com.unite.okhttpdemo.table.shiji.ShiJi;
import com.unite.okhttpdemo.rk.fragment.choosemode.adapter.RKModeAdapter;
import com.unite.okhttpdemo.table.shiji.ShiJiJson;
import com.unite.okhttpdemo.util.PopupWindowUtil;

import java.util.ArrayList;
import java.util.List;


public class ChooseModeFragment extends BaseFragment<FragmentChoosemodeBinding> {
    List<ShiJi> num = new ArrayList<>();
    RKModeAdapter rkModeAdapter;

    Handler modeHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String mc = (String) msg.obj;
                getBinding().rkChooseEt.setText(mc);
                popupWindow.dismiss();
            }
        }
    };
    private SearchAdapter searchAdapter;
    private PopupWindow popupWindow;


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
                popupWindow.dismiss();
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

        //搜索框事件
        getBinding().rkChooseEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                ApiService.getInstance()
                        .getShiJi(sp.getTokenType()+" "+sp.getToken(),10,getBinding().rkChooseEt.getText().toString())
                        .subscribe(new HttpObserver<ShiJiJson>() {
                            @Override
                            public void onSucceeded(ShiJiJson data) {
                                //设置观察
                                searchAdapter.setHandler(modeHandler);
                                //设置数据
                                List<ShiJi> num = new ArrayList<>();
                                for (int i = 0; i < data.getResponse().getData().size(); i++) {
                                    num.add(data.getResponse().getData().get(i));
                                }
                                searchAdapter.setData(num);
                                View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow_search, null);
                                View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_choosemode, null);
                                int recyclerviewheight;
                                if (num.size() < 5){
                                    recyclerviewheight = num.size()*40;
                                }else {
                                    recyclerviewheight = 200;
                                }
                                popupWindow = PopupWindowUtil.getPopupWindow(getActivity(),
                                        popupView,rootview,200,recyclerviewheight,100,100);
                                //防止被键盘影响显示位置
                                popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
                                //设置适配器
                                RecyclerView recyclerView = popupView.findViewById(R.id.search_recyclerview);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                                searchAdapter = new SearchAdapter(R.layout.item_shiji,getActivity(),getBinding().rkChooseEt.getText().toString(),popupWindow);
                                recyclerView.setAdapter(searchAdapter);
                            }
                        });
            }
        });


    }

    //获取试剂信息
    private void getShiJi(int rows,String name) {
        ApiService.getInstance()
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