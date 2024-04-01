package com.example.projectone;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.projectone.table.User;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

public class MyFragment extends Fragment {
    Intent intent;
    String name;
    TextView my_name;
    ImageView headpicture;
    private int REQUEST_PICTURE = 100;
    List<User> users;
    User user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment,null);
        intent = getActivity().getIntent();
        name = intent.getStringExtra("name");
        my_name = view.findViewById(R.id.my_name);
        my_name.setText(name);

        Connector.getDatabase();
        users = LitePal.where("username = ?",name).find(User.class);
        user = users.get(0);

        headpicture = view.findViewById(R.id.my_headpicture);

        if (user.getHeadpicture() == null){
            Toast.makeText(getActivity(), "jj", Toast.LENGTH_SHORT).show();
        }else{
            // 加载本地图片
            try {
                Uri uri = Uri.parse(user.getHeadpicture());
                Glide.with(this).load(uri).into(headpicture);
            }catch (Exception e){
                Toast.makeText(getActivity(),e.toString(), Toast.LENGTH_SHORT).show();
            }

        }

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
        return view;
    }

    private void openpicture() {
        Intent intent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //显示相册里的照片，不显示视频
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent,REQUEST_PICTURE);
    }

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
