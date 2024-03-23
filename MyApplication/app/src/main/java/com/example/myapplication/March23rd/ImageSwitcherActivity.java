package com.example.myapplication.March23rd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.myapplication.R;

public class ImageSwitcherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageswitcher);

        ImageSwitcher imageSwitcher = findViewById(R.id.imageSwitcher);
        //淡出效果
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(ImageSwitcherActivity.this,android.R.anim.fade_out));
        //进入的动画
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(ImageSwitcherActivity.this,android.R.anim.fade_in));
        //设置ViewFactory对象，用于完成两个图片切换时ViewSwitcher的转换操作
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(ImageSwitcherActivity.this);
                imageView.setImageResource(R.mipmap.background1);//初始图像
                return imageView;
            }
        });

        imageSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ImageSwitcher) view).setImageResource(R.mipmap.background2);
            }
        });
    }
}