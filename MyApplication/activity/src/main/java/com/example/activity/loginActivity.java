package com.example.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import android.app.FragmentTransaction;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //添加fragment
        ListFragment fragment = new ListFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.linearlayout,fragment);
        ft.commit();

        Button bt = findViewById(R.id.forget);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this,forgetActivity.class);

                //传递数据，数据需放在bundle再放入intent
                String name = ((EditText)findViewById(R.id.username)).getText().toString();
                Bundle bundle = new Bundle();
                bundle.putCharSequence("name",name);
                intent.putExtras(bundle);

//                startActivity(intent);
                startActivityForResult(intent,0x110);

            }
        });

        //action和data
        Button bt_call = findViewById(R.id.action_tele);
        Button bt_send = findViewById(R.id.action_send);
        bt_call.setOnClickListener(l);
        bt_send.setOnClickListener(l);

        Button big = findViewById(R.id.big);
        big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(intent.ACTION_VIEW);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x110 && resultCode == 0x110){
            Bundle bundle = new Bundle();
            bundle = data.getExtras();
            int image = bundle.getInt("imageid");
            ImageView imageView = findViewById(R.id.imgview);
            imageView.setImageResource(image);
        }
    }
    View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            Button button = (Button) view;
            switch (button.getId()){
                case R.id.action_tele:
                    intent.setAction(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:15868374899"));
                    startActivity(intent);
                    break;
                case R.id.action_send:
                    intent.setAction(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:15868374899"));
                    intent.putExtra("wkj","welcome");//用Intent设置短信内容,手机无短信内容
                    startActivity(intent);
                    break;
            }

        }
    };
    //跟想的不一样
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(this, "触摸", Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event);

    }
    //按一下变状态的按钮才有效，比如手机右边的音量键
    private long exittime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        Toast.makeText(this, "按下", Toast.LENGTH_SHORT).show();
        if (keyCode == KeyEvent.KEYCODE_BACK) {//按键判断，是否为返回键
            exit();
            Toast.makeText(this, "再按下一次退出", Toast.LENGTH_SHORT).show();
            return true;//不明白
        }

        return super.onKeyDown(keyCode, event);
    }
    public void  exit(){
        if((System.currentTimeMillis()-exittime)>=2000){//两次按下时间差
            exittime = System.currentTimeMillis();

        }else{
            finish();
            System.exit(0);
            //system.exit（0）:正常退出，程序正常执行结束退出
            //system.exit(1):是非正常退出，就是说无论程序正在执行与否，都退出，
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Toast.makeText(this, "拾起", Toast.LENGTH_SHORT).show();
        return super.onKeyUp(keyCode, event);
    }
}