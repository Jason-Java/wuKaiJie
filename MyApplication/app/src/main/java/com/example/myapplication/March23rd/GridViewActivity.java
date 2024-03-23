package com.example.myapplication.March23rd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridViewActivity extends AppCompatActivity {
    private int[] arrayPictures = new int[]{R.mipmap.background1,R.mipmap.background2,R.mipmap.headsculpture1,
            R.mipmap.headsculpture2,R.mipmap.chathistory1,R.mipmap.chathistory2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        GridView gridView = findViewById(R.id.gridview);
        //Map<键，值>
        List<Map<String,Object>> listitem = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < arrayPictures.length ; i++) {
            //、Map是一个接口，HashMap继承AbstractMap接口和实现了Map接口的类;
            //2、Map是存储键和值这样的双列数据集合，但存储的数据是没有顺序的，其键不能重复，但其值是可以重复的，可以通过每一个键找到每一个对应的值;HashMap线程不同步的，即线程不安全的，但只有一个线程访问时效率较高;
            //两者功能相同，不过一般在项目中，HashMap用的比较多些。
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("image",arrayPictures[i]);
            listitem.add(map);
        }
        //第四个参数list键，第五个参数cell的id
        SimpleAdapter simpleAdapter = new SimpleAdapter(GridViewActivity.this,listitem,R.layout.gridview_cell,new String[]{"image"},new int[]{R.id.image});
        gridView.setAdapter(simpleAdapter);
    }
}