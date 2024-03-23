package com.example.myapplication.March23rd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.myapplication.R;

public class ImageSwitcher2Activity extends AppCompatActivity {

    private ImageSwitcher is;
    private int[] arrayPictures = new int[]{R.mipmap.background1,R.mipmap.background2,R.mipmap.headsculpture1,
    R.mipmap.headsculpture2,R.mipmap.chathistory1,R.mipmap.chathistory2};

    private int index;
    private float touchDownX;
    private float touchUpX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageswitcher2);

        is = findViewById(R.id.is2);
        is.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                //创建视图窗口
                ImageView imageView = new ImageView(ImageSwitcher2Activity.this);
                imageView.setImageResource(arrayPictures[index]);
                return imageView;
            }
        });

        //
        is.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){//判断是否按下
                    touchDownX = motionEvent.getX();
                    return true;
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    touchUpX = motionEvent.getX();
                    if (touchUpX - touchDownX >= 100){//→滑
                        index = index==0?arrayPictures.length-1:index-1;
                        //退场动画
                        is.setOutAnimation(AnimationUtils.loadAnimation(ImageSwitcher2Activity.this, android.R.anim.slide_out_right));
                        //进场动画
                        is.setInAnimation(AnimationUtils.loadAnimation(ImageSwitcher2Activity.this, android.R.anim.slide_in_left));
                        //图片改变
                        is.setImageResource(arrayPictures[index]);
                    }else {//←滑
                        index = index==arrayPictures.length-1?0:index+1;
                        //退场动画
                        is.setOutAnimation(AnimationUtils.loadAnimation(ImageSwitcher2Activity.this, android.R.anim.fade_out));
                        //进场动画
                        is.setInAnimation(AnimationUtils.loadAnimation(ImageSwitcher2Activity.this, android.R.anim.fade_in));
                        //图片改变
                        is.setImageResource(arrayPictures[index]);
                    }
                    return true;
                }
                return false;
            }
        });
    }
}