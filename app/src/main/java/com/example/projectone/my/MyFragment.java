package com.example.projectone.my;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.projectone.R;
import com.example.projectone.add.activity.AddEnjoyActivity;
import com.example.projectone.my.activity.ChangemyActivity;
import com.example.projectone.my.activity.MyEnjoyActivity;
import com.example.projectone.table.User;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MyFragment extends Fragment {
    View view;
    Intent intent;
    String name;
    TextView my_name;
    TextView my_mytext;
    ImageView headpicture;
    private int REQUEST_PICTURE = 100;
    List<User> users;
    User user;
    Button my_changemy;
    Button my_exit;
    Button myEnjoy;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_fragment,null);

        initView();

        initData();

        initEvent();

        return view;
    }

    private void initView() {
        my_name = view.findViewById(R.id.my_name);
        headpicture = view.findViewById(R.id.my_headpicture);
        my_exit = view.findViewById(R.id.my_exit);
        my_changemy = view.findViewById(R.id.my_changemy);
        my_mytext = view.findViewById(R.id.my_mytext);
        myEnjoy = view.findViewById(R.id.my_enjoy);

        intent = getActivity().getIntent();
        name = intent.getStringExtra("name");
        my_name.setText(name);

        Connector.getDatabase();
        users = LitePal.where("username = ?",name).find(User.class);
        user = users.get(0);
        if (user.getHeadpicture() == null){
            Glide.with(this).load(R.drawable.defaultheadpicture).into(headpicture);
            Toast.makeText(getActivity(), "没有本地录入头像", Toast.LENGTH_SHORT).show();
        }else{
            // 加载本地图片
            try {
                Uri uri = Uri.parse(user.getHeadpicture());
                Glide.with(this).load(uri).into(headpicture);
            }catch (Exception e){
                Toast.makeText(getActivity(),"本地资源找不到", Toast.LENGTH_SHORT).show();
                Glide.with(this).load(R.drawable.defaultheadpicture).into(headpicture);
            }
        }

        if (user.getSentence() != null){
            my_mytext.setText(user.getSentence());
        }
    }


    private void initData() {
    }


    private void initEvent() {
        //长按更换头像
        headpicture.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    //没有获取权限，请求,第三个参数时请求码，用于在回调方法中判断哪个权限的回调
                    Toast.makeText(getActivity(), "没有读取相册的权限", Toast.LENGTH_SHORT).show();
                }else{
                    openpicture();
                }
                return false;
            }
        });

        //更改资料
        my_changemy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), ChangemyActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        //退回登录页面
        my_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        //进入自己的分享界面
        myEnjoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), MyEnjoyActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }



    //打开相册方法
    private void openpicture() {
        Intent intent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //显示相册里的照片，不显示视频
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent,REQUEST_PICTURE);
    }

    //相册选择完后的图片返回值
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_PICTURE && resultCode ==RESULT_OK && data != null){
            Uri imageurl = data.getData();
            Glide.with(this).load(imageurl).into(headpicture);

            users = LitePal.where("username = ?",name).find(User.class);
            user = users.get(0);
            user.setHeadpicture(imageurl.toString());
            user.save();

        }
    }

}
