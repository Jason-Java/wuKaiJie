package com.example.myapplication.March23rd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.myapplication.R;

public class GridView2Activity extends AppCompatActivity {
    private int[] arrayPictures = new int[]{R.drawable.tp1,R.drawable.tp2,R.drawable.tp3,R.drawable.tp4,R.drawable.tp5,R.drawable.tp6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview2);

        GridView gridView = findViewById(R.id.gridView2);
        gridView.setAdapter(new ImageAdapter(GridView2Activity.this));
    }
    //定制
    public class ImageAdapter extends BaseAdapter{
        private Context mContext;//上下文对象

        //编写构造方法
        public ImageAdapter(Context context){
            mContext = context;
        }

        @Override
        public int getCount() {
            return arrayPictures.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null){
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(300,400));//单位不是dp
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//CENTER_CROP按纵横比缩放，并填满imageview
                imageView.setPadding(10,10,10,10);//left\right失效
            }else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(arrayPictures[position]);
            return imageView;
        }
    }
}