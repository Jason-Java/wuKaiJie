package com.example.myapplication.March23rd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {
    private int[] img = new int[]{R.drawable.tp1,R.drawable.tp2,R.drawable.tp3,R.drawable.tp4,R.drawable.tp5,R.drawable.tp6};
    private String[] name = new String[]{"张三","李四","王五","吴凯杰","吴晓杰","吴晓羽"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        List<Map<String,Object>> listitem = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < name.length; i++) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("image",img[i]);
            map.put("name",name[i]);
            listitem.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(ListViewActivity.this,listitem,R.layout.listview_friend,new String[]{"image","name"},new int[]{R.id.imagelistview,R.id.tvlistview});
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Map<String,Object> map=(Map<String,Object>) adapterView.getItemAtPosition(position);
                Toast.makeText(ListViewActivity.this, map.get("name").toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}