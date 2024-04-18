package com.example.demo01.activity;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.example.demo01.R;

import butterknife.BindView;

/**
 *通用标题界面
 */
public class BaseTitleActivity extends BaseCommonActivity{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void initViews() {
        super.initViews();

        //初始化toolbar
        setSupportActionBar(toolbar);

        //是否显示返回按钮
        if (isShowBackMenu()){
            showBackMenu();
        }
    }

    /**
     * 显示返回按钮
     */
    protected void showBackMenu() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 是否显示返回按钮
     * @return
     */
    protected boolean isShowBackMenu() {
        return true;
    }

    /**
     * 菜单点击了回调
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //ToolBar返回按钮点击
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
