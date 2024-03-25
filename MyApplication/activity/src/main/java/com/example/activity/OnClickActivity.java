package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class OnClickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onclick);

        ImageView imageView = findViewById(R.id.img);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                registerForContextMenu(view);//注册菜单
                openContextMenu(view);
                return true;//false会闪烁
            }
        });
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                registerForContextMenu(view);//注册菜单
//                openContextMenu(view);
//            }
//        });

        LinearLayout ll = findViewById(R.id.canvas);
        HatView hatView = new HatView(OnClickActivity.this);
        ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hatView.BitmapX = motionEvent.getX()-100;
                hatView.BitmapY = motionEvent.getY()-400;
                //invalidate()是用来刷新View的，必须是在UI线程中进行工作。
                // 比如在修改某个view的显示时，调用invalidate()才能看到重新绘制的界面。
                // invalidate()的调用是把之前的旧的view从主UI线程队列中pop掉。
                hatView.invalidate();
                return true;
            }
        });
        ll.addView(hatView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("收藏");
        menu.add("举报");
    }
}